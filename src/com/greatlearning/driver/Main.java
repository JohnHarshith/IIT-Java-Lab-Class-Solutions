package com.greatlearning.driver;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("\nEnter the size of an array: ");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        System.out.println("Enter the " + size + " elements - ");
        int[] arr = new int[size];
        for(int i=0;i<size;i++) {
            arr[i] = sc.nextInt();
        }
        doMergeSort(arr,0,arr.length-1);
        System.out.println("Sorted array: ");
        for(int i=0;i<arr.length;i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        int midIndex = arr.length/2;
        int midElement = arr[midIndex];
        leftRotate(arr, midElement, arr.length);
        System.out.println("Rotated array: ");
        for(int i=0;i<arr.length;i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.print("\nEnter the element to be searched: ");
        int key = sc.nextInt();
        int index = pivotedBinarySearch(arr, key);
        System.out.println("We found the key at position " + (index+1));
        System.out.println();
    }
    private static void doMergeSort(int[] arr,int low,int high) {
        if(low<high) {
            int mid = (low+high)/2;
            doMergeSort(arr,low,mid);
            doMergeSort(arr,mid+1,high);
            mergeElements(arr,low,mid,high);
        }
    }
    private static void mergeElements(int[] arr,int low,int mid,int high) {
        int leftArraySize = mid-low+1;
        int rightArraySize = high-mid;
        int[] leftArray = new int[leftArraySize];
        int[] rightArray = new int[rightArraySize];
        for(int i=0;i<leftArraySize;i++) {
            leftArray[i] = arr[low+i];
        }
        for(int i=0;i<rightArraySize;i++) {
            rightArray[i] = arr[mid+1+i];
        }
        int i=0,j=0;
        int k = low;
        while(i<leftArraySize && j<rightArraySize) {
            if(leftArray[i]<=rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while(i<leftArraySize) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }
        while(j<rightArraySize) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }
    private static void leftRotate(int[] arr,int midElement,int length) {
        for(int i=0;i<midElement;i++) {
            leftRotateByOneElement(arr, length);
        }
    }
    private static void leftRotateByOneElement(int[] arr,int length) {
        int temp = arr[0];
        for(int i=0;i<length-1;i++) {
            arr[i] = arr[i+1];
        }
        arr[length-1] = temp;
    }
    private static int pivotedBinarySearch(int[] arr,int key) {
        int pivot = findPivotElement(arr,0,arr.length-1);
        if(pivot == -1) {
            return binarySearch(arr,0,arr.length-1,key);
        } else if(arr[pivot] == key) {
            return pivot;
        } else if(arr[0] <= key) {
            return binarySearch(arr,0,pivot-1,key);
        } else {
            return binarySearch(arr,pivot+1,arr.length-1,key);
        }
    }
    private static int binarySearch(int[] arr,int low,int high,int key) {
        if(high<low) {
            return -1;
        }
        int mid = (low+high)/2;
        if(arr[mid] == key) {
            return mid;
        } else if(arr[mid] < key) {
            return binarySearch(arr,mid+1,high,key);
        } else {
            return binarySearch(arr,low,mid-1,key);
        }
    }
    private static int findPivotElement(int[] arr, int low, int high) {
        if(high<low) {
           return -1;
        } else if(high==low) {
           return low;
        }
        int mid = (low+high)/2;
        if(mid<high && arr[mid]>arr[mid+1]) {
            return mid;
        } else if(mid>low && arr[mid]<arr[mid+1]) {
        return (mid-1);
        } else if(arr[low] >= arr[mid]) {
            return findPivotElement(arr,low,mid-1);
        }
        return findPivotElement(arr,mid+1,high);
    }
}
