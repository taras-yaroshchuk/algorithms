package com.sigma.test.strategy;

import java.util.*;

public class MergeSortingStrategy<V extends Comparable<V>> implements SortingStrategy<V> {


    @Override
    public List<V> sort(List<V> inputArray) {
        if (inputArray.size() == 0)
            return inputArray;

        //Temporary array that will be used while merging 2 subarrays
        List<V> temporaryArray = new ArrayList<>(inputArray);

        sort(inputArray, temporaryArray, 0, inputArray.size() - 1);
        return inputArray;
    }


    public void sort(List<V> inputArray, List<V> temporaryArray, int from, int to) {
        if (to <= from)
            return;
        int mid = from + (to - from) / 2;
        sort(inputArray, temporaryArray, from, mid);
        sort(inputArray, temporaryArray, mid + 1, to);
        merge(inputArray, temporaryArray, from, mid, to);
    }

    public void merge(List<V> inputArray, List<V> temporaryArray, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            temporaryArray.set(k, inputArray.get(k));
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                inputArray.set(k, temporaryArray.get(j++));
            else if (j > hi)
                inputArray.set(k, temporaryArray.get(i++));
            else if (temporaryArray.get(j).compareTo(temporaryArray.get(i)) > 0)
                inputArray.set(k, temporaryArray.get(j++));
            else
                inputArray.set(k, temporaryArray.get(i++));
        }

    }

}









