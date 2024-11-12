package com.pratice.sortApp.sort.mergeSort;

import com.pratice.sortApp.sort.ExecutableSort;
import com.pratice.sortApp.util.StringUtil;

/*
    병합정렬
    - 시간복잡도 : O(NlogN)
        -> 비교작업 (O(N)) x 트리의 높이 (O(logN)
    - 특징 : 정렬과정에서 추가적인 보조 배열 공간을 사용하기 때문에 메모리 사용량 높음.
 */
public class MergeSortTopDown implements ExecutableSort {

    private int[] sorted;

    @Override
    public int[] run(int[] a) {
        sorted = new int[a.length];
        mergeSort(a, 0, a.length - 1);

        sorted = null;

        return a;
    }

    private void mergeSort(int[] a, int left, int right) {
        if (left == right) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSort(a, left, mid);
        mergeSort(a, mid+1, right);

        merge(a, left, mid, right);
    }

    private void merge(int[] a, int left, int mid, int right) {
        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            sb.append(a[i] + " ");
        }
        System.out.println("원본 : " + sb.toString());

        // 왼쪽 부분리스트 시작점
        int l = left;
        // 오른쪽 부분리스트 시작점
        int r = mid + 1;

        int idx = left;

        while (l <= mid && r <= right) {
            if (a[l] <= a[r]) {
                sorted[idx] = a[l];
                idx += 1;
                l += 1;
            } else {
                sorted[idx] = a[r];
                idx += 1;
                r += 1;
            }
        }

        if (l > mid) {
            while (r <= right) {
                sorted[idx] = a[r];
                idx += 1;
                r += 1;
            }
        } else {
            while (l <= mid) {
                sorted[idx] = a[l];
                idx += 1;
                l += 1;
            }
        }

        sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            a[i] = sorted[i];
            sb.append(a[i] + " ");
        }
        System.out.println("정렬 후 : " + sb.toString());
        System.out.println();
    }
}
