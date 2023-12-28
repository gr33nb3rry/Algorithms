package minimum_spanning_tree;

import java.util.*;

class Graph {
    private final Map<Integer, Map<Integer, Integer>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }
    public void addEdge(int source, int destination, int weight) {
        adjacencyList.computeIfAbsent(source, k -> new HashMap<>()).put(destination, weight);
        adjacencyList.computeIfAbsent(destination, k -> new HashMap<>()).put(source, weight); // Assuming undirected graph
    }
    public Set<Integer> getVertices() {
        return adjacencyList.keySet();
    }
    public Map<Integer, Integer> getNeighbors(int vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyMap());
    }
}
class MinimumSpanningTree {
    public static List<Edge> primMST(Graph graph) {
        List<Edge> mstEdges = new ArrayList<>();
        Set<Integer> visitedVertices = new HashSet<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        // Choose an arbitrary starting vertex
        int startVertex = graph.getVertices().iterator().next();
        visitedVertices.add(startVertex);
        // Add edges from the starting vertex to the min heap
        for (Map.Entry<Integer, Integer> neighborEntry : graph.getNeighbors(startVertex).entrySet()) {
            int neighborVertex = neighborEntry.getKey();
            int edgeWeight = neighborEntry.getValue();
            minHeap.offer(new Edge(startVertex, neighborVertex, edgeWeight));
        }
        while (!minHeap.isEmpty() && visitedVertices.size() < graph.getVertices().size()) {
            Edge minEdge = minHeap.poll();
            int currentVertex = minEdge.destination;
            if (visitedVertices.contains(currentVertex)) {
                continue; // Skip if the destination vertex is already visited
            }
            visitedVertices.add(currentVertex);
            mstEdges.add(minEdge);
            for (Map.Entry<Integer, Integer> neighborEntry : graph.getNeighbors(currentVertex).entrySet()) {
                int neighborVertex = neighborEntry.getKey();
                int edgeWeight = neighborEntry.getValue();
                if (!visitedVertices.contains(neighborVertex)) {
                    minHeap.offer(new Edge(currentVertex, neighborVertex, edgeWeight));
                }
            }
        }
        return mstEdges;
    }
    public static List<Edge> kruskalMST(Graph graph) {
        List<Edge> mstEdges = new ArrayList<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        UnionFind unionFind = new UnionFind(graph.getVertices());
        // Add all edges to the min heap
        for (int source : graph.getVertices()) {
            for (Map.Entry<Integer, Integer> neighborEntry : graph.getNeighbors(source).entrySet()) {
                int destination = neighborEntry.getKey();
                int weight = neighborEntry.getValue();
                minHeap.offer(new Edge(source, destination, weight));
            }
        }
        while (!minHeap.isEmpty() && mstEdges.size() < graph.getVertices().size() - 1) {
            Edge minEdge = minHeap.poll();
            assert minEdge != null;
            int source = minEdge.source;
            int destination = minEdge.destination;

            if (unionFind.find(source) != unionFind.find(destination)) {
                mstEdges.add(minEdge);
                unionFind.union(source, destination);
            }
        }
        return mstEdges;
    }
    public static class Edge {
        int source;
        int destination;
        int weight;
        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    private static class UnionFind {
        private final int[] parent;
        public UnionFind(Set<Integer> vertices) {
            parent = new int[vertices.size() + 1];
            for (int vertex : vertices) {
                parent[vertex] = vertex;
            }
        }
        public int find(int vertex) {
            if (parent[vertex] != vertex) {
                parent[vertex] = find(parent[vertex]);
            }
            return parent[vertex];
        }
        public void union(int vertex1, int vertex2) {
            int root1 = find(vertex1);
            int root2 = find(vertex2);
            parent[root1] = root2;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 4, 5);
        graph.addEdge(3, 4, 6);

        // Finding Minimum Spanning Tree using Prim's algorithm
        List<MinimumSpanningTree.Edge> primMSTEdges = MinimumSpanningTree.primMST(graph);
        System.out.println("Prim's Minimum Spanning Tree Edges:");
        for (MinimumSpanningTree.Edge edge : primMSTEdges) {
            System.out.println("Edge (" + edge.source + " - " + edge.destination + ") Weight: " + edge.weight);
        }

        // Finding Minimum Spanning Tree using Kruskal's algorithm
        List<MinimumSpanningTree.Edge> kruskalMSTEdges = MinimumSpanningTree.kruskalMST(graph);
        System.out.println("\nKruskal's Minimum Spanning Tree Edges:");
        for (MinimumSpanningTree.Edge edge : kruskalMSTEdges) {
            System.out.println("Edge (" + edge.source + " - " + edge.destination + ") Weight: " + edge.weight);
        }
    }
}

