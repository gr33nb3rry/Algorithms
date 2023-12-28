package linkedlist;

class LinkedList {
    static class Node {
        int data;
        Node next;
    }
    static Node root;
    static Node insert(Node root, int item) {
        Node temp = new Node();
        temp.data = item;
        temp.next = root;
        root = temp;
        return root;
    }
    static Node append(Node root, int item) {
        Node newNode = new Node();
        newNode.data = item;
        newNode.next = null;

        if (root == null) {
            root = newNode;
        } else {
            Node last = root;
            while (last.next != null)
                last = last.next;
            last.next = newNode;
        }

        return root;
    }
    static Node find(Node root, int item) {
        while (root != null)
        {
            if (root.data == item) return root;
            root = root.next;
        }
        return null;
    }
    static int count(Node root) {
        int count = 0;
        while (root != null)
        {
            count++;
            root = root.next;
        }
        return count;
    }
    static Node remove(Node root, int item) {
        Node foundNode = find(root, item);
        if (foundNode == null) return root;

        Node newList = null;

        while (root != null) {
            if (root != foundNode) {
                newList = append(newList, root.data);
            }
            root = root.next;
        }

        return newList;
    }
    static void display(Node root) {
        while (root != null)
        {
            System.out.print(root.data + " ");
            root = root.next;
        }
    }
    static void print(Node root) {
        System.out.print(root.data);
    }
    static Node arrayToList(int[] arr, int n) {
        root = null;
        for (int i = n - 1; i >= 0 ; i--)
            root = insert(root, arr[i]);
        return root;
    }
}
public class Main {
    public static void main(String[] args)
    {
        int[] arr = { 1, 2, 3, 4, 5 };
        LinkedList.Node root = LinkedList.arrayToList(arr, arr.length);
        System.out.println("List:");
        LinkedList.display(root);
        root = LinkedList.append(root, 6);
        System.out.println("\nAdd new element to the end:");
        LinkedList.display(root);
        LinkedList.Node foundNode = LinkedList.find(root, 2);
        System.out.println("\nFind second node:");
        LinkedList.print(foundNode);
        root = LinkedList.remove(root, 3);
        System.out.println("\nRemove third node:");
        LinkedList.display(root);
        System.out.println("\nCount:");
        System.out.println(LinkedList.count(root));
    }
}