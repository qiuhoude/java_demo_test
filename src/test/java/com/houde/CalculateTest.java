package com.houde;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Administrator on 2017/4/1 0001.
 */
@RunWith(Parameterized.class)
public class CalculateTest {
    private int expected;
    private int first;
    private int second;

    public CalculateTest(int expectedResult, int firstNumber, int secondNumber) {
        this.expected = expectedResult;
        this.first = firstNumber;
        this.second = secondNumber;
    }

    @Parameterized.Parameters
    public static Collection addedNumbers() {
        return Arrays.asList(new Integer[][]{{3, 1, 2}, {5, 2, 3},
                {7, 3, 4}, {9, 4, 5},});
    }

    @Test
    public void sum() {
        Calculate add = new Calculate();
        System.out.println("Addition with parameters : " + first + " and "
                + second);
        Assert.assertEquals(expected, add.sum(first, second));
    }

    static class Calculate {
        public int sum(int f, int s) {
            return f + s;
        }
    }
}
