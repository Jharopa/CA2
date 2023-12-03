package org.CA2.util;

// Usage: HeapSort.sort(array); where array is an array of any type that implements the comparable interface

public class HeapSort {
    /**
     * Generic implementation of the heapsort algorithm
     * @param arr The array of objects to be sorted
     * @param <T> Any type that implements the Comparable interface
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            T temp =  arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    /**
     * Recursive function responsible for reshaping array into heap data-structure
     * @param arr heap array
     * @param n size of the heap
     * @param i root of subtree
     * @param <T> Any type that implements the Comparable interface
     */
    private static <T extends Comparable<T>> void heapify(T[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left].compareTo(arr[largest]) > 0) {
            largest = left;
        }

        if (right < n && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
        }
        if (largest != i) {
            T swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }
}
