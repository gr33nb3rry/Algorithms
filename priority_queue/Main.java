package priority_queue;

import java.util.Arrays;

class PriorityQueue {
    private final int capacity;
    private int size;
    private final int[] heap;
    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity + 1];
    }
    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Priority Queue is full. Cannot insert.");
            return;
        }
        size++;
        heap[size] = value;
        heapifyUp(size);
    }
    public int extractMax() {
        if (size == 0) {
            System.out.println("Priority Queue is empty. Cannot extract.");
            return Integer.MIN_VALUE;
        }
        int max = heap[1];
        heap[1] = heap[size];
        size--;
        heapifyDown(1);

        return max;
    }
    private void heapifyUp(int index) {
        while (index > 1 && heap[index] > heap[parent(index)]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }
    private void heapifyDown(int index) {
        int maxIndex = index;
        int leftChild = leftChild(index);
        int rightChild = rightChild(index);
        if (leftChild <= size && heap[leftChild] > heap[maxIndex]) {
            maxIndex = leftChild;
        }
        if (rightChild <= size && heap[rightChild] > heap[maxIndex]) {
            maxIndex = rightChild;
        }
        if (index != maxIndex) {
            swap(index, maxIndex);
            heapifyDown(maxIndex);
        }
    }
    private int parent(int index) {
        return index / 2;
    }
    private int leftChild(int index) {
        return 2 * index;
    }
    private int rightChild(int index) {
        return 2 * index + 1;
    }
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    public void display() {
        System.out.println(Arrays.toString(Arrays.copyOfRange(heap, 1, size + 1)));
    }
}

public class Main {
    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue(10);

        priorityQueue.insert(3);
        priorityQueue.insert(7);
        priorityQueue.insert(1);
        priorityQueue.insert(5);

        System.out.println("Priority Queue after insertion:");
        priorityQueue.display();

        int max = priorityQueue.extractMax();
        System.out.println("Extracted Max: " + max);

        System.out.println("Priority Queue after extraction:");
        priorityQueue.display();
    }
}

