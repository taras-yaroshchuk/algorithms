package com.sigma.test;


import com.google.common.collect.Maps;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Class that represents node of Trie data structure.
 * If isFinal is true, that means that "value" is completed word.
 * Node could be final and have childs in the same time. For example, "leg" and "legend"
 */
@Getter
@Setter
public class TrieNode {
    private boolean isFinal;
    private final String value;
    private final Map<String, TrieNode> childNodes = Maps.newHashMap();

    TrieNode(boolean isFinal, String value) {
        this.isFinal = isFinal;
        this.value = value;
    }
}