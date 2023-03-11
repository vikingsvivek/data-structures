package com.custom.datastructures;

public class TwoStacksUsingArray {
    public void main(String[] args) {
        Stacks stacks = new Stacks(5);

    }

    class Stacks{
        int arr[] ;
        int top1;
        int top2;
        public Stacks(int capacity){
            top1 = -1;
            top2 = capacity;
            arr = new int[capacity];
        }

        public boolean isEmpty1() {
            return top1 == -1;
        }

        public boolean isEmpty2() {
            return top2 == arr.length;
        }

        public void push1(int val){
            // at-least one space there
            if(top2-top1 > 1){
                arr[++top1] = val;
            }
        }

        public void push2(int val){
            // at-least one space there
            if(top2-top1 > 1){
                arr[--top2] = val;
            }
        }

        public int peek1(){
            return isEmpty1() ? -1 : arr[top1];
        }

        public int peek2(){
            return isEmpty2() ? -1 : arr[top2];
        }

        public int pop1(){
            return isEmpty1() ? -1 : arr[top1--];
        }

        public int pop2(){
            return isEmpty2() ? -1 : arr[top2++];
        }
    }
}
