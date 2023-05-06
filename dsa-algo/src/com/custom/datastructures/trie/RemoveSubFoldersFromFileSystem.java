package com.custom.datastructures.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveSubFoldersFromFileSystem {
    public static void main(String[] args) {
        System.out.println(removeSubfolders(new String[]{"/a","/a/b","/c/d","/c/d/e","/c/f"}));
        System.out.println(removeSubfolders(new String[]{"/a","/a/b/c","/a/b/d"}));
        System.out.println(removeSubfolders(new String[]{"/a/b/c","/a/b/ca","/a/b/d"}));
        System.out.println(removeSubfolders(new String[]{"/a/b", "/a/c/d","/c/d","/c/d/e","/c/f"}));
    }
    //Problem: https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/
    public static List<String> removeSubfolders(String[] folder) {
       // return bruteforce(folder);
       return usingTrie(folder);
    }

    //O(N*N*L) where N is folder arr leng, and L is max length of string in folder arr
    private static List<String> bruteforce(String[] folders){
        //Lexicographical sort
        Arrays.sort(folders);

        List<String> ans = new ArrayList<>();
        for(int i = 0; i< folders.length;i++){
            if(folders[i] != null){
                //add the prefix folder to ans
                ans.add(folders[i]);
                String prefix = folders[i]+"/";
                for(int j = i+1;j< folders.length; j++)
                    // remove if the subfolder starts with folder prefix
                    if(folders[j] != null && folders[j].startsWith(prefix))
                        folders[j] = null;
            }
        }
        return ans;
    }

    private static List<String> usingTrie(String[] folders){

        TrieNode root = new TrieNode();
        //add all paths
        for (String folder: folders) {
            insert(root,folder);
        }

        List<String> res = new ArrayList<>();

        DFS(root, res);
      //  DFS(root.children[26], res);
        //DFS on trie
       /*
        for(int i = 0;i<=25;i++)
            if(root.children[i] != null)
                DFS(root.children[i], res);
        */


        return res;
    }

    private static void DFS(TrieNode node, List<String> res){
        if(node == null)
            return;
        if(node.isTerminal)
            res.add(node.path);

        for(int i = 0; i<=25;i++)
            if(node.children[i] != null)
                DFS(node.children[i], res);
        //if not terminal word and path exists
        if(!node.isTerminal && node.children[26] != null)
            DFS(node.children[26], res);
    }

    private static  void insert(TrieNode root,String path){
        TrieNode curr = root;
        for(int i = 1;i < path.length();i++){
            int idx = path.charAt(i) == '/' ? 26 : path.charAt(i) - 'a';
            if(curr.children[idx] == null)
                curr.children[idx] = new TrieNode();
            //move the pointer
            curr = curr.children[idx];
        }
        curr.isTerminal = true;
        curr.path = path;
    }

   static class TrieNode{
        //only terminal node will store path
        String path;
        boolean isTerminal;
        TrieNode [] children;
        TrieNode(){
            //26th index is to store path separator '/'
            children = new  TrieNode [27];
        }
    }
}

/*
Example 1:

Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
Output: ["/a","/c/d","/c/f"]
Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
Example 2:

Input: folder = ["/a","/a/b/c","/a/b/d"]
Output: ["/a"]
Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".
Example 3:

Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 */
