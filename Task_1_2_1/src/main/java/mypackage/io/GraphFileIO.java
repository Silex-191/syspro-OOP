package mypackage.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import mypackage.graph.Edge;
import mypackage.graph.Graph;
import mypackage.graph.Vertex;

/**
 * Utility class for reading and writing graphs to and from files.
 */
public class GraphFileIO {

    /**
     * Loads a graph from a specified text file.
     *
     * @param graph  the target graph to populate
     * @param path   the path of the file
     * @param parser a function to parse the payload string into type T
     * @param <T>    the data type of the graph
     * @throws IOException if an error occurs during reading or parsing
     */
    public static <T> void load(Graph<T> graph, Path path, Function<String, T> parser)
        throws IOException {
        List<String> lines = Files.readAllLines(path);
        Map<Integer, Vertex<T>> mapVertex = new HashMap<>();
        int i = 0;

        while (i < lines.size() && !lines.get(i).trim().isEmpty()) {
            String[] parts = lines.get(i).split(";", 2);
            if (parts.length != 2) {
                throw new IOException("Invalid format for vertex on line " + (i + 1));
            }

            int key = Integer.parseInt(parts[0]);
            Vertex<T> vertex = new Vertex<>(key, parser.apply(parts[1]));

            if (!graph.addVertex(vertex)) {
                throw new IOException("Duplicate vertex key exists: " + key);
            }
            mapVertex.put(key, vertex);
            i++;
        }

        while (i < lines.size() && lines.get(i).trim().isEmpty()) {
            i++;
        }

        while (i < lines.size() && !lines.get(i).trim().isEmpty()) {
            String[] parts = lines.get(i).split(";");
            if (parts.length != 2) {
                throw new IOException("Invalid format for edge on line " + (i + 1));
            }

            Vertex<T> vertexFrom = mapVertex.get(Integer.parseInt(parts[0]));
            Vertex<T> vertexTo = mapVertex.get(Integer.parseInt(parts[1]));

            if (vertexFrom == null || vertexTo == null || !graph.addEdge(vertexFrom, vertexTo)) {
                throw new IOException("Error adding edge: " + parts[0] + " -> " + parts[1]);
            }
            i++;
        }
    }

    /**
     * Saves a graph to a specified text file.
     *
     * @param graph     the graph to save
     * @param path      the destination path
     * @param formatter a function to format the payload type T into a string
     * @param <T>       the data type of the graph
     * @throws IOException if an error occurs during writing
     */
    public static <T> void save(Graph<T> graph, Path path, Function<T, String> formatter)
        throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Vertex<T> vertex : graph.getVertices()) {
                writer.write(vertex.getId() + ";" + formatter.apply(vertex.getData()));
                writer.newLine();
            }

            writer.newLine();

            for (Edge<T> edge : graph.getEdges()) {
                writer.write(edge.toString());
                writer.newLine();
            }
        }
    }
}