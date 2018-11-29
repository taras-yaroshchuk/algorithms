package com.sigma.test.conf;

import com.sigma.test.service.AlgorithmsExecutionService;
import com.sigma.test.service.IntegersSortingService;
import com.sigma.test.service.WhitespacesRestoreService;
import com.sigma.test.service.SortingService;
import com.sigma.test.strategy.MergeSortingStrategy;
import com.sigma.test.strategy.TrieWhitespacesStrategy;

/**
 * Simple IoC container for console app.
 * No frameworks (such Spring) is required for such small apps.
 */
public class AppContext {

    public static String ROW_SEPARATOR = ",";


    public SortingService<Integer> getSortingService() {
        return new IntegersSortingService(new MergeSortingStrategy<>());
    }

    public WhitespacesRestoreService getWhitespacesService() {
        return new WhitespacesRestoreService(new TrieWhitespacesStrategy());
    }

    public AlgorithmsExecutionService getAlgorithmsExecutionService() {
        return new AlgorithmsExecutionService(getSortingService(), getWhitespacesService());
    }

}
