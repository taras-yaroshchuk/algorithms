package com.sigma.test.service;

import com.sigma.test.TestAppContext;
import com.sigma.test.exception.AlgorithmRunnerException;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrieWhitespacesServiceTest {

    private static TestAppContext appContext;

    @BeforeClass
    public static void init() {
        appContext = new TestAppContext();
    }


    @Test
    public void testRestoringWhitespaces() throws AlgorithmRunnerException {
        WhitespacesRestoreService whitespacesService = appContext.getWhitespacesService();

        String inputFile = "src/test/resources/restore_whitespaces.csv";
        String dictionaryFile = "src/test/resources/restore_whitespaces_dictionary.csv";
        String actual = whitespacesService.restoreWhitespaces(inputFile, dictionaryFile).get();

        assertEquals("карл у клары украл кораллы", actual);

    }
}
