package mypackage.graph;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import mypackage.sort.DfsSort;

/**
 * Represents a generic directed graph.
 *
 * @param <T> the type of data stored in the vertices
 */
public interface Graph<T> {

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to add
     * @return true if the vertex was successfully added, false if it already exists
     */
    boolean addVertex(Vertex<T> vertex);

    /**
     * Removes a vertex from the graph, along with all its incident edges.
     *
     * @param vertex the vertex to remove
     * @return true if the vertex was found and removed, false otherwise
     */
    boolean removeVertex(Vertex<T> vertex);

    /**
     * Adds a directed edge from one vertex to another.
     *
     * @param from the source vertex
     * @param to   the target vertex
     * @return true if the edge was added, false if it already exists or vertices are missing
     */
    boolean addEdge(Vertex<T> from, Vertex<T> to);

    /**
     * Removes a directed edge between two vertices.
     *
     * @param from the source vertex
     * @param to   the target vertex
     * @return true if the edge was found and removed, false otherwise
     */
    boolean removeEdge(Vertex<T> from, Vertex<T> to);

    /**
     * Returns a list of all vertices in the graph.
     *
     * @return a list of vertices
     */
    List<Vertex<T>> getVertices();

    /**
     * Returns a list of all edges in the graph.
     *
     * @return a list of edges
     */
    List<Edge<T>> getEdges();

    /**
     * Returns a list of all outgoing neighbors for a given vertex.
     *
     * @param vertex the source vertex
     * @return a list of neighboring vertices
     */
    List<Vertex<T>> getNeighbors(Vertex<T> vertex);

    /**
     * Loads the graph data from a file.
     *
     * @param path   the file path
     * @param parser a function to parse the string data into type T
     * @throws IOException if an I/O error occurs or the format is invalid
     */
    void loadFromFile(Path path, Function<String, T> parser) throws IOException;

    /**
     * Saves the graph data to a file.
     *
     * @param path      the file path
     * @param formatter a function to format type T into a string
     * @throws IOException if an I/O error occurs
     */
    void saveToFile(Path path, Function<T, String> formatter) throws IOException;

    /**
     * Performs a topological sort on the given graph using the default DFS algorithm.
     *
     * @param g   the graph to sort
     * @param <T> the type of data in the graph
     * @return a topologically sorted list of vertices
     */
    static <T> List<Vertex<T>> topologicalSort(Graph<T> g) {
        return new DfsSort<T>().sort(g);
    }

    /**
     * Sorts the current graph topologically.
     *
     * @return a topologically sorted list of vertices
     */
    default List<Vertex<T>> sortTopologically() {
        return topologicalSort(this);
    }
}