package com.pratice.sortApp.domain;

import com.pratice.sortApp.sort.ExecutableSort;
import com.pratice.sortApp.sort.BubbleSort;
import com.pratice.sortApp.sort.InsertionSort;
import com.pratice.sortApp.sort.SelectionSort;
import com.pratice.sortApp.sort.mergeSort.MergeSortButtomUP;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum SortType {

    MERGE_SORT("병합 정렬", "O(NlogN)", new MergeSortButtomUP()),
    BUBBLE_SORT("버블 정렬", "O(N^2)", new BubbleSort()),
    INSERTION_SORT("삽입 정렬", "O(N^2)", new InsertionSort()),
    SELECTION_SORT("선택 정렬", "O(N^2)", new SelectionSort());

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
