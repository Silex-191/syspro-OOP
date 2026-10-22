package mypackage.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractGraphTest {

    private AbstractGraph<String> graph1;
    private AbstractGraph<String> graph2;
    private Vertex<String> v1;
    private Vertex<String> v2;

    @BeforeEach
    void setUp() {
        v1 = new Vertex<>(1, "A");
        v2 = new Vertex<>(2, "B");

        graph1 = createDummyGraph();
        graph2 = createDummyGraph();
    }

    private AbstractGraph<String> createDummyGraph() {
        return new AbstractGraph<>() {
            private final List<Vertex<String>> vertices = new ArrayList<>();
            private final List<Edge<String>> edges = new ArrayList<>();

            @Override
            public boolean addVertex(Vertex<String> vertex) {
                if (vertices.contains(vertex)) return false;
                return vertices.add(vertex);
            }

            @Override
            public boolean removeVertex(Vertex<String> vertex) {
                return vertices.remove(vertex);
            }

            @Override
            public boolean addEdge(Vertex<String> from, Vertex<String> to) {
                return edges.add(new Edge<>(from, to));
            }

            @Override
            public boolean removeEdge(Vertex<String> from, Vertex<String> to) {
                return edges.remove(new Edge<>(from, to));
            }

            @Override
            public List<Vertex<String>> getVertices() {
                return vertices;
            }

            @Override
            public List<Edge<String>> getEdges() {
                return edges;
            }

            @Override
            public List<Vertex<String>> getNeighbors(Vertex<String> vertex) {
                List<Vertex<String>> neighbors = new ArrayList<>();
                for (Edge<String> edge : edges) {
                    if (edge.getSource().equals(vertex)) {
                        neighbors.add(edge.getTarget());
                    }
                }
                return neighbors;
            }
        };
    }

    @Test
    void testEqualsCornerCasesAndSuccess() {
        graph1.addVertex(v1);
        graph1.addVertex(v2);
        graph1.addEdge(v1, v2);

        graph2.addVertex(v1);
        graph2.addVertex(v2);
        graph2.addEdge(v1, v2);

        assertNotEquals(null, graph1);
        assertNotEquals(new Object(), graph1);
        assertEquals(graph1, graph2);
    }

    @Test
    void testHashCodeMatchesForEqualGraphs() {
        graph1.addVertex(v1);
        graph2.addVertex(v1);

        assertEquals(graph1.hashCode(), graph2.hashCode());
    }

    @Test
    void testToStringContainsVerticesAndNeighbors() {
        graph1.addVertex(v1);
        graph1.addVertex(v2);
        graph1.addEdge(v1, v2);

        String result = graph1.toString();

        assertTrue(result.contains(v1.toString()));
        assertTrue(result.contains(v2.toString()));
        assertTrue(result.contains("->"));
    }
}