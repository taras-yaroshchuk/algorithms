package com.sigma.test.service;

import com.sigma.test.exception.AlgorithmRunnerException;
import com.sigma.test.strategy.SortingStrategy;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.sigma.test.conf.AppContext.ROW_SEPARATOR;

@RequiredArgsConstructor
public abstract class SortingService<V extends Comparable<V>> {

    private static String CANT_READ_INPUT_MESSAGE = "Error while reading input array";

    private final SortingStrategy<V> sortingStrategy;

    abstract V fromString(String s);

    public List<V> sort(String inputPath) throws AlgorithmRunnerException {
        List<V> input = readInputFile(inputPath);
        return sortingStrategy.sort(input);
    }

    private List<V> readInputFile(String inputFile) throws AlgorithmRunnerException {
        try {
            return Files.readAllLines(Paths.get(inputFile))
                    .stream()
                    .flatMap(line -> Stream.of(line.split(ROW_SEPARATOR)))
                    .map(this::fromString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new AlgorithmRunnerException(CANT_READ_INPUT_MESSAGE, e);
        }
    }


}
