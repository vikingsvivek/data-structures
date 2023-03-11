package com.custom.datastructures;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    int capacity;
    Map<Integer, DoubleList> freq2List;
    Map<Integer, DoubleLinkNode> cache;
    int minFreq;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 1;
        freq2List = new HashMap<>();
        cache = new HashMap<>();
    }

    public int get(int key) {
        if(cache.containsKey(key)){
            DoubleLinkNode node = cache.get(key);
            //promote to higher freq list
            updateNode(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)){
            DoubleLinkNode node = cache.get(key);
            //update value, it might have changed
            node.val = value;
            //promote to higher freq list
            updateNode(node);
        }else{
            if(cache.size() == capacity){
                //eviction needed in LFU fashion
                DoubleList leastFreqList = freq2List.get(minFreq);
                cache.remove(leastFreqList.tail.prev.key);
                leastFreqList.removeNode(leastFreqList.tail.prev);
            }
            // resetting leastFreq to 1, because adding a new node
            minFreq = 1;
            DoubleLinkNode node = new DoubleLinkNode(key,value);
            DoubleList list = freq2List.getOrDefault(minFreq, new DoubleList());

            //put node in cache & list of freq2List
            cache.put(key,node);
            freq2List.put(1,list);
            list.addNodeAtHead(node);
        }
    }

    private void updateNode(DoubleLinkNode node){
        // remove node from old its freq-list
        DoubleList oldFreqList = freq2List.get(node.freq);
        oldFreqList.removeNode(node);

        if(node.freq == minFreq && oldFreqList.isEmpty()){
            minFreq++;
        }
        //increment freq of node
        node.freq++;
        //promote node to increased freq list
        DoubleList newFreqList = freq2List.getOrDefault(node.freq, new DoubleList());
        newFreqList.addNodeAtHead(node);
        // put in freq2List back
        freq2List.put(node.freq, newFreqList);
    }

    public static void main(String[] args) {
        //[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
       LFUCache cache = new LFUCache(2);
       cache.put(1,1);
       cache.put(2,2);
       System.out.println(cache.get(1));
       cache.put(3,3);
       System.out.println(cache.get(2));
       System.out.println(cache.get(3));
       cache.put(4,4);
       System.out.println(cache.get(1));
       System.out.println(cache.get(3));
       System.out.println(cache.get(4));

    }
}
class DoubleLinkNode{
    int key;
    int val;
    int freq;
    DoubleLinkNode next;
    DoubleLinkNode prev;

    public DoubleLinkNode(int key, int val){
        this.val= val;
        this.key = key;
        this.freq = 1;
    }

    public String toString(){
        return String.format("[key: %s, value: %s, freq: %s]", key,val,freq);
    }
}

class DoubleList{
    DoubleLinkNode head;
    DoubleLinkNode tail;
    int size;
    public DoubleList(){
        head = new DoubleLinkNode(-1,-1);
        tail = new DoubleLinkNode(-10,-10);
        head.next = tail;
        tail.prev = head;
    }

    public void addNodeAtHead(DoubleLinkNode node){
        if(node == null){
            System.out.println("Invalid node to insert");
            return;
        }
        // link node's pointer to proper place
        node.next = head.next;
        node.prev = head;
        //link new node in list head
        head.next.prev = node;
        head.next = node;
        this.size++;
    }

    public void removeNode(DoubleLinkNode node){
        if(node == null){
            System.out.println("Invalid node to remove");
            return;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        this.size--;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public  void print(){
        DoubleLinkNode node = head.next;

        while (node != tail){
            System.out.print(node+"->");
            node = node.next;
        }
        System.out.println();
    }
}