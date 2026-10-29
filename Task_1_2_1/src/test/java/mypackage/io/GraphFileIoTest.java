package mypackage.io;

import mypackage.graph.AdjacencyListGraph;
import mypackage.graph.Graph;
import mypackage.graph.Vertex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class GraphFileIoTest {

    @Test
    void testSaveAndLoadGraph(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("graph.txt");

        Graph<String> originalGraph = new AdjacencyListGraph<>();
        Vertex<String> v1 = new Vertex<>(1, "Apple");
        Vertex<String> v2 = new Vertex<>(2, "Banana");

        originalGraph.addVertex(v1);
        originalGraph.addVertex(v2);
        originalGraph.addEdge(v1, v2);

        originalGraph.saveToFile(file, data -> data);

        assertTrue(Files.exists(file), "File must be created");

        Graph<String> loadedGraph = new AdjacencyListGraph<>();
        loadedGraph.loadFromFile(file, str -> str);

        assertEquals(originalGraph, loadedGraph);
    }

    @Test
    void testLoadInvalidFormat(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("invalid.txt");
        Files.writeString(file, "Invalid string format without semicolons");

        Graph<String> graph = new AdjacencyListGraph<>();

        assertThrows(IOException.class, () -> {
            graph.loadFromFile(file, str -> str);
        });
    }
}