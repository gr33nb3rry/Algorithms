package trie;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        this.children = new TrieNode[26]; // Assuming only lowercase English letters
        this.isEndOfWord = false;
    }
}
class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isEndOfWord;
    }
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return true;
    }
    public void delete(String word) {
        delete(root, word, 0);
    }
    private boolean delete(TrieNode node, String word, int depth) {
        if (node == null) {
            return false;
        }
        if (depth == word.length()) {
            if (!node.isEndOfWord) {
                return false; // Word not present
            }
            node.isEndOfWord = false;
            return isNodeEmpty(node);
        }
        int index = word.charAt(depth) - 'a';
        if (delete(node.children[index], word, depth + 1)) {
            node.children[index] = null;
            return !node.isEndOfWord && isNodeEmpty(node);
        }
        return false;
    }
    private boolean isNodeEmpty(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) {
                return false;
            }
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        // Insert words
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apricot");
        // Search words
        System.out.println("Search 'apple': " + trie.search("apple")); // true
        System.out.println("Search 'app': " + trie.search("app"));     // true
        System.out.println("Search 'apri': " + trie.search("apri"));   // false
        System.out.println("Search 'apricot': " + trie.search("apricot")); // true
        // Search prefixes
        System.out.println("StartsWith 'app': " + trie.startsWith("app")); // true
        System.out.println("StartsWith 'apri': " + trie.startsWith("apri")); // true
        System.out.println("StartsWith 'ban': " + trie.startsWith("ban"));   // false
        // Delete words
        trie.delete("apple");
        System.out.println("Search 'apple' after deletion: " + trie.search("apple")); // false
    }
}
