package com.pratice.sortApp.controller;

import com.pratice.sortApp.domain.SortType;
import com.pratice.sortApp.sort.ExecutableSortProxy;

import java.util.Arrays;
import java.util.Scanner;

import static com.pratice.sortApp.service.GenerateNumberService.generateRandomNumbers;

public class MainController {
    
    private static final String SELECT_SORT_TYPE = "정렬 방식을 고르세요 :)";
    private static final String WRITE_INT_ARRAY = "숫자 배열을 입력해주세요. ' '로 구분해주시면 됩니다. ex) 1 11 111";
    private static final String SUGGEST_RANDOM = "입력하기 귀찮으실 경우 0을 입력해주세요. 자동으로 다섯 숫자를 만들겠습니다 ^ㅡ^ ";
    private static final String SEPERATE_LINE = "========================";
    private static final String WRONG_MESSAGE = "잘못된 값을 입력하였습니다. :( \n다시 입력해주세요.";
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        SortType sortType = getSortType();
        int[] intArray = getIntArray();

        ExecutableSortProxy executableSortProxy = new ExecutableSortProxy(sortType.getTask());
        executableSortProxy.run(intArray);
    }

    public SortType getSortType() {
        try {
            int num = Integer.parseInt(scanSortType());
            return SortType.fromNum(num);
        } catch (Exception e) {
            System.out.println(WRONG_MESSAGE);
            return getSortType();
        }
    }
    
    private String scanSortType() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(SEPERATE_LINE);
        sb.append(SortType.getInfo());
        sb.append(SEPERATE_LINE);
        sb.append(SELECT_SORT_TYPE);
        System.out.println(sb);

        return scanner.nextLine();
    }

    public int[] getIntArray() {
        String input = scanIntArray();

        try {
            if (input.trim().equals("0")) {
                return generateRandomNumbers(5);
            }

            return Arrays.stream(input.split("\\s+"))
//                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (Exception e) {
            System.out.println(WRONG_MESSAGE);
            return getIntArray();
        }
    }

    private String scanIntArray() {
        StringBuilder sb = new StringBuilder();
        sb.append(WRITE_INT_ARRAY);
        sb.append(SUGGEST_RANDOM);
        sb.append("\n");
        System.out.println(sb);

        return scanner.nextLine();
    }
}
