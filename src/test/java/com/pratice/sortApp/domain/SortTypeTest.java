package com.pratice.sortApp.domain;

import com.pratice.sortApp.domain.SortType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortTypeTest {

    @Test
    void fromNum() {
        SortType mergeSort = SortType.MERGE_SORT;
        int mergeSortNum = mergeSort.getNum();

        SortType find = SortType.fromNum(mergeSortNum);

        assertEquals(mergeSortNum, find);
    }
}