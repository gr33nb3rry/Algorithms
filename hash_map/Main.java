package hash_map;

import java.util.LinkedList;

class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private final LinkedList<Entry<K, V>>[] buckets;
    private final int capacity;
    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }
    public MyHashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }
    public void put(K key, V value) {
        int hashCode = key.hashCode();
        int index = hashCode % capacity;
        LinkedList<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        bucket.add(new Entry<>(key, value));
    }
    public V get(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % capacity;
        LinkedList<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }
    public void remove(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % capacity;
        LinkedList<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                bucket.remove(entry);
                return;
            }
        }
    }
    private static class Entry<K, V> {
        K key;
        V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();

        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        System.out.println("Value for key 'two': " + hashMap.get("two"));

        hashMap.remove("two");

        System.out.println("Value for key 'two' after removal: " + hashMap.get("two"));
    }
}

