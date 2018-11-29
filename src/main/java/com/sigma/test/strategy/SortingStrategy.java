package com.sigma.test.strategy;

import java.util.List;

public interface SortingStrategy<V extends Comparable<V>> {

    List<V> sort(List<V> input);
}
