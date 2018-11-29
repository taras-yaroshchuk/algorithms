package com.sigma.test.strategy;

import com.sigma.test.TestAppContext;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MergeSortingStrategyTest {


    private static TestAppContext appContext;

    @BeforeClass
    public static void init() {
        appContext = new TestAppContext();
    }

    @Test
    public void testIntMergingSorting() {
        SortingStrategy<Integer> sortingStrategy = appContext.getIntMergeSortingStrategy();

        List<Integer> actualResult = sortingStrategy.sort(Arrays.asList(2, 5, 6, 7, 4, 3));
        List<Integer> expectedResult = Arrays.asList(7, 6, 5, 4, 3, 2);

        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testDoubleMergingSorting() {
        SortingStrategy<Double> sortingStrategy = appContext.getDoubleMergeSortingStrategy();

        List<Double> actualResult = sortingStrategy.sort(Arrays.asList(2.0, 5.0, 6.0, 7.0, 4.0, 3.0));
        List<Double> expectedResult = Arrays.asList(7.0, 6.0, 5.0, 4.0, 3.0, 2.0);

        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testStringMergingSorting() {
        SortingStrategy<String> sortingStrategy = appContext.getStringMergeSortingStrategy();

        List<String> actualResult = sortingStrategy.sort(Arrays.asList("Los Angeles", "San Francisco", "Las Vegas"));
        List<String> expectedResult = Arrays.asList("San Francisco", "Los Angeles", "Las Vegas");

        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult);
    }
}
