package com.sigma.test.strategy;


import com.google.common.collect.Lists;
import com.sigma.test.TestAppContext;
import com.sigma.test.exception.AlgorithmRunnerException;
import com.sigma.test.service.WhitespacesRestoreService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RestoreWhitespacesStrategyTest {

    private static TestAppContext appContext;

    @BeforeClass
    public static void init() {
        appContext = new TestAppContext();
    }


    @Test
    public void testRestoringWhitespacesSampleSentence() throws AlgorithmRunnerException {
        RestoreWhitespacesStrategy restoreWhitespacesStrategy = appContext.getRestoreWhitespacesStrategy();

        List<String> dictionary = Lists.newArrayList(
                "dog", "lazy", "jumps", "over", "the", "quick", "brown", "fox");
        String result = restoreWhitespacesStrategy.restoreWhiteSpaces("thequickbrownfoxjumpsoverthelazydog", dictionary).get();

        String expected = "the quick brown fox jumps over the lazy dog";
        assertEquals(expected, result);

    }

    @Test
    public void testRestoringWhitespacesDublicatesSentence() throws AlgorithmRunnerException {
        RestoreWhitespacesStrategy restoreWhitespacesStrategy = appContext.getRestoreWhitespacesStrategy();

        List<String> dictionary = Lists.newArrayList(
                "a", "abc", "bb", "bbb", "cccc", "ddddd");
        String result = restoreWhitespacesStrategy.restoreWhiteSpaces("aabcdddddbbbabb", dictionary).get();

        String expected = "a abc ddddd bbb a bb";
        assertEquals(expected, result);

    }

    @Test
    public void testRestoringWhitespacesInvalidSentence() throws AlgorithmRunnerException {
        RestoreWhitespacesStrategy restoreWhitespacesStrategy = appContext.getRestoreWhitespacesStrategy();

        List<String> dictionary = Lists.newArrayList(
                "a", "b");
        Optional<String> result = restoreWhitespacesStrategy.restoreWhiteSpaces("qweadsdas", dictionary);

        assertTrue(!result.isPresent());
    }
}
