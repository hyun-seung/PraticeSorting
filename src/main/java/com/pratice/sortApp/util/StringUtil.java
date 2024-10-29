package com.pratice.sortApp.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtil {

    public static String getLineByIntArray(int[] a) {
        return Arrays.stream(a).mapToObj(v -> v + " ").collect(Collectors.joining("", "", "\n"));
    }
}
