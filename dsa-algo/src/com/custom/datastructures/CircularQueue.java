package com.custom.datastructures;

import java.util.* ;
import java.io.*;
public class CircularQueue {
    // Initialize your data structure.
    int[] arr;
    int front , rear;
    int capacity;
    public CircularQueue(int n) {
        capacity = n;
        arr = new int[capacity];
        front = rear = -1;
    }

    /*
       Enqueues 'X' into the queue. Returns true if it gets pushed into the stack,
       and false otherwise.
    */
    public boolean enqueue(int value) {
        if(isFull()){
            // overflow
            return false;
        }
        // empty
        if(isEmpty()){
            front = rear = 0;
        }else if(rear == capacity-1 && front != 0){
            //make it cyclic behavior
            rear = 0;
        }else{
            // normal case
            rear++;
        }
        arr[rear] = value;
        return true;
    }

    /*
      Dequeues top element from queue. Returns -1 if the stack is empty, otherwise
      returns the popped element.
    */
    public int dequeue() {
        // Empty Queue
        if(isEmpty()){
            return -1;
        }
        int  val = arr[front];
        // single element
        if(front == rear){
            front = rear = -1;
        }else if (front == capacity-1){
            // front is at end index, make it cyclic behavior
            front = 0;
        }else {
            front++;
        }
        return val;
    }

    private boolean isEmpty(){
        return front == -1;
    }
    private boolean isFull(){
        int lastIndex = capacity-1;
        return (front == 0 && rear == lastIndex) || (rear == ((front - 1 ) % lastIndex));
    }
};
