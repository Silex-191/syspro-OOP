package mypackage.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph implementation using an adjacency matrix.
 *
 * @param <T> the type of data stored in the vertices
 */
public class AdjacencyMatrixGraph<T> extends AbstractGraph<T> {

    private final Map<Vertex<T>, Integer> vertexToMap = new HashMap<>();
    private final List<Vertex<T>> listVertex = new ArrayList<>();
    private boolean[][] matrix;

    public AdjacencyMatrixGraph() {
        matrix = new boolean[16][16];
    }

    private void setSize(int size) {
        boolean[][] newMatrix = new boolean[size][size];
        for (int i = 0; i < matrix.length; i++) {
            newMatrix[i] = Arrays.copyOf(matrix[i], size);
        }
        matrix = newMatrix;
    }

    @Override
    public boolean addVertex(Vertex<T> vertex) {
        if (vertex == null || vertexToMap.containsKey(vertex)) {
            return false;
        }
        if (listVertex.size() == matrix.length) {
            setSize(matrix.length * 2);
        }
        vertexToMap.put(vertex, listVertex.size());
        listVertex.add(vertex);
        return true;
    }

    @Override
    public boolean removeVertex(Vertex<T> vertex) {
        if (vertex == null || !vertexToMap.containsKey(vertex)) {
            return false;
        }
        int indexToRemove = vertexToMap.get(vertex);
        int lastIndex = listVertex.size() - 1;

        if (indexToRemove != lastIndex) {
            Vertex<T> lastVertex = listVertex.get(lastIndex);
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][indexToRemove] = matrix[i][lastIndex];
                matrix[indexToRemove][i] = matrix[lastIndex][i];
            }
            matrix[indexToRemove][indexToRemove] = matrix[lastIndex][lastIndex];
            listVertex.set(indexToRemove, lastVertex);
            vertexToMap.put(lastVertex, indexToRemove);
        }

        for (int i = 0; i < matrix.length; i++) {
            matrix[lastIndex][i] = false;
            matrix[i][lastIndex] = false;
        }

        listVertex.remove(lastIndex);
        vertexToMap.remove(vertex);
        return true;
    }

    @Override
    public boolean addEdge(Vertex<T> from, Vertex<T> to) {
        if (from == null || to == null) {
            return false;
        }
        if (!vertexToMap.containsKey(from) || !vertexToMap.containsKey(to)) {
            return false;
        }

        int fromIndex = vertexToMap.get(from);
        int toIndex = vertexToMap.get(to);

        if (matrix[fromIndex][toIndex]) {
            return false;
        }

        matrix[fromIndex][toIndex] = true;
        return true;
    }

    @Override
    public boolean removeEdge(Vertex<T> from, Vertex<T> to) {
        if (from == null || to == null) {
            return false;
        }
        if (!vertexToMap.containsKey(from) || !vertexToMap.containsKey(to)) {
            return false;
        }

        int fromIndex = vertexToMap.get(from);
        int toIndex = vertexToMap.get(to);

        if (!matrix[fromIndex][toIndex]) {
            return false;
        }

        matrix[fromIndex][toIndex] = false;
        return true;
    }

    @Override
    public List<Vertex<T>> getVertices() {
        return List.copyOf(listVertex);
    }

    @Override
    public List<Edge<T>> getEdges() {
        List<Edge<T>> edgeList = new ArrayList<>();
        for (int i = 0; i < listVertex.size(); i++) {
            for (int j = 0; j < listVertex.size(); j++) {
                if (matrix[i][j]) {
                    edgeList.add(new Edge<>(listVertex.get(i), listVertex.get(j)));
                }
            }
        }
        return edgeList;
    }

    @Override
    public List<Vertex<T>> getNeighbors(Vertex<T> vertex) {
        if (vertex == null || !vertexToMap.containsKey(vertex)) {
            return List.of();
        }
        int indexV = vertexToMap.get(vertex);
        List<Vertex<T>> neighbors = new ArrayList<>();
        for (int i = 0; i < listVertex.size(); i++) {
            if (matrix[indexV][i]) {
                neighbors.add(listVertex.get(i));
            }
        }
        return neighbors;
    }
}