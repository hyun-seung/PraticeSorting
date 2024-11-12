package com.pratice.sortApp.sort.quickSort;

import com.pratice.sortApp.sort.ExecutableSort;
import com.pratice.sortApp.util.StringUtil;

/*
    퀵 정렬
    - 시간복잡도 : O(NlogN)
    - 특징 : 특정 조건하에 성능이 급격하게 떨어진다. 재귀 미 사용시 난이도 급 상승
 */
public class QuickSortRightSide implements ExecutableSort {
    @Override
    public int[] run(int[] a) {
        quickSort_right(a, 0, a.length - 1);
        return a;
    }

    private void quickSort_right(int[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int pivot = partition(a, lo, hi);
        System.out.println("피벗 : " + a[pivot]);
        System.out.println(StringUtil.getLineByIntArray(a));

        quickSort_right(a, lo, pivot - 1);
        quickSort_right(a, pivot + 1, hi);
    }

    private int partition(int[] a, int left, int right) {
        int lo = left;
        int hi = right;

        int pivot = a[right];

        while (lo < hi) {

            while (a[lo] < pivot && lo < hi) {
                lo++;
            }

            while (a[hi] >= pivot && lo < hi) {
                hi--;
            }

            swap(a, lo, hi);
        }

        swap(a, right, hi);

        return hi;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
