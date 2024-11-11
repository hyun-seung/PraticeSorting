package com.pratice.sortApp.domain;

import com.pratice.sortApp.sort.*;
import com.pratice.sortApp.sort.heapSort.HeapSort;
import com.pratice.sortApp.sort.mergeSort.MergeSortButtomUP;
import com.pratice.sortApp.sort.quickSort.QuickSortRightSide;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum SortType {

    MERGE_SORT("병합 정렬", "O(NlogN)", new MergeSortButtomUP()),
    BUBBLE_SORT("버블 정렬", "O(N^2)", new BubbleSort()),
    INSERTION_SORT("삽입 정렬", "O(N^2)", new InsertionSort()),
    SELECTION_SORT("선택 정렬", "O(N^2)", new SelectionSort()),
    QUICK_SORT("퀵 정렬", "O(NlogN)", new QuickSortRightSide()),
    HEAP_SORT("힙 정렬", "O(NlogN)", new HeapSort()),
    BINARY_INSERTION_SORT("이진 삽입 정렬", "O(N^2)", new BinaryInsertionSort()),
    TIM_SORT("팀 정렬", "O(NlogN)", new TimSort()),
    COUNTING_SORT("카운팅 정렬", "O(N)", new CountingSort());

    private final int num;
    private final String name;
    private final String time;
    private final ExecutableSort task;

    SortType(String name, String time, ExecutableSort task) {
        this.num = this.ordinal() + 1;
        this.name = name;
        this.time = time;
        this.task = task;
    }

    public static SortType fromNum(int num) {
        return Arrays.stream(SortType.values())
                .filter(sortType -> sortType.getNum() == num)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 num : " + num));
    }

    public static String getInfo() {
        return "정렬 알고리즘 목록 \n\n" +
                Arrays.stream(SortType.values())
                        .map(sortType -> String.format("%d : %s (시간 복잡도 : %s)",
                                sortType.getNum(),
                                sortType.getName(),
                                sortType.getTime()))
                        .collect(Collectors.joining("\n"));
    }

    public ExecutableSort getTask() {
        return task;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
}
