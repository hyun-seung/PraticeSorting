package com.pratice.sortApp.sort;

import com.pratice.sortApp.util.StringUtil;

/*
    팀 정렬
    - 시간복잡도 : O(NlogN)
    - 특징 : 최상의 시간복잡도는 O(N), 객체 참조 및 비교 비용이 비싼 경우 QuickSort에 비해 빠르다. 안정정렬 가능.
            오버헤드가 O(NlogN) 알고리즘에 비해 큰 편이기 때문에 간단한 정렬에서는 비효율적.
 */
public class TimSort implements ExecutableSort {

    private static final int THRESHOLD = 32;

    @Override
    public int[] run(int[] a) {
        timSort(a, 0, a.length);
        return a;
    }

    private class IntMergeStack {
        private int[] array;
        private int[] runBase;
        private int[] runLength;
        private int stackSize = 0;  // run 스택의 원소 개수를 가리킬 변수

        public IntMergeStack(int[] a) {
            this.array = a;
//            int len = a.length;

            runBase = new int[40];
            runLength = new int[40];
        }

        public void pushRun(int runBase, int runLen) {
            this.runBase[stackSize] = runBase;
            this.runLength[stackSize] = runLen;
            stackSize += 1;
        }

        public void mergeForce() {
            while (stackSize > 1) {
                if (stackSize > 2 && runLength[stackSize - 3] < runLength[stackSize - 1]) {
                    merge(stackSize - 3);
                } else {
                    merge(stackSize - 2);
                }
            }
        }

        public void merge() {
            /*
                기본적인 메커니즘
                1. runLen[i-3] > runLen[i-2] + runLen[i-1]
                2. runLen[i-2] > runLen[i-1]
             */

            while (stackSize > 1) {
                if (stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1]
                    || stackSize > 3 && runLength[stackSize - 4] <= runLength[stackSize - 3] + runLength[stackSize - 2]) {

                    if (runLength[stackSize - 3] < runLength[stackSize - 1]) {
                        merge(stackSize - 3);
                    } else {
                        merge(stackSize - 2);
                    }

                } else if (runLength[stackSize - 2] <= runLength[stackSize - 1]){
                    merge(stackSize - 2);
                } else {
                    return;
                }
            }
        }

        /*
            run[idx] 와 run[idx + 1]이 병합됨
         */
        private void merge(int idx) {
            int start1 = runBase[idx];
            int start2 = runBase[idx+1];
            int length1 = runLength[idx];
            int length2 = runLength[idx + 1];

            runLength[idx] = length1 + length2;

            /*
                상위 3개 (A, B, C)에서 A, B가 병합 할 경우 C를 당겨온다.

                ex)
                stack [[A], [B], [C]]
                -> stack [[A+B], [B], [C]]
                -> stack [[A+B], [C], [C]]

                이 때 마지막 [C](stack[i - 1])는 어차피 더 이상 참조될 일 없음.
             */
            if (idx == (stackSize - 3)) {
                runBase[idx + 1] = runBase[idx + 2];
                runLength[idx + 1] = runLength[idx + 2];
            }
            stackSize -= 1;

            int lo = gallopRight(array[start2], array, start1, length1);

            if (length1 == lo) {
                return;
            }
            start1 += lo;
            length1 -= lo;

            int hi = gallopLeft(array[start1 + length2 - 1], array, start2, length2);

            if (hi == 0) {
                return;
            }

            length2 = hi;
            if (length1 <= length2) {
                mergeLo(start1, length1, start2, length2);
            } else {
                mergeHi(start1, length1, start2, length2);
            }
        }

        /**
         * RUN B의 첫번째 원소보다 큰 원소들이 첫번째 출현하는 위치를 RUN A에서 찾는다.
         * @param key       run B의 key
         * @param array     배열
         * @param base      run A의 시작지점
         * @param lenOfA    run A의 길이
         * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
         */
        private int gallopRight(int key, int[] array, int base, int lenOfA) {
            int lo = 0;  // 이전 탐색(gallop) 지점
            int hi = 1;  // 현재 탐색(gallop) 지점

            if (key < array[base]) {
                return 0;
            } else {
                int maxLen = lenOfA;

                while (hi < maxLen && array[base + hi] <= key) {
                    lo = hi;
                    hi = (hi << 1) + 1;

                    if (hi <= 0) {  // overflow 발생 시 run A의 끝 점으로 초기화
                        hi = maxLen;
                    }
                }

                if (hi > maxLen) {
                    hi = maxLen;
                }
            }

            lo += 1;

            // binary search (Upper Bound)
            while (lo < hi) {
                int mid = lo + ((hi - lo) >>> 1);

                if (key < array[base + mid]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            return hi;
        }

        /**
         * RUN A의 첫 번째 원소(오른쪽 끝)보다 큰 원소들이 첫 번째로 출현하는 위치를 RUN B에서 찾는다.
         * @param key       run A의 key
         * @param array     array 배열
         * @param base      base run B의 시작 지점
         * @param lenOfB    run B의 길이
         * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
         */
        private int gallopLeft(int key, int[] array, int base, int lenOfB) {
            int lo = 0;
            int hi = 1;

            if (key > array[base + lenOfB - 1]) {
                return lenOfB;
            } else {
                int startPointOfRun = base + lenOfB - 1;

                int maxLen = lenOfB;

                while (hi < maxLen && key <= array[startPointOfRun - hi]) {
                    lo = hi;
                    hi = (hi << 1) + 1;

                    if (hi <= 0) {
                        hi = maxLen;
                    }
                }

                if (hi > maxLen) {
                    hi = maxLen;
                }

                /*
                    뒤에서부터 탐색했기 때문에 실제 가리키는 인덱스는 lo > hi 이므로
                    이분 탐색을 하기 위해 run B에 대해 가리키는 지점을 서로 바꿔준다.
                 */
                int temp = lo;
                lo = lenOfB - 1 - hi;
                hi = lenOfB - 1 - temp;
            }

            lo += 1;

            // binary search (lower bound)
            while (lo < hi) {
                int mid = lo + ((hi - lo) >>> 1);

                if (key <= array[base + mid]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            return hi;
        }

        /**
         * 상대적으로 낮은 인덱스에 위치한 run을 기준으로 복사하여 실제 배열 원소를 병합하는 메소드
         * @param start1    run A에서의 병합 시작 지점
         * @param length1   run A에서의 병합해야 할 길이(개수)
         * @param start2    run B에서의 병합 시작 지점
         * @param length2   run B에서의 병합해야 할 길이(개수)
         */
        private void mergeLo(int start1, int length1, int start2, int length2) {
            int[] temp = new int[length1];
            System.arraycopy(array, start1, temp, 0, length1);

            int insertIdx = start1; // 재배치 되는 위치
            int runBIdx = start2;    // run B의 탐색 위치
            int tempIdx = 0;        // 복사한 run A의 탐색 위치

            int leftRemain = length1;   // 배치해야 할 run A의 원소 개수
            int rightRemain = length2;  // 배치해야 할 run B의 원소 개수

            while (leftRemain != 0 && rightRemain != 0) {
                if (array[runBIdx] < temp[tempIdx]) {
                    array[insertIdx++] = array[runBIdx++];
                    rightRemain--;
                } else {
                    array[insertIdx++] = temp[tempIdx++];
                    leftRemain--;
                }
            }

            if (leftRemain != 0) {
                System.arraycopy(temp, tempIdx, array, insertIdx, leftRemain);
            } else {
                System.arraycopy(array, runBIdx, array, insertIdx, rightRemain);
            }
        }

        /**
         * 상대적으로 높은 인덱스에 위치한 run을 기준으로 복사하여 실제 배열 원소를 병합하는 메소드
         * @param start1    run A에서의 병합 시작 지점
         * @param length1   run A에서의 병합해야 할 길이(개수)
         * @param start2    run B에서의 병합 시작 지점
         * @param length2   run B에서의 병합해야 할 길이(개수)
         */
        private void mergeHi(int start1, int length1, int start2, int length2) {
            int[] temp = new int[length2];
            System.arraycopy(array, start2, temp, 0, length2);

            int insertIdx = start2 + length2 - 1;   // 재배치되는 위치
            int runAIdx = start1 + length1 - 1;     // run A의 탐색 위치
            int tempIdx = length2 - 1;              // 복사한 run B의 탐색 위치

            int leftRemain = length1;
            int rightRemain = length2;

            while (leftRemain != 0 && rightRemain != 0) {
                if (array[runAIdx] > temp[tempIdx]) {
                    array[insertIdx--] = array[runAIdx--];
                    leftRemain--;
                } else {
                    array[insertIdx--] = temp[tempIdx--];
                    rightRemain--;
                }
            }

            if (rightRemain != 0) {
                System.arraycopy(temp, 0, array, start1, rightRemain);
            } else {
                System.arraycopy(array, start1, array, start1, leftRemain);
            }
        }
    }

    private void timSort(int[] a, int lo, int hi) {
        int remain = hi - lo;

        if (remain < 2) {
            return;
        }

        if (remain < THRESHOLD) {
            int increaseRange = getAscending(a, lo, hi);
            BinarySort(a, lo, hi, lo + increaseRange);
            return;
        }

        IntMergeStack ims = new IntMergeStack(a);
        int minRun = minRunLength(remain);

        do {
            int runLen = getAscending(a, lo, hi);

            if (runLen < minRun) {
                int counts = remain > minRun ? remain : minRun;

                BinarySort(a, lo, lo + counts, lo + runLen);

                runLen = counts;
            }

            ims.pushRun(lo, runLen);
            ims.merge();

            lo += runLen;
            remain -= runLen;
        } while (remain != 0);

        ims.mergeForce();
    }

    private int minRunLength(int runSize) {
        int r = 0;

        while (runSize >= THRESHOLD) {
            r |= (runSize & 1);
            runSize >>= 1;
        }
        return runSize + r;
    }

    private void BinarySort(int[] a, int lo, int hi, int start) {
        if (lo == start) {
            start += 1;
        }

        for (; start < hi; start++) {
            int target = a[start];

            int loc = binarySearch(a, target, lo, start);

            int j = start - 1;

            while (j >= loc) {
                a[j+1] = a[j];
                j -= 1;
            }

            a[loc] = target;
        }
    }

    private int binarySearch(int[] a, int key, int lo, int hi) {
        int mid;
        while (lo < hi) {
            mid = (lo + hi) >>> 1;

            if (key < a[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private int getAscending(int[] a, int lo, int hi) {
        int limit = lo + 1;
        if (limit == hi) {
            return 1;
        }

        if (a[lo] <= a[limit]) {
            while (limit < hi && a[limit - 1] <= a[limit]) {
                limit++;
            }
        } else {
            while (limit < hi && a[limit - 1] > a[limit]) {
                limit++;
            }
            reversing(a, lo, limit);
        }
        return limit - lo;
    }

    private void reversing(int[] a, int lo, int hi) {
        hi--;
        while (lo < hi) {
            int temp = a[lo];
            a[lo++] = a[hi];
            a[hi--] = temp;
        }
    }
}
