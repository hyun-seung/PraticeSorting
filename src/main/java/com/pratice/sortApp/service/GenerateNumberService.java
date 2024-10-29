package com.pratice.sortApp.service;

import java.util.Random;
import java.util.stream.IntStream;

public class GenerateNumberService {

    public static int[] generateRandomNumbers(int num) {
        Random random = new Random(System.currentTimeMillis());
        return IntStream.range(0, num).map(i -> random.nextInt(100) + 1).toArray();
    }
}
