package com.pratice.sortApp.sort;

import com.pratice.sortApp.util.StringUtil;

/*
    삽입정렬
    - 시간복잡도 : O(N^2)
        - 최선의 경우 : O(N)
    - 특징 : 추가적인 메모리 소비가 적음. 안정정렬 가능. 역순에 가까울수록 매우 비효율적. 데이터의 상태에 따라 성능 편차가 매우 크다.
 */
public class InsertionSort implements ExecutableSort {

    @Override
    public void run(int[] a) {
        System.out.println("\n입력 배열 : " + StringUtil.getLineByIntArray(a));

        insertionSort(a, a.length);

        System.out.println("최종 배열 : " + StringUtil.getLineByIntArray(a));
    }

    private void insertionSort(int[] a, int size) {
        for (int i=1; i<size; i++) {
            int target = a[i];
            System.out.println("TARGET : " + target);

            int j = i-1;

            while (j >= 0 && target < a[j]) {
                a[j+1] = a[j];
                j--;
            }

            a[j+1] = target;

            StringBuilder sb = new StringBuilder();
            for (int k=0; k<size; k++) {
                sb.append(a[k] + " " );
            }
            sb.append("\n");
            System.out.println(sb);
        }
    }
}
