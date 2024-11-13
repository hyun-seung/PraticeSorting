package com.pratice.sortApp.sort;

/*
    쉘 정렬
    - 시간복잡도 : O(NlogN)  ~ O(N^2)
    - 특징 : 멀리 있는 원소들끼리 빠르게 비교 및 교환이 이루어진다.
            gap sequence에 영향을 많이 받으며 적절한 시퀀스를 선택해야 한다.
 */
public class ShellSort implements ExecutableSort {

    private final static int[] gap = {
            1, 4, 10, 23, 57, 132, 301, 701, 1750, 3937,
            8858, 19930, 44842, 100894, 227011, 510774,
            1149241, 2585792, 5818032, 13090572, 29453787,
            66271020, 149109795, 335497038, 754868335, 1698453753
    };

    @Override
    public int[] run(int[] a) {
        shellSort(a, a.length);
        return a;
    }

    private void shellSort(int[] a, int size) {
        int gapIndex = getGap(size);

        while (gapIndex >= 0) {
            int step = gap[gapIndex--];

            for (int i=step; i<size; i++) {
                for (int j=i; j>=step && a[j] < a[j-step]; j-=step) {
                    swap(a, j, j-step);
                }
            }
        }
    }

    private int getGap(int length) {
        int index = 0;
        int len = (int)(length / 2.25);
        while (gap[index] < len) {
            index++;
        }
        return index;
    }

    private void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
