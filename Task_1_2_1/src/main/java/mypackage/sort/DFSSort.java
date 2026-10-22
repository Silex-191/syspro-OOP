package mypackage.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mypackage.graph.Graph;
import mypackage.graph.Vertex;

/**
 * Depth-First Search (DFS) implementation of a topological sort.
 *
 * @param <T> the type of data stored in the graph vertices
 */
public class DFSSort<T> implements TopologicalSort<T> {

    @Override
    public List<Vertex<T>> sort(Graph<T> graph) {
        Map<Vertex<T>, Boolean> finished = new HashMap<>();
        List<Vertex<T>> result = new ArrayList<>();

        for (Vertex<T> vertex : graph.getVertices()) {
            visit(graph, vertex, finished, result);
        }

        Collections.reverse(result);
        return result;
    }

    private void visit(Graph<T> graph, Vertex<T> vertex,
        Map<Vertex<T>, Boolean> finished, List<Vertex<T>> result) {
        Boolean state = finished.get(vertex);

        if (state != null) {
            if (!state) {
                throw new IllegalStateException("Graph has cycle, topological sort is not possible");
            }
            return;
        }

        finished.put(vertex, false);

        for (Vertex<T> neighbor : graph.getNeighbors(vertex)) {
            visit(graph, neighbor, finished, result);
        }

        finished.put(vertex, true);
        result.add(vertex);
    }
}