package com.custom.datastructures.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class SearchSuggestionSystem {
    //https://leetcode.com/problems/search-suggestions-system/

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        //return bruteforce(products, searchWord);
        //return usingTrie(products, searchWord);
        //return usingBinarySearch(products, searchWord);
        return usingBinarySearchApproach2(products, searchWord);
    }

    private List<List<String>> usingBinarySearchApproach2(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        TreeMap<String, Integer> map = new TreeMap<>();
        //sort array
        Arrays.sort(products);
        List<String> productsList = Arrays.asList(products);

        //product to its index in sorted array map
        for (int i = 0; i < products.length; i++) {
            map.put(products[i], i);
        }

        String key = "";
        for (char c : searchWord.toCharArray()) {
            key += c;
            String ceiling = map.ceilingKey(key);
            String floor = map.floorKey(key + "~");
            if (ceiling == null || floor == null)
                break;
            //get entries from ceiling to ceiling+2 OR ceiling to floor i.e. ceiling represent the most matching, floor represent least matching
            List<String > suggestions = productsList.subList(map.get(ceiling),
                    Math.min(map.get(ceiling) + 3, map.get(floor) + 1));

            res.add(suggestions);
        }

        //if not enough results added then add empty suggestions
        while (res.size() < searchWord.length())
            res.add(new ArrayList<>());

        return res;
    }

    List<List<String>> usingBinarySearch(String[] products, String searchWord) {
        //Sort products
        Arrays.sort(products);

        List<List<String>> res = new ArrayList<>();
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < searchWord.length(); i++) {
            String prefix = strb.append(searchWord.charAt(i)).toString();

            int idx = Arrays.binarySearch(products, prefix);

            if (idx < 0) {
                //if not found then, search on one index greater
                idx = -idx - 1;
            }
            List<String> suggestions = new ArrayList<>();
            int j = idx;
            // add next 3 matching words starting from idx where we found the prefix max
            while (suggestions.size() < 3 && j < products.length && products[j].startsWith(prefix)) {
                suggestions.add(products[j]);
                j++;
            }

            //add suggestions to ans
            res.add(suggestions);
        }
        return res;
    }

    private List<List<String>> usingTrie(String[] products, String searchWord){
        TrieNode root = new TrieNode();
        // Insert All products in Trie
        for(String product: products){
            insert(root, product);
        }

        List<List<String>>  results = new ArrayList<>();
        StringBuilder strb = new StringBuilder();
        //for every prefix substring of searchWord, get all suggestions from Trie in DFS mode

        TrieNode curr = root;
        for(int i = 0; i< searchWord.length();i++){
            int index = searchWord.charAt(i) - 'a';
            strb.append(searchWord.charAt(i));

            List<String> suggestions = new ArrayList<>();
            // if no child found for this char
            if(curr == null || curr.children[index] == null){
                curr = null;
            }else{
                //DFS to find suggestions
                DFSOnTrie(curr.children[index], suggestions);
                curr = curr.children[index];
            }

            //add suggestion to results
            results.add(suggestions);
        }
        return results;
    }

    void DFSOnTrie(TrieNode node, List<String> suggestions){
        if(suggestions.size() >=3 || node == null)
            return;
        //if its a word end, then add that word
        if(node.isTerminal){
            suggestions.add(node.word);
        }
        // recursve call to all child of curr node
        for(int  i = 0; i<=25; i++){
            if(node.children[i] != null)
                DFSOnTrie(node.children[i],suggestions);
        }
    }

    void insert(TrieNode root, String word){
        TrieNode curr = root;
        for(int i = 0;i< word.length();i++){
            int index = word.charAt(i)-'a';
            // if no child found for this char
            if(curr.children[index] == null)
                curr.children[index] = new TrieNode();
            //move the pointer
            curr = curr.children[index];
        }

        curr.word = word;
        curr.isTerminal = true;
    }

    class TrieNode {
        boolean isTerminal;
        TrieNode[] children;
        String word;
        TrieNode(){
            children = new TrieNode[26];
        }
    }

    // O(N*M*M + M*LOG M) time, O(N) space where M is length of product array, N is prefix length
    private List<List<String>> bruteforce(String [] products, String search){
        //Sort array
        Arrays.sort(products);
        List<List<String>> results = new ArrayList<>();

        StringBuilder strb = new StringBuilder();
        //O(M*M*N) time
        for(int i = 0; i< search.length();i++){
            strb.append(search.charAt(i));
            String prefix = strb.toString();
            //iterate over all words and see if that matches the current prefix

            List<String> suggestedWords = new ArrayList<>();
            //O(M*N)
            for(String word: products){
                // check if matches prefix
                if(word.startsWith(prefix) ){
                    suggestedWords.add(word);
                }
                // check if already found 3 suggestions
                if(suggestedWords.size() == 3){
                    break;
                }
            }
            results.add(suggestedWords);
        }
        return results;
    }
}
