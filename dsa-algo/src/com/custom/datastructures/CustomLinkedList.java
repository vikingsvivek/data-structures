package com.custom.datastructures;

public class CustomLinkedList {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
       list.addAtIndex(1,0);
        list.print();
        System.out.println("At idx 1:  "+ list.get(0));

    }

//  ["com.custom.datastructures.MyLinkedList","addAtHead","deleteAtIndex","addAtHead","addAtHead","addAtHead","addAtHead","addAtHead","addAtTail","get","deleteAtIndex","deleteAtIndex"]
// [[],[2],[1],[2],[7],[3],[2],[5],[5],[5],[6],[4]]
}


class MyLinkedList {
    private LinkedNode head;
    // private com.custom.datastructures.LinkedNode tail;
    public MyLinkedList() {
        // no-op
    }

    public void print(){
        LinkedNode curr = head;
        while(curr != null){
            System.out.print(curr.val+" ");
            curr = curr.next;
        }
        System.out.println();
    }
    public int get(int index) {

        int count = 0;
        LinkedNode curr = head;

        while(curr != null && count < index){
            curr = curr.next;
            count++;
        }

        if(curr !=null && count == index){
            return curr.val;
        }
        return -1;
    }

    public void addAtHead(int val) {
        LinkedNode node = new LinkedNode(val);
        if(head == null)
        {
            head = node;
            //tail = node;
        }else{
            node.next = head;
            head = node;
        }
    }

    public void addAtTail(int val) {
        LinkedNode node = new LinkedNode(val);
        if(head == null){
            head = node;
            return;
        }

        //tail.next = node;
        //tail = node;

        LinkedNode curr = head;
        while(curr.next != null){
            curr = curr.next;
        }
        curr.next = node;

    }

    public void addAtIndex(int index, int val) {
        if(head == null && index == 0){
            head = new LinkedNode(val);
            return;
        }

        LinkedNode curr = head, prev = null;
        int count = 0;

        while(curr != null && count < index){
            prev = curr;
            curr = curr.next;
            count++;
        }

        //System.out.println(count+" indx: "+ index);
        if(count < index)
            return;

        LinkedNode node = new LinkedNode(val);

        if(prev != null){
            prev.next = node;
            node.next = curr;
        }else{
            // when insertion at 0th index
            node.next = head;
            head = node;
        }
    }

    public void deleteAtIndex(int index) {
        LinkedNode curr = head, prev = null;
        int count = 0;
        while( curr != null && count < index){
            prev = curr;
            curr = curr.next;
            count++;
        }
        //  System.out.println(count+" indx: "+ index);

        // deleting invalid node
        if(curr == null)
            return;

        if(prev == null){
            //deletion on 0th index
            head = head.next;
        }else{
            prev.next = curr.next;
            curr.next = null;
        }

    }
}
class LinkedNode {
    LinkedNode next;
    int val;

    public LinkedNode (int val){
        this.val = val;
    }
}
