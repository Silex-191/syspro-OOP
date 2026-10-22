package mypackage.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

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
}