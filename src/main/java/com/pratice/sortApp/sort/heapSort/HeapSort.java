package com.pratice.sortApp.sort.heapSort;

import com.pratice.sortApp.sort.ExecutableSort;
import com.pratice.sortApp.util.StringUtil;

/*
    힙 정렬
    - 시간복잡도 : O(NlogN)
    - 특징 : 최악의 경우에도 O(NlogN)으로 유지, 힙의 특성상 부분 정렬을 할 때 효과적, 안정정렬 X
 */
public class HeapSort implements ExecutableSort {

    @Override
    public int[] run(int[] a) {
        heapSort(a, a.length);
        return a;
    }

    /*
        Left Child Node = index * 2 + 1
        Right Child Node = index * 2 + 2
        Parent Node = (index - 1) / 2
     */
    private void heapSort(int[] a, int size) {
        if (size < 2) {
            return;
        }

        // 가장 마지막 요소의 부모 인덱스
        int parentIdx = getParent(size - 1);

        for (int i=parentIdx; i>=0; i--) {
            heapify(a, i, size - 1);
        }

        System.out.println("1 : " + StringUtil.getLineByIntArray(a));

        for (int i = size - 1; i>0; i--) {
            swap(a, 0, i);
            heapify(a, 0, i-1);

            System.out.println("2 : " + StringUtil.getLineByIntArray(a));
        }

    }

    private int getParent(int child) {
        return (child - 1) / 2;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void heapify(int[] a, int parentIdx, int lastIdx) {
        int leftChildIdx, rightChildIdx, largestIdx;

        while ((parentIdx * 2) + 1 <= lastIdx) {
            leftChildIdx = 2 * parentIdx + 1;
            rightChildIdx = 2 * parentIdx + 2;
            largestIdx = parentIdx;

            if (a[leftChildIdx] > a[largestIdx]) {
                largestIdx = leftChildIdx;
            }

            if (rightChildIdx <= lastIdx && a[rightChildIdx] > a[largestIdx]) {
                largestIdx = rightChildIdx;
            }

            if (largestIdx != parentIdx) {
                swap(a, parentIdx, largestIdx);
                parentIdx = largestIdx;
            } else {
                return;
            }
        }
    }
}
