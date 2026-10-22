package mypackage.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public abstract class GraphTest {

    protected Graph<String> graph;
    protected Vertex<String> v1;
    protected Vertex<String> v2;
    protected Vertex<String> v3;

    protected abstract Graph<String> createGraph();

    @BeforeEach
    void setUp() {
        graph = createGraph();
        v1 = new Vertex<>(1, "A");
        v2 = new Vertex<>(2, "B");
        v3 = new Vertex<>(3, "C");
    }

    @Test
    void testAddVertex() {
        assertTrue(graph.addVertex(v1));
        assertFalse(graph.addVertex(v1), "Cannot add an existing vertex");
        assertEquals(1, graph.getVertices().size());
    }

    @Test
    void testRemoveVertex() {
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v1, v2);

        assertTrue(graph.removeVertex(v1));
        assertFalse(graph.removeVertex(v1), "Vertex is already removed");
        assertEquals(1, graph.getVertices().size());
        assertEquals(0, graph.getEdges().size(), "Associated edges must be removed");
    }

    @Test
    void testAddEdge() {
        graph.addVertex(v1);
        graph.addVertex(v2);

        assertTrue(graph.addEdge(v1, v2));
        assertFalse(graph.addEdge(v1, v2), "Edge already exists");
        assertEquals(1, graph.getEdges().size());
    }

    @Test
    void testAddEdgeMissingVertices() {
        assertFalse(graph.addEdge(v1, v2), "Cannot add an edge for non-existent vertices");
    }

    @Test
    void testRemoveEdge() {
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v1, v2);

        assertTrue(graph.removeEdge(v1, v2));
        assertFalse(graph.removeEdge(v1, v2), "Edge is already removed");
        assertEquals(0, graph.getEdges().size());
    }

    @Test
    void testGetNeighbors() {
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        graph.addEdge(v1, v2);
        graph.addEdge(v1, v3);

        List<Vertex<String>> neighbors = graph.getNeighbors(v1);
        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains(v2));
        assertTrue(neighbors.contains(v3));
    }

    @Test
    void testEqualsDifferentImplementations() {
        Graph<String> listGraph = new AdjacencyListGraph<>();
        Graph<String> matrixGraph = new AdjacencyMatrixGraph<>();
        listGraph.addVertex(v1);
        listGraph.addVertex(v2);
        listGraph.addEdge(v1, v2);
        matrixGraph.addVertex(v1);
        matrixGraph.addVertex(v2);
        matrixGraph.addEdge(v1, v2);
        assertEquals(listGraph, matrixGraph,
            "Graphs with different implementations but identical structures must be equal");
    }

    @Test
    void testInterfaceDefaultAndStaticMethods() {
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        graph.addEdge(v1, v2);
        graph.addEdge(v2, v3);

        List<Vertex<String>> sorted1 = graph.sortTopologically();
        assertEquals(3, sorted1.size());

        List<Vertex<String>> sorted2 = Graph.topologicalSort(graph);
        assertEquals(3, sorted2.size());
    }

    @Test
    void testSelfLoopEdge() {
        graph.addVertex(v1);

        assertTrue(graph.addEdge(v1, v1), "Should allow adding a self-loop");
        assertEquals(1, graph.getEdges().size(), "Graph should contain 1 edge");

        List<Vertex<String>> neighbors = graph.getNeighbors(v1);
        assertEquals(1, neighbors.size(),
            "Vertex with a self-loop should have 1 neighbor");
        assertTrue(neighbors.contains(v1), "Vertex should be its own neighbor");

        assertTrue(graph.removeEdge(v1, v1), "Should be able to remove self-loop");
        assertEquals(0, graph.getEdges().size(),
            "Graph should be empty after removing the loop");
        assertTrue(graph.getNeighbors(v1).isEmpty(),
            "Vertex should have no neighbors after removal");
    }

    @Test
    void testEqualsCornerCasesAndSuccess() {
        Graph<String> otherGraph = createGraph();

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v1, v2);

        otherGraph.addVertex(v1);
        otherGraph.addVertex(v2);
        otherGraph.addEdge(v1, v2);

        assertTrue(graph.equals(graph), "Graph should be equal to itself");
        assertFalse(graph.equals(null), "Graph should not be equal to null");
        assertFalse(graph.equals(new Object()),
            "Graph should not be equal to a different class");
        assertTrue(graph.equals(otherGraph), "Identical graphs should be equal");
    }

    @Test
    void testHashCodeMatchesForEqualGraphs() {
        Graph<String> otherGraph = createGraph();

        graph.addVertex(v1);
        otherGraph.addVertex(v1);

        assertEquals(graph.hashCode(), otherGraph.hashCode(),
            "Hash codes of equal graphs must match");
    }

    @Test
    void testToStringFormat() {
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v1, v2);

        String result = graph.toString();

        assertTrue(result.contains(v1.toString()), "toString should contain vertex data");
        assertTrue(result.contains(v2.toString()), "toString should contain vertex data");
        assertTrue(result.contains("->"), "toString should contain '->' separator");
    }

    @Test
    void testAbstractGraphIODelegation(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("graph_delegation_test.txt");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v1, v2);

        graph.saveToFile(file, data -> data);
        assertTrue(Files.exists(file), "saveToFile should create a file");

        Graph<String> loadedGraph = createGraph();
        loadedGraph.loadFromFile(file, str -> str);

        assertEquals(graph, loadedGraph,
            "Graph loaded from file should equal the original graph");
    }
}