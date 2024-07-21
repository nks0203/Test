package com.jpmc.test.fix.cache;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    static class TrieNode {
        Map<Byte, TrieNode> children;
        String value;

        public TrieNode() {
            children = new HashMap<>();
            value = null;
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void put(String key, String value) {
        TrieNode current = root;
        for (byte c : key.getBytes()) {
            current = current.children.computeIfAbsent(c, v -> new TrieNode());
        }
        current.value = value;
    }

    public void put(String key, String value,Map<String,String> validValuesMap) {
        TrieNode current = root;
        for (byte c : key.getBytes()) {
            current = current.children.computeIfAbsent(c, v -> new TrieNode());
        }
        current.value = value;
    }
    public String get(byte[] bytes,int from, int to) {
        TrieNode current = root;
        for (;from<to;from++) {
            current = current.children.get(bytes[from]);
            if (current == null) {
                return null; // no data for key found
            }
        }
        return current.value; // Return value
    }
}
