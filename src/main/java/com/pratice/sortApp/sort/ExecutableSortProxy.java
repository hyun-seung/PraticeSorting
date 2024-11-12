package com.pratice.sortApp.sort;

import com.pratice.sortApp.util.StringUtil;

public class ExecutableSortProxy implements ExecutableSort {

    private final ExecutableSort target;

    public ExecutableSortProxy(ExecutableSort target) {
        this.target = target;
    }

    @Override
    public int[] run(int[] a) {
        System.out.println("\n입력 배열 : " + StringUtil.getLineByIntArray(a));

        int[] result = target.run(a);

        System.out.println("최종 배열 : " + StringUtil.getLineByIntArray(result));
        return result;
    }
}
