package com.sigma.test.service;

import com.sigma.test.exception.AlgorithmRunnerException;
import com.sigma.test.strategy.RestoreWhitespacesStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class WhitespacesRestoreService {

    private static String CANT_READ_DICTIONARY_MESSAGE = "Error while reading dictionary file";
    private static String CANT_READ_INPUT_ARRAY = "Error while reading input array.";

    private final RestoreWhitespacesStrategy restoreWhitespacesStrategy;

    public Optional<String> restoreWhitespaces(String inputPath, String wordsDictionaryPath) throws AlgorithmRunnerException {
        String inputString = readInput(inputPath);
        List<String> words = readWordsDictionary(wordsDictionaryPath);

        return restoreWhitespacesStrategy.restoreWhiteSpaces(inputString, words);
    }

    private String readInput(String inputPath) throws AlgorithmRunnerException {
        try {
            return Files.readAllLines(Paths.get(inputPath)).get(0);
        } catch (IOException e) {
            throw new AlgorithmRunnerException(CANT_READ_INPUT_ARRAY, e);
        }
    }

    private List<String> readWordsDictionary(String inputPath) throws AlgorithmRunnerException {
        try {
            return Files.readAllLines(Paths.get(inputPath));
        } catch (IOException e) {
            throw new AlgorithmRunnerException(CANT_READ_DICTIONARY_MESSAGE, e);
        }
    }

}
