package com.sigma.test;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

/**
 * Trie data structure with few modifications:
 * 1. "get" method return node even if its not final (even if its not a whole word, but subword).
 * 2. Classic Trie use array of child nodes for each node, but it require to init N*256 arrays, where N - tree depth.
 * In order to save memory this implementation use Map for storing link to children nodes.
 *
 */
public class Trie {
    private TrieNode root = new TrieNode(false, null);

    private Trie() {}

    public static TrieBuilder builder() {
        return new TrieBuilder();
    }

    /**
     * Add new key to Trie
     */
    public void put(String key) {
        put(root, key, 1);
    }


    /**
     * Return node from Trie if exist, Optional.empty() otherwise
     * @param key - word or subword that needs to be found
     */
    public Optional<TrieNode> get(String key) {
        return get(root, key, 1);
    }

    /**
     * Add new key to Trie
     * @param trieNode - current Trie node
     * @param key - key that needs to be added
     * @param charIndex - current index of char that is being processed
     */
    private void put(TrieNode trieNode, String key, int charIndex) {
        String currentKey = key.substring(0, charIndex);

        TrieNode nextTrieNode = trieNode.getChildNodes().get(currentKey);

        boolean isFinal = charIndex == key.chars().count();

        if (nextTrieNode == null)
            nextTrieNode = new TrieNode(isFinal, currentKey);

        if (isFinal)
            nextTrieNode.setFinal(isFinal);

        trieNode.getChildNodes().put(currentKey, nextTrieNode);

        if (!isFinal)
            put(nextTrieNode, key, ++charIndex);

    }

    /**
     * Return node from Trie if exist, Optional.empty() otherwise
     * @param trieNode - current Trie node
     * @param key - word or subword that needs to be found
     * @param charIndex - current index of char that is being processed
     */
    private Optional<TrieNode> get(TrieNode trieNode, String key, int charIndex) {
        String currentKey = key.substring(0, charIndex);
        TrieNode nextTrieNode = trieNode.getChildNodes().get(currentKey);

        if (nextTrieNode == null)
            return Optional.empty();
        else {
            if (nextTrieNode.getValue().equals(key))
                return Optional.of(nextTrieNode);
            else
                return get(nextTrieNode, key, ++charIndex);
        }
    }

    /**
     * Builder class for Trie data structure.
     */
    public static class TrieBuilder {

        private List<String> words = Lists.newArrayList();

        public TrieBuilder addAllWords(List<String> wordsToAdd) {
            words.addAll(wordsToAdd);
            return this;
        }

        public Trie build() {
            Trie trie = new Trie();
            words.forEach(trie::put);
            return trie;
        }

    }

}
