package com.custom.datastructures;

public class DoubleLinkList {
    int size;
    Node head;
    Node tail;

    public DoubleLinkList(){
        head = new Node(-1,-1);
        tail = new Node(-10,-10);
        head.next = tail;
        tail.prev = head;
    }

    public Node insertAtHead(int key, int val){
        Node node = new Node(key,val);
        // add new node at head
        node.prev = head;
        node.next = head.next;
        // heads next linked to new node via prev pointer
        head.next.prev = node;
        // head linked with new node
        head.next = node;
        // increase size
        size++;
        return  node;
    }

    public void remove(Node node){
        if(isEmpty()){
            System.out.println("Already Empty list ->");
            return;
        }

        Node nodeNext = node.next;
        Node nodePrev = node.prev;

        nodePrev.next = nodeNext;
        nodeNext.prev = nodePrev;

        // remove curr node
        node.next = null;
        node.prev = null;
        // decrease size
        size--;
    }

    public Node removefromTail(){
        if(isEmpty()){
            System.out.println("Already Empty list ->");
            return null;
        }
        Node lastNode = tail.prev;

        tail.prev = lastNode.prev;
        lastNode.prev.next = tail;

        // remove the lastNode
        lastNode.prev = null;
        lastNode.next = null;
        // decrease size
        size--;
        return  lastNode;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        /*com.custom.datastructures.DoubleLinkList list = new com.custom.datastructures.DoubleLinkList();
        System.out.println("size: "+ list.size());

       com.custom.datastructures.Node n1 =  list.insertAtHead(10);
       com.custom.datastructures.Node n2 =  list.insertAtHead(20);

        list.print();
        System.out.println("size: "+list.size());
        list.removefromTail();
        System.out.println("size: "+list.size());
        list.print();
        //com.custom.datastructures.Node n3 =  list.insertAtHead(30);

        list.remove(n2);
        System.out.println("size: "+list.size());
        list.print();*/


    }

    private void print(){
        Node curr = head;
        while (curr != null){
            System.out.print(curr.val+"->");
            curr = curr.next;
        }
        System.out.println();
    }
}
class Node{

    int key;
    int val;
    Node next;
    Node prev;

    public Node(int key, int val){
        this.key = key;
        this.val=val;
    }
}
