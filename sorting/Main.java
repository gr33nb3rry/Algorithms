package sorting;

import java.util.Arrays;

class Sorting {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
    public static void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] left = new int[mid];
            int[] right = new int[arr.length - mid];
            System.arraycopy(arr, 0, left, 0, mid);
            System.arraycopy(arr, mid, right, 0, arr.length - mid);
            mergeSort(left);
            mergeSort(right);
            merge(arr, left, right);
        }
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        // Bubble Sort
        int[] bubbleSortedArr = arr.clone();
        long bubbleStartTime = System.nanoTime();
        Sorting.bubbleSort(bubbleSortedArr);
        long bubbleEndTime = System.nanoTime();
        long bubbleTime = bubbleEndTime - bubbleStartTime;
        System.out.println("Bubble Sort:\n\t" + Arrays.toString(bubbleSortedArr) + "\n\t(Time: " + bubbleTime + " ns)");
        // Selection Sort
        int[] selectionSortedArr = arr.clone();
        long selectionStartTime = System.nanoTime();
        Sorting.selectionSort(selectionSortedArr);
        long selectionEndTime = System.nanoTime();
        long selectionTime = selectionEndTime - selectionStartTime;
        System.out.println("Selection Sort:\n\t" + Arrays.toString(selectionSortedArr) + "\n\t(Time: " + selectionTime + " ns)");
        // Merge Sort
        int[] mergeSortedArr = arr.clone();
        long mergeStartTime = System.nanoTime();
        Sorting.mergeSort(mergeSortedArr);
        long mergeEndTime = System.nanoTime();
        long mergeTime = mergeEndTime - mergeStartTime;
        System.out.println("Merge Sort:\n\t" + Arrays.toString(mergeSortedArr) + "\n\t(Time: " + mergeTime + " ns)");
        // Quick Sort
        int[] quickSortedArr = arr.clone();
        long quickStartTime = System.nanoTime();
        Sorting.quickSort(quickSortedArr);
        long quickEndTime = System.nanoTime();
        long quickTime = quickEndTime - quickStartTime;
        System.out.println("Quick Sort:\n\t" + Arrays.toString(quickSortedArr) + "\n\t(Time: " + quickTime + " ns)");
    }
}

