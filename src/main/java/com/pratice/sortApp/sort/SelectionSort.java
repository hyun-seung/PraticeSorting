package com.pratice.sortApp.sort;

import com.pratice.sortApp.util.StringUtil;

/*
    선택정렬
    - 시간복잡도 : O(N^2)
    - 특징 : 추가적인 메모리 소비가 적음. 안정 정렬이 아님.
 */
public class SelectionSort implements ExecutableSort {

    @Override
    public int[] run(int[] a) {
        System.out.println("\n입력 배열 : " + StringUtil.getLineByIntArray(a));

        selectionSort(a, a.length);

        System.out.println("최종 배열 : " + StringUtil.getLineByIntArray(a));
        return a;
    }

    private void selectionSort(int[] a, int size) {
        for (int i=0; i<size-1; i++) {
            int min_index = i;

            for (int j=i+1; j<size; j++) {
                if (a[j] < a[min_index]) {
                    min_index = j;
                }
            }

            swap(a, min_index, i);

            StringBuilder sb = new StringBuilder();
            for (int k=0; k<size; k++) {
                sb.append(a[k] + " ");
            }
            sb.append("\n");
            System.out.println(sb.toString());
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
