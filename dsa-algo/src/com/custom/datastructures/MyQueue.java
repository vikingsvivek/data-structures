package com.custom.datastructures;

public class MyQueue {
    private MyStack st1;
    private MyStack st2;
    int capacity ;
    public MyQueue(int cap) {
        this.capacity = cap;
        st1 = new MyStack(capacity);
        st2 = new MyStack(capacity);
    }

    public void push(int val) {
        // push will happen on stack1
        if(this.size() == capacity){
            System.out.println("Overflow. - Queue is full.");
            return;
        }
        // if st2 is Empty push in st2 else push in st1
        if(st2.isEmpty())
            st2.push(val);
        else
            st1.push(val);
    }

    // pop Heave Queue
    public int pop() {
        if(st2.isEmpty())
            return -1;
        int val = st2.pop();

        // if stack 2 is empty
        if(st2.isEmpty()){
            // transfer st1's values in st2
            while(!st1.isEmpty()){
                st2.push(st1.pop());
            }

        }
        return val;

    }

    public int peek() {
        if(st2.isEmpty())
            return -1;
        return st2.peek();
    }

    public boolean empty() {
        return st2.isEmpty();
    }

    public  int size(){
        return st1.size() + st2.size();
    }

    class MyStack{
        int arr[];
        int top;
        int capacity;

        MyStack(int cap){
            this.capacity = cap;
            arr = new int[cap];
            top = -1;
        }

        public boolean isEmpty(){
            return top == -1;
        }

        public void push(int val){
            if(isFull()){
                System.out.println("Overflow");
                return;
            }
            arr[++top] = val;
        }

        public int pop(){
            if(top == -1){
                System.out.println("Empty");
                return -1;
            }
            return arr[top--];
        }

        public int peek(){
            if(top == -1)
                return -1;
            return arr[top];
        }

        public int size(){
            return top+1;
        }

        public boolean isFull(){
            return top == capacity-1;
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(5);

        queue.push(1);
        System.out.println(queue.peek()+" popping: "+queue.pop());
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        queue.push(6);
        queue.push(7);

        System.out.println(queue.peek());
        while(!queue.empty())
            System.out.print(queue.pop()+" ");
        System.out.println();
    }
}
