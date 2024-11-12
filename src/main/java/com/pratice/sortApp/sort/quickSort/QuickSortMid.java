package com.pratice.sortApp.sort.quickSort;

import com.pratice.sortApp.sort.ExecutableSort;
import com.pratice.sortApp.util.StringUtil;

/*
    퀵 정렬
    - 시간복잡도 : O(NlogN)
    - 특징 : 특정 조건하에 성능이 급격하게 떨어진다. 재귀 미 사용시 난이도 급 상승
 */
public class QuickSortMid implements ExecutableSort {
    @Override
    public int[] run(int[] a) {
        quickSort_mid(a, 0, a.length - 1);
        return a;
    }

    private void quickSort_mid(int[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int pivot = partition(a, lo, hi);
        System.out.println("피벗 : " + a[pivot]);
        System.out.println(StringUtil.getLineByIntArray(a));


        quickSort_mid(a, lo, pivot);
        quickSort_mid(a, pivot + 1, hi);
    }

    private int partition(int[] a, int left, int right) {
        int lo = left - 1;
        int hi = right + 1;

        int pivot = a[(left + right) / 2];

        while (true) {

            do {
                lo++;
            } while (a[lo] < pivot);

            do {
                hi--;
            } while (a[hi] > pivot && lo <= hi);

            if (lo >= hi) {
                return hi;
            }

            swap(a, lo, hi);
        }
    }

    private void swap(int[] a, int i, int j) {
        System.out.println("lo : " + a[i] + " hi : " + a[j]);
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
