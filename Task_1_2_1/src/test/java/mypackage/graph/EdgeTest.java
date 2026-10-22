package mypackage.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    @Test
    void testEdgeCreationAndGetters() {
        Vertex<String> v1 = new Vertex<>(1, "A");
        Vertex<String> v2 = new Vertex<>(2, "B");
        Edge<String> edge = new Edge<>(v1, v2);

        assertEquals(v1, edge.getSource());
        assertEquals(v2, edge.getTarget());
    }

    @Test
    void testEqualsAndHashCode() {
        Vertex<String> v1 = new Vertex<>(1, "A");
        Vertex<String> v2 = new Vertex<>(2, "B");

        Edge<String> e1 = new Edge<>(v1, v2);
        Edge<String> e2 = new Edge<>(new Vertex<>(1, "A"), new Vertex<>(2, "B"));
        Edge<String> e3 = new Edge<>(v2, v1);

        assertEquals(e1, e2);
        assertNotEquals(e1, e3);
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    void testToString() {
        Vertex<String> v1 = new Vertex<>(10, "A");
        Vertex<String> v2 = new Vertex<>(20, "B");
        Edge<String> edge = new Edge<>(v1, v2);

        assertEquals("10;20", edge.toString());
    }
}