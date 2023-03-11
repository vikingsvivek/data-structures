package com.custom.datastructures;

import java.util.*;

public class BinaryTree {
    // from PreOrder traversal
    public static  TreeNode buildTree(List<Integer> values){
        int ind []= {0};
        return buildTree(values,ind);
    }

    private static TreeNode buildTree(List<Integer> values, int i[]){
        if(i[0] >= values.size())
            return null;
        Integer val =values.get(i[0]);

        if(val == null||val == -1 )
            return null;

        TreeNode node = new TreeNode(val);
        i[0]++;
        node.left = buildTree(values,i);
        i[0]++;
        node.right = buildTree(values,i);

        return node;
    }

    private static void preOrder(TreeNode node){
        if(node == null)
            return;
        System.out.print(node.val+" ");
        preOrder(node.left);
        preOrder(node.right);
    }

    private static void inOrder(TreeNode node){
        if(node == null)
            return;
        inOrder(node.left);
        System.out.print(node.val+" ");
        inOrder(node.right);
    }

    private static void postOrder(TreeNode node){
        if(node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val+" ");
    }

    private static void levelOrder(TreeNode node){
        if(node == null)
            return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(node);
        q.offer(null);
        while (!q.isEmpty()){
            TreeNode front = q.poll();
            if(front == null){
                System.out.println();
                // if Q is not empty, then mark level end by putting one null in end of Q
                if(!q.isEmpty())
                    q.offer(null);
            }else {
                //process curr node
                System.out.print(front.val+" ");
                //push child nodes in queue to be processed later
                if(front.left !=null)
                    q.offer(front.left);
                if(front.right !=null)
                    q.offer(front.right);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,8, -1,-1,-1,-1,4,5,-1,-1,6,-1,-1);
        TreeNode root = BinaryTree.buildTree(list);
        System.out.println("PreOrder");
        preOrder(root);
        System.out.println("InOrder");
        inOrder(root);
        System.out.println();
        System.out.println("PostOrder");
        postOrder(root);
        System.out.println();
        System.out.println("LevelOrder");
        levelOrder(root);
        System.out.println();

        System.out.println();
        list = Arrays.asList(1,2,4,6, -1,-1,3,5,7,-1,-1);
         root = BinaryTree.buildTree(list);
        System.out.println("PreOrder");
        preOrder(root);
        System.out.println("InOrder");
        inOrder(root);
        System.out.println("PostOrder");
        postOrder(root);
        System.out.println("LevelOrder");
        levelOrder(root);
        System.out.println();

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode (int val) {
        this.val = val;
    }
    public String toString(){
        return String.valueOf(this.val);
    }
}
