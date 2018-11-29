package com.sigma.test;

import com.sigma.test.conf.AppContext;
import com.sigma.test.service.AlgorithmsExecutionService;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Log4j2
public class App {

    /**
     * Main class of App. Path to csv file with algorithms info should be passed as first program arg.
     * [optional] Output path could be passed through second arg
     */
    public static void main(String[] args) throws IOException {
        AppContext appContext = new AppContext();
        AlgorithmsExecutionService algorithmsExecutionService = appContext.getAlgorithmsExecutionService();

        log.info("Starting App for args: {}", args);

        validateArgs(args);
        String inputFile = args[0];

        List<String> lines = Files.readAllLines(Paths.get(inputFile));
        String outputFile = getOutputFile(inputFile, args);

        log.info("Processing {} lines", lines.size());
        algorithmsExecutionService.runAlgorithmsAndSaveResult(lines, outputFile);
        log.info("Processing succeed");
    }

    private static String getOutputFile(String inputFile, String[] args) {
        if (args.length < 2)
            return String.format("%s.output", inputFile);
        else
            return args[1];
    }

    private static void validateArgs(String[] args) {
        if (args.length < 1)
            throw new RuntimeException("Please specify path to csv file with info about algorithms that needs to be run.");
    }

}
