package com.pratice.sortApp.sort;

import com.pratice.sortApp.util.StringUtil;

/*
    버블정렬
    - 시간복잡도 : O(N^2)
        - 최선의 경우 : O(N)
    - 특징 : 추가적인 메모리 소비가 적음. 안정정렬 가능.
 */
public class BubbleSort implements ExecutableSort {

    @Override
    public int[] run(int[] a) {
        bubbleSort(a, a.length);
        return a;
    }

    private void bubbleSort(int[] a, int size) {
        for (int i = 1; i < size; i++) {
            boolean swapped = false;

            for (int j = 0; j < size - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    swapped = true;
                }

                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < size; k++) {
                    sb.append(a[k] + " ");
                }
                System.out.println(sb.toString());
            }

            System.out.println();
            if (swapped == false) {
                break;
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
