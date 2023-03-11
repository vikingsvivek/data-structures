package com.custom.datastructures;

public class MaxHeap {
    private  int [] arr;
    private int capacity;
    private int size;

    public MaxHeap(int capacity){
        this.size = -1;
        this.capacity = capacity;
        this.arr = new int[this.capacity];
    }

    public void add(int val){
        if(size == capacity-1){
            System.out.println("WARN:: Heap is Full");
            return;
        }
        size++;
        // put element in end
        int i = size;
        arr[i] = val;

        while(i>0){
            int  parent = (i-1)/2;
            if(arr[i] > arr[parent]){
                swap(i,parent);
            }
            i = parent;

        }
    }

    public int poll(){
        if(isEmpty())
            return  -1;
        int i = 0;
        int val = arr[0];
        //move last element on top
        arr[0] = arr[size--];
        // re-arrange the top to maintain the heap property till last element
        while(i < size/2){
            int left = 2*i +1;
            int right = 2*i+2;
            int maxI = left;
            // Choose max from left/ right child
            if(arr[left] < arr[right]){
                maxI = right;
            }
            //if curr is less than max, swap and move forward
            if(arr[i] < arr[maxI]){
                swap(i,maxI);
                i = maxI;
            }
        }
        return val;
    }

    public int peek(){
        if (isEmpty())
            return  -1;
        return arr[0];
    }

    public int size (){
        return size;
    }
    public boolean isEmpty(){
        return size < 0;
    }

    private static int[] buildHeapUsingHeapifyAlgo(int[] arr){

        for(int i = arr.length/2-1 ; i >=0;i--){
            heapify(arr,i, arr.length);
        }
        return arr;
    }

    private static void heapify(int[] arr, int i, int size){
        int largest = i;
        int left = 2*i+1;
        int right = 2*i+2;
        if(left < size && arr[left] > arr[largest]){
            largest = left;
        }
        if(right < size && arr[right] > arr[largest]){
            largest = right;
        }
        if(i != largest){
            swap(arr, i, largest);
            heapify(arr,largest, size);
        }
    }

    private void swap( int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    private static void swap(int arr[], int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(5);
        heap.add(10);
        heap.add(1);
        heap.add(11);
        heap.add(9);
        heap.add(1);
        heap.add(12);
        System.out.println("Size: "+heap.size() +" peek: "+heap.peek());

        while(!heap.isEmpty()){
            System.out.println("peek: "+ heap.poll());
        }

        int arr[] = {10,1,12,2,5,8,11,16,9};
        buildHeapUsingHeapifyAlgo(arr);
        print(arr);
        heapSort(arr, arr.length);
        System.out.println("After Sorting: ");
        print(arr);
    }

    //O(N Log N) time O(1) space
    private static void heapSort(int[] nums, int n){
        int size = n;
        // As top of max heap is max value, we can assume that as sorted and move it to end of array i.e swap it to end index, then we need to rearrange the heap, then again repeat this process until only one element is in heap.
        while(size > 1){
            //swap nums at index 0 & last index num
            swap(nums, 0, size-1);
            // keep reducing the size, this is sorting array from end
            size--;
            // heapify
            heapify(nums,0, size);
        }
    }

    private static void print(int arr[]){
        for(int el: arr)
            System.out.print(el+" ");
        System.out.println();
    }
}
