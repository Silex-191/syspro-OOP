package mypackage.graph;

import java.util.Objects;

/**
 * Represents a vertex in a graph.
 *
 * @param <T> the type of data stored in this vertex
 */
public class Vertex<T> {

    private final int id;
    private final T data;

    /**
     * Constructs a new Vertex.
     *
     * @param id   the unique identifier for the vertex
     * @param data the data payload
     */
    public Vertex(int id, T data) {
        this.id = id;
        this.data = data;
    }

    /**
     * Gets the unique identifier of the vertex.
     *
     * @return the vertex ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the data stored in the vertex.
     *
     * @return the vertex data
     */
    public T getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vertex<?> vertex = (Vertex<?>) o;
        return id == vertex.id && Objects.equals(data, vertex.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }

    @Override
    public String toString() {
        return id + ";" + data;
    }
}