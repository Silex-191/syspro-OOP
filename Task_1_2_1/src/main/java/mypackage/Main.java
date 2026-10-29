package mypackage;

import mypackage.graph.AdjacencyMatrixGraph;
import mypackage.graph.Graph;
import mypackage.graph.Vertex;
import mypackage.sort.DfsSort;
import mypackage.sort.TopologicalSort;

/**
 * The main entry point of the application.
 * This class demonstrates the initialization of a graph,
 * adding vertices and edges, and performing a topological sort.
 */
public class Main {

    /**
     * The main method that executes the demonstration.
     * It creates an adjacency matrix graph, populates it with sample data,
     * and prints the result of the topological sorting.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Graph<String> graph = new AdjacencyMatrixGraph<>();

        Vertex<String> a = new Vertex<>(1, "A");
        Vertex<String> b = new Vertex<>(2, "B");
        Vertex<String> c = new Vertex<>(3, "C");

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);

        graph.addEdge(a, c);
        graph.addEdge(b, c);

        TopologicalSort<String> sorter = new DfsSort<>();
        System.out.println("Using manual sorter injection: " + sorter.sort(graph));

        System.out.println("Using static method: " + Graph.topologicalSort(graph));
    }
}