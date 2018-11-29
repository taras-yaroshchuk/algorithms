package com.sigma.test.strategy;

import com.google.common.collect.Lists;
import com.sigma.test.Trie;
import com.sigma.test.TrieNode;

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

    private List<String> getWordsList(String input, Trie trie) {
        List<String> words = Lists.newArrayList();
        getWordsList(input, 0, 1, trie, words);
        return words;
    }


    private boolean getWordsList(String input, int startIndex, int endIndex, Trie trie, List<String> words) {
        String currentKey = input.substring(startIndex, endIndex);

        Optional<TrieNode> currentNode = trie.get(currentKey);

        return currentNode.map(node -> {

            // If word from input matches with word from dictionary
            if (node.isFinal()) {

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

        }).orElse(false);
    }

    private Optional<String> restoreWhitespaces(List<String> words) {
        if (words.isEmpty())
            return Optional.empty();
        else {
            Collections.reverse(words);
            return Optional.of(words.stream().collect(Collectors.joining(WHITESPACE)));
        }
    }

    private Trie buildTrie(List<String> words) {
        return Trie.builder()
                .addAllWords(words)
                .build();
    }
}
