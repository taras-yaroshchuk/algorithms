package com.sigma.test.service;

import com.sigma.test.Algorithm;
import com.sigma.test.exception.AlgorithmRunnerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.sigma.test.conf.AppContext.ROW_SEPARATOR;

@Log4j2
@RequiredArgsConstructor
public class AlgorithmsExecutionService {

    private static String UNSUPPORTED_ALGORITHMS_MESSAGE = "Algorithm is not supported yet!";
    private static String NO_INPUT_PATH_DEFINED = "Path to inout file should be passed as first program arg";
    private static String NO_DICTIONARY_PATH_DEFINED = "Path to file with dictionary should be passed as second program arg";
    private static String CANT_RESTORE_SPACES_MESSAGE = "Can't restore spaces. " +
            "Unknown words were found in input string. Please, check the dictionary";

    private final SortingService<Integer> sortingService;
    private final WhitespacesRestoreService whitespacesRestoreService;


    /**
     * Parse each line of input csv file, run algorithm and store result in single file.
     * @param algInfoLines - list of input lines that contain algorithm definition and config files pathes.
     * @param outputFile - is used to storing results
     */
    public void runAlgorithmsAndSaveResult(List<String> algInfoLines, String outputFile) throws IOException {
        log.info("Trying to parse {} lines and save result to {}", algInfoLines.size(), outputFile);
        List<String> results = algInfoLines.stream()
                .map(this::parseAndRunAlgorithm)
                .collect(Collectors.toList());

        log.info("Saving result to file {}", outputFile);
        Files.write(Paths.get(outputFile), results);
    }

    /**
     * Parse individual row of algorithms info file, read input and run algorithm processing on it
     */
    private String parseAndRunAlgorithm(String row) {
        String[] configValues = row.split(ROW_SEPARATOR);
        Algorithm algorithm = Algorithm.parseAlgorithm(configValues[0]);

        if (configValues.length < 2)
            return formatResultString(algorithm, NO_INPUT_PATH_DEFINED);

        String inputPath = configValues[1];

        String result = processAlgorithm(algorithm, inputPath, configValues)
                .orElseGet(() -> UNSUPPORTED_ALGORITHMS_MESSAGE);

        return formatResultString(algorithm, result);
    }

    /**
     * Simple format of output file. Could be changed in fute
     */
    private String formatResultString(Algorithm algorithm, String result) {
        return String.format("%s,[%s]", algorithm.toString(), result);
    }

    /**
     * Run algorithm based on algorithm param
     * @param inputPath - path of file that contains inout data
     * @return result of algorithm run
     */
    private Optional<String> processAlgorithm(Algorithm algorithm, String inputPath, String[] configValues) {
        switch (algorithm) {
            case SORT:
                return Optional.ofNullable(processSortingAndPrepareResult(inputPath));
            case WHITESPACES:
                return Optional.ofNullable(processRestoringWhitespaces(inputPath, configValues));
            case UNSUPPORTED:
                return Optional.of(UNSUPPORTED_ALGORITHMS_MESSAGE);
            default:
                return Optional.empty();
        }
    }

    /**
     * Read dictionary file and start processing of RestoringWhitespaces
     * @return result of algorithm's work
     */
    private String processRestoringWhitespaces(String inputPath, String[] configValues) {
        if (configValues.length < 3)
            return NO_DICTIONARY_PATH_DEFINED;
        try {
            return whitespacesRestoreService.restoreWhitespaces(inputPath, configValues[2])
                    .orElseGet(() -> CANT_RESTORE_SPACES_MESSAGE);
        } catch (AlgorithmRunnerException e) {
            return e.getMessage();
        }
    }

    /**
     * Read input array and sort it
     * @return result of algorithm's work
     */
    private String processSortingAndPrepareResult(String inputPath) {
        try {
            return sortingService.sort(inputPath)
                    .stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(ROW_SEPARATOR));
        } catch (AlgorithmRunnerException e) {
            return e.getMessage();
        }
    }

}
