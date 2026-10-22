package mypackage.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {

    @Test
    void testVertexCreationAndGetters() {
        Vertex<String> v = new Vertex<>(1, "A");
        assertEquals(1, v.getId());
        assertEquals("A", v.getData());
    }

    @Test
    void testEqualsAndHashCode() {
        Vertex<String> v1 = new Vertex<>(1, "A");
        Vertex<String> v2 = new Vertex<>(1, "A");
        Vertex<String> v3 = new Vertex<>(1, "B");
        Vertex<String> v4 = new Vertex<>(2, "A");

        assertEquals(v1, v2, "Vertices with the same id and data should be equal");
        assertNotEquals(v1, v3, "Vertices with different data should not be equal");
        assertNotEquals(v1, v4, "Vertices with different ids should not be equal");
        assertEquals(v1.hashCode(), v2.hashCode(),
            "HashCodes should match for equal vertices");
    }

    @Test
    void testToString() {
        Vertex<String> v = new Vertex<>(5, "Data");
        assertEquals("5;Data", v.toString());
    }
}