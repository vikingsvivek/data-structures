package com.custom.datastructures;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsing2Queues {
    Queue<Integer> q1;
    Queue<Integer> q2;
    int peek = -1;

    public StackUsing2Queues() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    //O(1)
    public void push(int val) {
        //push in Q1 always
        peek = val;
        q1.offer(val);
    }

    // POP heavy com.custom.datastructures.Stack
// O(1) Best case O(N) worst case
    public int pop() {
            if(size() == 0)
            return -1;

        //pop always from q2
        if (q2.isEmpty()) {
            while (q1.size() > 1) {
                    peek = q1.peek();
                q2.offer(q1.poll());
            }
            int val = q1.poll();
            // swap Q1 Q2
            Queue<Integer> tmp = q1;
            q1 = q2;
            q2 = tmp;
            return val;
        }
        return q2.poll();
    }

    //O(1)
    public int top() {
        return empty() ? -1 : peek;
    }

    public boolean empty() {
        return (q1.size() + q2.size() == 0);
    }

    public int size(){
            return (q1.size() + q2.size());
    }

        public static void main(String[] args) {
                StackUsing2Queues stack = new StackUsing2Queues();

                for(int i = 1; i<= 5;i++)
                        stack.push(i);
                //System.out.println("Size: "+ stack.size()+ " peek: "+stack.top()+" Pooped: "+ stack.pop() );
                stack.push(6);
                stack.push(7);
                stack.push(8);
                System.out.println("Size: "+ stack.size()+ " peek: "+stack.top()+" Pooped: "+ stack.pop() );
                System.out.println("Size: "+ stack.size()+ " peek: "+stack.top()+" Pooped: "+ stack.pop() );
                System.out.println("Size: "+ stack.size()+ " peek: "+stack.top()+" Pooped: "+ stack.pop() );
                while (!stack.empty()){
                        System.out.println("Size: "+ stack.size()+ " peek: "+stack.top()+" Pooped: "+ stack.pop() );
                }
                System.out.println("Size: "+ stack.size()+ " peek: "+stack.top()+" Pooped: "+ stack.pop() );

        }
}