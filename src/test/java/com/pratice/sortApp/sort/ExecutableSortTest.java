package com.pratice.sortApp.sort;

import com.pratice.sortApp.sort.heapSort.HeapSort;
import com.pratice.sortApp.sort.heapSort.HeapSortRecursion;
import com.pratice.sortApp.sort.mergeSort.MergeSortButtomUP;
import com.pratice.sortApp.sort.mergeSort.MergeSortTopDown;
import com.pratice.sortApp.sort.quickSort.QuickSortLeftSide;
import com.pratice.sortApp.sort.quickSort.QuickSortMid;
import com.pratice.sortApp.sort.quickSort.QuickSortRightSide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ExecutableSortTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private PrintStream originOut;

    @BeforeEach
    public void setUp() {
        originOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originOut);
    }

    static Stream<ExecutableSort> provideAlgorithms() {
        return Stream.of(
                new MergeSortTopDown(),
                new MergeSortButtomUP(),
                new BubbleSort(),
                new InsertionSort(),
                new SelectionSort(),
                new QuickSortLeftSide(),
                new QuickSortMid(),
                new QuickSortRightSide(),
                new HeapSortRecursion(),
                new HeapSort()
        );
    }

    @ParameterizedTest
    @MethodSource("provideAlgorithms")
    void testSorting(ExecutableSort algorithm) {
        int[] target = {5, 3, 8, 1, 2};
        int[] expect = {1, 2, 3, 5, 8};

        assertArrayEquals(expect, algorithm.run(target));
    }

}