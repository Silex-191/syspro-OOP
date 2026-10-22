package mypackage.sort;

import mypackage.graph.AdjacencyListGraph;
import mypackage.graph.Graph;
import mypackage.graph.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DfsSortTest {

    private Graph<String> graph;
    private TopologicalSort<String> sorter;
    private Vertex<String> v1;
    private Vertex<String> v2;
    private Vertex<String> v3;
    private Vertex<String> v4;

    @BeforeEach
    void setUp() {
        graph = new AdjacencyListGraph<>();
        sorter = new DfsSort<>();

        v1 = new Vertex<>(1, "A");
        v2 = new Vertex<>(2, "B");
        v3 = new Vertex<>(3, "C");
        v4 = new Vertex<>(4, "D");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
    }

    @Test
    void testValidTopologicalSort() {
        graph.addEdge(v1, v2);
        graph.addEdge(v1, v3);
        graph.addEdge(v2, v4);
        graph.addEdge(v3, v4);

        List<Vertex<String>> sorted = sorter.sort(graph);

        assertEquals(4, sorted.size());
        assertEquals(v1, sorted.get(0));
        assertEquals(v4, sorted.get(3));
    }

    @Test
    void testGraphWithCycleThrowsException() {
        graph.addEdge(v1, v2);
        graph.addEdge(v2, v3);
        graph.addEdge(v3, v1);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            sorter.sort(graph);
        });

        assertTrue(exception.getMessage().contains("cycle"));
    }

    @Test
    void testGraphWithSelfLoopThrowsException() {
        graph.addEdge(v1, v2);
        graph.addEdge(v2, v2);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            sorter.sort(graph);
        });

        assertTrue(exception.getMessage().contains("cycle"),
            "Self-loop is a cycle and should trigger a cycle exception");
    }
}