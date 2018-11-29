package com.sigma.test.service;

import com.sigma.test.TestAppContext;
import com.sigma.test.exception.AlgorithmRunnerException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class TestSortingService {


    private static TestAppContext appContext;

    @BeforeClass
    public static void init() {
        appContext = new TestAppContext();
    }

    @Test
    public void testIntMergingSorting() throws AlgorithmRunnerException {
        SortingService<Integer> sortingService = appContext.getSortingService();
        List<Integer> expectedResult = Arrays.asList(10, 5, 4, 3, 2, 1);

        List<Integer> actualResult = sortingService.sort("src/test/resources/tosort.csv");

        assertEquals(expectedResult, actualResult);
    }
}
