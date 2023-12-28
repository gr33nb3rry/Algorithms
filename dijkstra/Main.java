package dijkstra;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;

class Graph {
    private final Map<Integer, Map<Integer, Integer>> adjacencyList;
    public Graph() {
        adjacencyList = new HashMap<>();
    }
    public void addEdge(int source, int destination, int weight) {
        adjacencyList.computeIfAbsent(source, k -> new HashMap<>()).put(destination, weight);
    }
    public Map<Integer, Integer> getNeighbors(int vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyMap());
    }
}

class DijkstraAlgorithm {
    public static Map<Integer, Integer> findShortestPaths(Graph graph, int start) {
        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));

        distances.put(start, 0);
        minHeap.offer(new Node(start, 0));

        while (!minHeap.isEmpty()) {
            Node currentNode = minHeap.poll();
            int currentVertex = currentNode.vertex;
            int currentDistance = currentNode.distance;

            if (distances.containsKey(currentVertex) && distances.get(currentVertex) < currentDistance) {
                continue; // Skip if we have a shorter distance to the current vertex
            }
            for (Map.Entry<Integer, Integer> neighborEntry : graph.getNeighbors(currentVertex).entrySet()) {
                int neighborVertex = neighborEntry.getKey();
                int edgeWeight = neighborEntry.getValue();
                int newDistance = currentDistance + edgeWeight;
                if (!distances.containsKey(neighborVertex) || newDistance < distances.get(neighborVertex)) {
                    distances.put(neighborVertex, newDistance);
                    minHeap.offer(new Node(neighborVertex, newDistance));
                }
            }
        }
        return distances;
    }
    private static class Node {
        int vertex;
        int distance;
        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 8, 7);

        int startVertex = 0;
        Map<Integer, Integer> shortestDistances = DijkstraAlgorithm.findShortestPaths(graph, startVertex);

        System.out.println("Shortest Distances from vertex " + startVertex + ":");
        for (Map.Entry<Integer, Integer> entry : shortestDistances.entrySet()) {
            System.out.println("To vertex " + entry.getKey() + ": " + entry.getValue());
        }
    }
}

