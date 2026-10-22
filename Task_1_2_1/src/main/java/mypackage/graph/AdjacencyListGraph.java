package mypackage.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph implementation using an adjacency list.
 *
 * @param <T> the type of data stored in the vertices
 */
public class AdjacencyListGraph<T> extends AbstractGraph<T> {
    private final Map<Vertex<T>, List<Vertex<T>>> adjList = new HashMap<>();

    @Override
    public boolean addVertex(Vertex<T> vertex) {
        if (vertex == null || adjList.containsKey(vertex)) {
            return false;
        }
        adjList.put(vertex, new ArrayList<>());
        return true;
    }

    @Override
    public boolean removeVertex(Vertex<T> vertex) {
        if (vertex == null || !adjList.containsKey(vertex)) {
            return false;
        }
        adjList.remove(vertex);
        for (List<Vertex<T>> neighbors : adjList.values()) {
            neighbors.removeIf(v -> v.equals(vertex));
        }
        return true;
    }

    @Override
    public boolean addEdge(Vertex<T> from, Vertex<T> to) {
        if (from == null || to == null) return false;
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) return false;

        List<Vertex<T>> neighbors = adjList.get(from);
        if (neighbors.contains(to)) return false;

        neighbors.add(to);
        return true;
    }

    @Override
    public boolean removeEdge(Vertex<T> from, Vertex<T> to) {
        if (from == null || to == null) return false;
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) return false;

        return adjList.get(from).remove(to);
    }

    @Override
    public List<Vertex<T>> getVertices() {
        return new ArrayList<>(adjList.keySet());
    }

    @Override
    public List<Edge<T>> getEdges() {
        List<Edge<T>> edgeList = new ArrayList<>();
        for (Map.Entry<Vertex<T>, List<Vertex<T>>> entry : adjList.entrySet()) {
            Vertex<T> source = entry.getKey();
            for (Vertex<T> target : entry.getValue()) {
                edgeList.add(new Edge<>(source, target));
            }
        }
        return edgeList;
    }

    @Override
    public List<Vertex<T>> getNeighbors(Vertex<T> vertex) {
        if (vertex == null || !adjList.containsKey(vertex)) {
            return List.of();
        }
        return new ArrayList<>(adjList.get(vertex));
    }
}