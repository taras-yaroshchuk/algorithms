package com.sigma.test.strategy;

import com.google.common.collect.Lists;
import com.sigma.test.Trie;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class use Trie data structure to build a tree with all words combination.
 * First step of algorithm is building a classic Trie from dictionary.
 * Second step is iterating over each char of input string and checking if it exist in Trie.
 */
public class TrieWhitespacesStrategy implements RestoreWhitespacesStrategy {

    private static String WHITESPACE = " ";

    @Override
    public Optional<String> restoreWhiteSpaces(String input, List<String> dictionary) {
        Trie trie = buildTrie(dictionary);
        List<String> words = getWordsList(input, trie);
        return restoreWhitespaces(words);
    }

    /**
     * Get all words from single string input using Trie data structure
     *
     * @param input - string input without whitespaces
     * @param trie  - Trie data structure filled with dictionary words
     * @return list of all found words if any, empty list otherwise
     */
    private List<String> getWordsList(String input, Trie trie) {
        List<String> words = Lists.newArrayList();
        getWordsList(input, 0, 1, trie, words);
        return words;
    }

    /**
     * Get all words from single string input using Trie data structure
     *
     * @param input - string input without whitespaces
     * @param trie - Trie data structure filled with dictionary words
     * @param startIndex - start index of word that will be chacked in Trie
     * @param endIndex - end index of word that will be chacked in Trie
     * @return list of all found words if any, empty list otherwise
     */
    private boolean getWordsList(String input, int startIndex, int endIndex, Trie trie, List<String> words) {
        String currentKey = input.substring(startIndex, endIndex);

        return trie.get(currentKey)
                .map(node -> {
                    // If word from input matches with word from dictionary
                    if (node.isFinal()) {
                        //algorithm succeed
                        if (endIndex == input.length()) {
                            words.add(currentKey);
                            return true;
                        }
                        //start processing for next word
                        boolean finishFound = getWordsList(input, endIndex, endIndex + 1, trie, words);
                        if (finishFound) {
                            words.add(currentKey);
                            return true;
                        }
                    }
                    //start processing for current string + one more char
                    return getWordsList(input, startIndex, endIndex + 1, trie, words);
                })
                .orElse(false);
    }

    /**
     * If list is not empty, join words by whitespace char. Otherwise, return Optional.empty
     */
    private Optional<String> restoreWhitespaces(List<String> words) {
        if (words.isEmpty())
            return Optional.empty();
        else {
            Collections.reverse(words);
            return Optional.of(words.stream().collect(Collectors.joining(WHITESPACE)));
        }
    }

    /**
     * Build Trie woth dictionary words
     */
    private Trie buildTrie(List<String> words) {
        return Trie.builder()
                .addAllWords(words)
                .build();
    }
}
