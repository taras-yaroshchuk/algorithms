package com.sigma.test.service;

import com.sigma.test.strategy.SortingStrategy;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class IntegersSortingService extends SortingService<Integer> {

    public IntegersSortingService(SortingStrategy<Integer> sortingStrategy) {
        super(sortingStrategy);
    }

    @Override
    Integer fromString(String s) {
        return Integer.valueOf(s);
    }
}
