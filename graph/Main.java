package graph;

import java.util.LinkedList;
import java.util.Queue;

class Graph {
    private final int V;
    private final LinkedList<Integer>[] adjList;

    public Graph(int vertices) {
        this.V = vertices;
        adjList = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new LinkedList<>();
        }
    }
    public void addVertex(int v) {
        if (v >= 0 && v < V) {
            adjList[v] = new LinkedList<>();
        } else {
            System.out.println("Invalid vertex");
        }
    }
    public void addEdge(int v, int w) {
        if (v >= 0 && v < V && w >= 0 && w < V) {
            adjList[v].add(w);
            adjList[w].add(v);
        } else {
            System.out.println("Invalid vertices");
        }
    }
    public void DFS(int startVertex) {
        boolean[] visited = new boolean[V];
        DFSUtil(startVertex, visited);
        System.out.println();
    }
    private void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int neighbor : adjList[v]) {
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited);
            }
        }
    }
    public void BFS(int startVertex) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        visited[startVertex] = true;
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");

            for (int neighbor : adjList[currentVertex]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        for (int i = 0; i < 5; i++) {
            graph.addVertex(i);
        }
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        System.out.println("Depth-First Search (DFS):");
        graph.DFS(0);

        System.out.println("Breadth-First Search (BFS):");
        graph.BFS(0);
    }
}
