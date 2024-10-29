package com.pratice.sortApp.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilTest {

    @Test
    public void printIntArrayTest1() {
        int[] given = new int[] {1, 2, 3, 4, 5};

        String real = StringUtil.getLineByIntArray(given);

        String expected = "1 2 3 4 5 \n";

        assertEquals(expected, real);
    }

    @Test
    public void printIntArrayTest2() {
        int[] given = new int[] {};

        String real = StringUtil.getLineByIntArray(given);

        String expected = "\n";

        assertEquals(expected, real);
    }

    @Test
    public void printIntArrayTest3() {
        int[] given = new int[] {99};

        String real = StringUtil.getLineByIntArray(given);

        String expected = "99 \n";

        assertEquals(expected, real);
    }
}