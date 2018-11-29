package com.sigma.test.strategy;

import com.google.common.collect.Lists;
import com.sigma.test.TrieNode;
import com.sigma.test.Trie;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;


public class TrieTest {

    @Test
    public void testPutOneCharWord() {
        ArrayList<String> words = Lists.newArrayList("q");
        Trie trie = Trie.builder()
                .addAllWords(words)
                .build();

        Optional<TrieNode> simpleWord = trie.get("q");

        assertTrue(simpleWord.isPresent());
    }

    @Test
    public void testGetNotExistWord() {
        ArrayList<String> words = Lists.newArrayList("qwerty", "q", "qwe", "asdf");
        Trie trie = Trie.builder()
                .addAllWords(words)
                .build();

        Optional<TrieNode> notExist = trie.get("notexist");

        assertTrue(!notExist.isPresent());
    }

    @Test
    public void testGetValidWord() {
        ArrayList<String> words = Lists.newArrayList("qwerty", "q", "qwe", "asdf");
        Trie trie = Trie.builder()
                .addAllWords(words)
                .build();

        Optional<TrieNode> validWord = trie.get("qwe");

        assertTrue(validWord.isPresent());
        assertEquals("qwe", validWord.get().getValue());
    }


    @Test
    public void testGetSubword() {
        ArrayList<String> words = Lists.newArrayList("qwerty", "q", "qwe", "asdf");
        Trie trie = Trie.builder()
                .addAllWords(words)
                .build();

        Optional<TrieNode> subword = trie.get("qw");

        assertTrue(subword.isPresent());
        assertFalse(subword.get().isFinal());
    }


    @Test
    public void testPutNewWord() {
        ArrayList<String> words = Lists.newArrayList("qwerty", "q", "qwe", "asdf");
        Trie trie = Trie.builder()
                .addAllWords(words)
                .build();

        Optional<TrieNode> notExistYet = trie.get("notYet");
        trie.put("notYet");
        Optional<TrieNode> justAdded = trie.get("notYet");


        assertTrue(!notExistYet.isPresent());
        assertTrue(justAdded.isPresent());
    }

}
