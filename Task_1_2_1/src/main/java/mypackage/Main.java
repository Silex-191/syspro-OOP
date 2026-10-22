package mypackage;

import mypackage.graph.AdjacencyMatrixGraph;
import mypackage.graph.Graph;
import mypackage.graph.Vertex;
import mypackage.sort.DFSSort;
import mypackage.sort.TopologicalSort;

public class Main {

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

        TopologicalSort<String> sorter = new DFSSort<>();
        System.out.println("Using manual sorter injection: " + sorter.sort(graph));

        System.out.println("Using static method: " + Graph.topologicalSort(graph));
    }
}