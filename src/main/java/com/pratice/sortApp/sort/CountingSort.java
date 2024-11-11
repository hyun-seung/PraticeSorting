package com.pratice.sortApp.sort;

import com.pratice.sortApp.util.StringUtil;

/*
    카운팅 정렬
    - 시간복잡도 : O(N)
    - 특징 : 수열의 길이보다 수의 범위가 극단적으로 크면 메모리가 엄청 낭비될 수 있다.
 */
public class CountingSort implements ExecutableSort {

    @Override
    public int[] run(int[] a) {
        System.out.println("\n입력 배열 : " + StringUtil.getLineByIntArray(a));

        countingSort(a);

        System.out.println("최종 배열 : " + StringUtil.getLineByIntArray(a));
        return a;
    }

    private void countingSort(int[] a) {
        int[] counting = new int[101];
        int[] result = new int[a.length];

        for (int i=0; i<a.length; i++) {
            counting[a[i]]++;
        }

        for (int i=1; i<counting.length; i++) {
            counting[i] += counting[i - 1];
        }

        for (int i=a.length - 1; i>=0; i--) {
            int value = a[i];
            counting[value]--;
            result[counting[value]] = value;
        }

        System.arraycopy(result, 0, a, 0, a.length);
    }
}
