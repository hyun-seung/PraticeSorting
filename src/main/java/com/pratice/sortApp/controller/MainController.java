package com.pratice.sortApp.controller;

import com.pratice.sortApp.domain.SortType;

import java.util.Arrays;
import java.util.Scanner;

import static com.pratice.sortApp.service.GenerateNumberService.generateRandomNumbers;

public class MainController {

    private static final String SEPERATE_LINE = "========================";
    private static final String WRONG_MESSAGE = "잘못된 값을 입력하였습니다. :( \n다시 입력해주세요.";
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        SortType sortType = getSortType();
        int[] intArray = getIntArray();
        sortType.getTask().run(intArray);
    }

    public SortType getSortType() {
        System.out.println();
        System.out.println(SEPERATE_LINE);
        System.out.println(SortType.getInfo());
        System.out.println(SEPERATE_LINE);

        System.out.println("정렬 방식을 고르세요 :)");
        String input = scanner.nextLine();

        SortType sortType;
        try {
            int num = Integer.parseInt(input);
            sortType = SortType.fromNum(num);
        } catch (Exception e) {
            System.out.println(WRONG_MESSAGE);
            return getSortType();
        }
        return sortType;
    }

    public int[] getIntArray() {
        System.out.println("숫자 배열을 입력해주세요. ' '로 구분해주시면 됩니다. ex) 1 11 111");
        System.out.println("입력하기 귀찮으실 경우 0을 입력해주세요. 자동으로 다섯 숫자를 만들겠습니다 ^ㅡ^ ");
        String input = scanner.nextLine();

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
}
