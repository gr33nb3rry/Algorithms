package binary_search_tree;
class BinarySearchTree {
    static class TreeNode {
        int key;
        TreeNode left, right;
        public TreeNode(int item) {
            key = item;
            left = right = null;
        }
    }
    public TreeNode root;
    static TreeNode insert(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        }
        return root;
    }
    static TreeNode search(TreeNode root, int key) {
        if (root == null || root.key == key) {
            return root;
        }
        if (key < root.key) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }
    static void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.key + " ");
            inOrderTraversal(root.right);
        }
    }
    static void preOrderTraversal(TreeNode root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }
    static void postOrderTraversal(TreeNode root) {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.key + " ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BinarySearchTree root = new BinarySearchTree();
        int[] keys = {50, 30, 20, 40, 70, 60, 80};
        for (int key : keys) {
            root.root = BinarySearchTree.insert(root.root, key);
        }

        System.out.println("In-order traversal:");
        BinarySearchTree.inOrderTraversal(root.root);

        int keyToSearch = 40;
        System.out.println("\nSearch for key " + keyToSearch + ": " + BinarySearchTree.search(root.root, keyToSearch));

        System.out.println("Pre-order traversal:");
        BinarySearchTree.preOrderTraversal(root.root);

        System.out.println("\nPost-order traversal:");
        BinarySearchTree.postOrderTraversal(root.root);
    }
}
