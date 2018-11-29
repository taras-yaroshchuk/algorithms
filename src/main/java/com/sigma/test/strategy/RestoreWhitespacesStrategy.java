package com.sigma.test.strategy;

import java.util.List;
import java.util.Optional;

public interface RestoreWhitespacesStrategy {

    Optional<String> restoreWhiteSpaces(String input, List<String> words);
}
