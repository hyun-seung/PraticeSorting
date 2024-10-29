package com.pratice.sortApp.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerateNumberServiceTest {

    @Test
    public void generateRandomNumbersTest() {
        int n = 5;

        int[] generateRandomNumbers = GenerateNumberService.generateRandomNumbers(n);

        assertEquals(n, generateRandomNumbers.length);
    }
}