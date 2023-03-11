package com.custom.datastructures;

public class MyCircularDeque {
    private int front, rear, size, n;
    private final int[] a;
    public MyCircularDeque(int k) {
        a = new int[k];
        n = a.length;
        rear = 1;
    }

    public boolean insertFront(int x) {
        if (size == n) return false;
        a[front = ++front % n] = x;
        size++;
        return true;
    }

    public boolean insertLast(int x) {
        if (size == n) return false;
        a[rear = (--rear + n) % n] = x;
        size++;
        return true;
    }

    public int deleteFront() {
        if (size == 0) return -1;
        int val = a[front];
        front = (--front + n) % n;
        size--;
        return val;
    }

    public int deleteLast() {
        if (size == 0) return -1;
        int val = a[rear];
        rear = ++rear % n;
        size--;
        return val;
    }

    public int getFront() {
        return size == 0 ? -1 : a[front];
    }

    public int getRear() {
        return size == 0 ? -1 : a[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == a.length;
    }

    public static void main(String[] args) {
        int cap = 10;
        MyCircularDeque deque = new MyCircularDeque(cap);
        for(int i = 1;i<= cap/2;i++){
            deque.insertFront(i-1);
            deque.insertLast(cap-i);
        }
        System.out.println("Full: "+deque.isFull());
        for(int i = 1;i<= cap/2;i++){
           System.out.println("front: "+deque.deleteFront()+" rear: "+deque.deleteLast());
        }
        System.out.println("Empty: "+deque.isEmpty());
    }
}