package com.pratice.sortApp.sort;

import com.pratice.sortApp.util.StringUtil;

/*
    이진 삽입 정렬
    - 시간복잡도 : O(N^2)
    - 특징 : 추가적인 메모리 소비가 적다. 비교 비용이 비싸질수록 삽입 정렬에 비해 빨라진다. 항상 삽입 정렬보다 빠른건 아니다.
 */
public class BinaryInsertionSort implements ExecutableSort {

    @Override
    public int[] run(int[] a) {
        System.out.println("\n입력 배열 : " + StringUtil.getLineByIntArray(a));

        if (a.length < 2) {
            return a;
        }

        int incLength = getAscending(a, 0, a.length);

        System.out.println("순수 정렬 위치 : " + incLength + "\n");

        binaryInsertionSort(a, 0, a.length, incLength);

        System.out.println("최종 배열 : " + StringUtil.getLineByIntArray(a));
        return a;
    }

    private int getAscending(int[] a, int lo, int hi) {
        int limit = lo + 1;
        if (limit == hi) {
            return 1;
        }

        if (a[lo] <= a[limit]) {
            while (limit < hi && a[limit - 1] <= a[limit]) {
                limit += 1;
            }
        } else {
            while (limit < hi && a[limit - 1] > a[limit]) {
                limit += 1;
            }
            reversing(a, lo, limit);
        }
        return limit - lo;
    }

    private void binaryInsertionSort(int[] a, int lo, int hi, int start) {
        if (lo == start) {
            start += 1;
        }

        for (; start < hi; start++) {
            int target = a[start];

            int loc = binarySearch(a, target, lo, start);

            int j = start - 1;

            while (j >= loc) {
                a[j+1] = a[j];
                j--;
            }

            a[loc] = target;

            System.out.println("TARGET : " + target);
            System.out.println(StringUtil.getLineByIntArray(a));
        }
    }

    private int binarySearch(int[] a, int key, int lo, int hi) {
        int mid;
        while (lo < hi) {
            mid = lo + ((hi - lo) / 2);

            if (key < a[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private void reversing(int[] a, int lo, int hi) {
        hi -= 1;
        while (lo < hi) {
            int temp = a[lo];
            a[lo] = a[hi];
            a[hi] = temp;
            lo += 1;
            hi -= 1;
        }
    }
}
