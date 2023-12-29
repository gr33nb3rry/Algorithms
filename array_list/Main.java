package array_list;

import java.util.Arrays;

class ArrayListCustom<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;
    public ArrayListCustom() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    public void add(T element) {
        ensureCapacity();
        array[size++] = element;
    }
    public void remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            removeAtIndex(index);
        }
    }
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) array[index];
    }
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (array[i] == null) {
                    return i;
                }
            } else {
                if (element.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    public void traverse() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    private void ensureCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 2);
        }
    }
    private void removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayListCustom<Integer> arrayList = new ArrayListCustom<>();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        System.out.println("ArrayList after insertion:");
        arrayList.traverse();

        int searchElement = 2;
        int indexOfElement = arrayList.indexOf(searchElement);
        System.out.println("Index of " + searchElement + ": " + indexOfElement + ". Element: " + arrayList.get(indexOfElement));

        arrayList.remove(2);

        System.out.println("ArrayList after deletion 2:");
        arrayList.traverse();
    }
}

