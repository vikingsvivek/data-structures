package com.custom.datastructures;

public class Stack{
    int arr[];
    int top;
    int capacity;

    Stack(int cap){
        this.capacity = cap;
        arr = new int[cap];
        top = -1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void push(int val){
        if(top == capacity-1){
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

    public static void main(String[] args) {
        Stack st = new Stack(3);

        st.push(10);
        System.out.println(st.peek()+" size: "+ st.size());
        st.push(1);
        st.push(12);
        st.push(20);
        System.out.println(st.peek()+" size: "+ st.size());
        System.out.println(st.peek()+" Popping: "+st.pop()+ " Now size: "+ st.size());
        System.out.println(st.peek()+" Popping: "+st.pop()+ " Now size: "+ st.size());
        System.out.println(st.peek()+" Popping: "+st.pop()+ " Now size: "+ st.size());
        System.out.println(st.peek()+" Popping: "+st.pop()+ " Now size: "+ st.size());
    }
}
