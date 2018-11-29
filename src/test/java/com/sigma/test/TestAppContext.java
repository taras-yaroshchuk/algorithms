package com.sigma.test;

import com.sigma.test.service.IntegersSortingService;
import com.sigma.test.service.SortingService;
import com.sigma.test.service.WhitespacesRestoreService;
import com.sigma.test.strategy.MergeSortingStrategy;
import com.sigma.test.strategy.RestoreWhitespacesStrategy;
import com.sigma.test.strategy.SortingStrategy;
import com.sigma.test.strategy.TrieWhitespacesStrategy;

public class TestAppContext {

    public SortingStrategy<Integer> getIntMergeSortingStrategy() {
        return new MergeSortingStrategy<>();
    }

    public SortingStrategy<Double> getDoubleMergeSortingStrategy() {
        return new MergeSortingStrategy<>();
    }

    public SortingStrategy<String> getStringMergeSortingStrategy() {
        return new MergeSortingStrategy<>();
    }


    public SortingService<Integer> getSortingService() {
        return new IntegersSortingService(getIntMergeSortingStrategy());
    }

    public WhitespacesRestoreService getWhitespacesService() {
        return new WhitespacesRestoreService(getRestoreWhitespacesStrategy());
    }

    public RestoreWhitespacesStrategy getRestoreWhitespacesStrategy() {
        return new TrieWhitespacesStrategy();
    }

}
