package com.custom.datastructures;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    // key 2 com.custom.datastructures.Node reference in DoubleLink NOde
    private Map<Integer, Node> cache;
    // These two pointers manage our Double link list
    private  Node head ,tail;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        // make DLL
        head = new Node(-1,-1);
        tail = new Node(-10,-10);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        // cache hit
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            // remove the node from list
            remove(node);
            // add again at head, as its accessed
            insert(key,node.val);
            return node.val;
        }
        // cache miss
        return -1;
    }

    public void put(int key, int value) {
        // cache hit
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            remove(node);
        }else{
            //cache miss
            // capacity check
            if(cache.size() == capacity) {
                //evict LRU node from list i.e. node from DLL tail side i.e. tail's prev node
                remove(tail.prev);
            }
        }
        insert(key,value);
    }

    private void insert(int key, int val){
        Node node = new Node(key,val);
        // put in cache
        cache.put(key, node);
        // insert at head
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private  void remove(Node node){
        // remove from cache
        cache.remove(node.key);
        // remove node from DLL
        node.prev.next = node.next;
        node.next.prev = node.prev;
        /**
         *  com.custom.datastructures.Node nodeNext = node.next;
         *         com.custom.datastructures.Node nodePrev = node.prev;
         *
         *         nodePrev.next = nodeNext;
         *         nodeNext.prev = nodePrev;
         *
         *         // remove curr node
         *         node.next = null;
         *         node.prev = null;
         */
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        cache.put(4,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
