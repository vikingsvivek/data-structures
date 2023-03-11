package com.custom.datastructures;

public class Trie {

    TrieNode root = new TrieNode('1');
    class TrieNode{
        char val;
        TrieNode[] childern;
        boolean isTerminalNode;

        public TrieNode(char val){
            this.val = val;
            childern = new TrieNode[26];
            this.isTerminalNode=false;
        }
    }

    public void insert(String str){
        insertUtil(root,str, 0);
    }
    private void insertUtil(TrieNode node, String str, int i){
        if(i == str.length()){
            node.isTerminalNode = true;
            return;
        }
        char ch = str.charAt(i);
        //Assuming lowercase chars
        int index = ch - 'a';
        //No Match, then create new node for the char
        if(node.childern[index] == null){
            // create new node
            node.childern[index] =  new TrieNode(ch);

        }
        // recursion
        insertUtil(node.childern[index], str, i + 1);
    }

    public boolean search(String str){
        return searchUtil(root,str, 0);
    }

    private boolean searchUtil(TrieNode node,String str, int i){
        // reached of string
        if(i == str.length()){
            //based on terminal node
            return node.isTerminalNode;
        }
        char ch = str.charAt(i);
        //Assuming lowercase chars
        int index = ch - 'a';

        // if found match
        if(node.childern[index] != null){
            // MATCH
            return searchUtil(node.childern[index], str, i+1);
        }else {
            // NO match
            return false;
        }

    }

    public boolean prefixSearch(String str){
        return prefixSearchUtil(root,str,0);
    }

    private boolean prefixSearchUtil(TrieNode node,String str, int i){
        if(i == str.length())
            return true;
        char ch = str.charAt(i);
        //Assuming lowercase chars
        int index = ch - 'a';

        if(node.childern[index] != null){
            //Match
            return prefixSearchUtil(node.childern[index], str, i+1);
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println("Searching vivek: "+trie.search("vivek"));
        trie.insert("vivek");
        System.out.println("Searching vivek: "+trie.search("vivek"));
        System.out.println("Searching viv: "+trie.search("viv"));
        System.out.println("*** Prefix Search vive: "+ trie.prefixSearch("viv"));
        System.out.println("*** Prefix Search shym: "+ trie.prefixSearch("shym"));
        trie.insert("viv");
        System.out.println("Searching viv: "+trie.search("viv"));

        System.out.println(longestCommonPrefixUsingTrie(new String[]{"flower","flow","flight"}));
        System.out.println(longestCommonPrefixUsingTrie(new String[]{"race","job","cat"}));
    }

    //TODO: fix this implementation
    private static String longestCommonPrefixUsingTrie(String[] strs){
        //find smallest string, now try to find it in trie, if not found then reduce it to its substring
        //Step 1: find smallest string
        String smallest = strs[0];
        Trie trie = new Trie();

        for(String str: strs){
            if(str.length() < smallest.length() )
                smallest = str;
        }

        for(String str: strs){
            // if not smallest then insert to trie
            if(!str.equals(smallest)){
                System.out.println("inserting string: "+str);
                trie.insert(str);
            }

        }

        System.out.println("smallest string: "+smallest);
        int maxCommonLen = smallest.length();

        // untill we find the prefix in trie
        while(!trie.prefixSearch(smallest.substring(0, maxCommonLen))){
            maxCommonLen--;
        }

        return smallest.substring(0, maxCommonLen);
    }

}
