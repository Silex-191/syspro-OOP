package mypackage.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph implementation using an incidence matrix. Rows represent vertices and columns represent
 * edges.
 *
 * @param <T> the type of data stored in the vertices
 */
public class IncidenceMatrixGraph<T> extends AbstractGraph<T> {

    private static final int EDGE_NONE = 0;
    private static final int EDGE_OUTGOING = -1;
    private static final int EDGE_INCOMING = 1;
    private static final int EDGE_LOOP = 2;

    private int[][] matrix;
    private final Map<Vertex<T>, Integer> vertexToMap = new HashMap<>();
    private final Map<Edge<T>, Integer> edgeToMap = new HashMap<>();
    private final List<Vertex<T>> listVertex = new ArrayList<>();
    private final List<Edge<T>> listEdge = new ArrayList<>();

    public IncidenceMatrixGraph() {
        matrix = new int[16][16];
    }

    private void setSizeVertex(int size) {
        int[][] newMatrix = new int[size][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            newMatrix[i] = Arrays.copyOf(matrix[i], matrix[0].length);
        }
        matrix = newMatrix;
    }

    private void setSizeEdge(int size) {
        int[][] newMatrix = new int[matrix.length][size];
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
            setSizeVertex(matrix.length * 2);
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

        int lastIndex = listVertex.size() - 1;
        int indexVertex = vertexToMap.get(vertex);

        for (int i = listEdge.size() - 1; i >= 0; i--) {
            Edge<T> edge = listEdge.get(i);
            if (edge.getSource().equals(vertex) || edge.getTarget().equals(vertex)) {
                removeEdgeByIndex(i);
            }
        }

        if (lastIndex != indexVertex) {
            Vertex<T> lastVertex = listVertex.get(lastIndex);
            System.arraycopy(matrix[lastIndex], 0,
                matrix[indexVertex], 0, matrix[0].length);
            Arrays.fill(matrix[lastIndex], EDGE_NONE);

            listVertex.set(indexVertex, lastVertex);
            vertexToMap.put(lastVertex, indexVertex);
        } else {
            Arrays.fill(matrix[indexVertex], EDGE_NONE);
        }

        listVertex.remove(lastIndex);
        vertexToMap.remove(vertex);
        return true;
    }

    private void removeEdgeByIndex(int i) {
        int lastIndex = listEdge.size() - 1;
        Edge<T> edge = listEdge.get(i);

        if (lastIndex != i) {
            Edge<T> lastEdge = listEdge.get(lastIndex);
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = matrix[j][lastIndex];
                matrix[j][lastIndex] = EDGE_NONE;
            }
            edgeToMap.put(lastEdge, i);
            listEdge.set(i, lastEdge);
        } else {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = EDGE_NONE;
            }
        }
        listEdge.remove(lastIndex);
        edgeToMap.remove(edge);
    }

    @Override
    public boolean addEdge(Vertex<T> from, Vertex<T> to) {
        if (from == null || to == null) {
            return false;
        }
        if (!vertexToMap.containsKey(from) || !vertexToMap.containsKey(to)) {
            return false;
        }

        Edge<T> edge = new Edge<>(from, to);
        if (edgeToMap.containsKey(edge)) {
            return false;
        }

        if (listEdge.size() == matrix[0].length) {
            setSizeEdge(matrix[0].length * 2);
        }

        int edgeIndex = listEdge.size();
        edgeToMap.put(edge, edgeIndex);
        listEdge.add(edge);

        int fromIndex = vertexToMap.get(from);
        int toIndex = vertexToMap.get(to);

        if (fromIndex == toIndex) {
            matrix[fromIndex][edgeIndex] = EDGE_LOOP;
        } else {
            matrix[fromIndex][edgeIndex] = EDGE_OUTGOING;
            matrix[toIndex][edgeIndex] = EDGE_INCOMING;
        }

        return true;
    }

    @Override
    public boolean removeEdge(Vertex<T> from, Vertex<T> to) {
        if (from == null || to == null) {
            return false;
        }

        Edge<T> edge = new Edge<>(from, to);
        if (!edgeToMap.containsKey(edge)) {
            return false;
        }

        removeEdgeByIndex(edgeToMap.get(edge));
        return true;
    }

    @Override
    public List<Vertex<T>> getVertices() {
        return List.copyOf(listVertex);
    }

    @Override
    public List<Edge<T>> getEdges() {
        return List.copyOf(listEdge);
    }

    @Override
    public List<Vertex<T>> getNeighbors(Vertex<T> vertex) {
        if (vertex == null || !vertexToMap.containsKey(vertex)) {
            return List.of();
        }
        int indexV = vertexToMap.get(vertex);
        List<Vertex<T>> neighbors = new ArrayList<>();

        for (int j = 0; j < listEdge.size(); j++) {
            if (matrix[indexV][j] == EDGE_OUTGOING || matrix[indexV][j] == EDGE_LOOP) {
                neighbors.add(listEdge.get(j).getTarget());
            }
        }
        return neighbors;
    }
}