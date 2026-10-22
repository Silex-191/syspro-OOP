package mypackage.sort;

import java.util.List;
import mypackage.graph.Graph;
import mypackage.graph.Vertex;

/**
 * Interface for topological sorting algorithms.
 *
 * @param <T> the type of data stored in the graph vertices
 */
public interface TopologicalSort<T> {

    /**
     * Sorts the provided graph topologically.
     *
     * @param graph the graph to sort
     * @return a topologically sorted list of vertices
     * @throws IllegalStateException if a cycle is detected
     */
    List<Vertex<T>> sort(Graph<T> graph);
}