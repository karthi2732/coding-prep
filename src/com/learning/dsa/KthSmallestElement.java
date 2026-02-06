package com.learning.dsa;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestElement {

    public static int kThSmallestElementUsingPriorityQueue(int[] arr, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int e: arr) {
            priorityQueue.add(e);
        }
        int kthSmallestElement = 0;
        while (k-- > 0) {
            kthSmallestElement = priorityQueue.poll();
        }
        return kthSmallestElement;
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static int quickPartition(int[] arr, int k, int l, int r) {

        int pivotIdx = l + ((r-l)/2);

        swap(arr, pivotIdx, r);
        System.out.println(Arrays.toString(arr));

        pivotIdx = r;

        int start = l, end=r-1;

        System.out.printf("l: %s | r: %s \n", l, r);

        while(start<end) {

            while ( start<pivotIdx && arr[start]>arr[pivotIdx] )
                start++;

            while ( end>=l && arr[end]<=arr[pivotIdx] )
                end--;

            if(start<end) {
                swap(arr, start, end);
                System.out.println(Arrays.toString(arr));
            }

        }

        if(arr[pivotIdx]>arr[start]) {
            swap(arr, pivotIdx, start);
            System.out.println(Arrays.toString(arr));
        }

        return start;
    }


    public static int kthElementUsingQuickSelect(int[] arr, int k) {

        int l = 0, r = arr.length-1;
        int partition = quickPartition(arr, k, l, r);

        System.out.println(partition);
        System.out.println(Arrays.toString(arr));

        while(partition!=k) {

            if (partition<k) {
                l = partition+1;
            } else {
                r = partition-1;
            }

            partition = quickPartition(arr, k, l, r);

            System.out.println(partition);
            System.out.println(Arrays.toString(arr));

        }

        return arr[partition];
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        int k = 1;

        // To find 3rd Smallest element in the above array without sorting
        // Either use Priority Queue and fill the elements in the in heap. And Poll n times to find the nth smallest
        // Or use Quick Select Algorithm and find the kth smallest element.

        // Using Priority Queue
        int kthElement = kThSmallestElementUsingPriorityQueue(arr, k);
        System.out.printf("kthElement: %s \n", kthElement);

        // kth smallest = k-1 idx
        k = k-1;
        int kthElementUsingQuickSelect = kthElementUsingQuickSelect(arr, k);
        System.out.printf("kth Element Using Quick Select Algorithm: %s \n", kthElementUsingQuickSelect);

    }

}
