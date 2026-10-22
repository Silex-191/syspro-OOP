package mypackage.graph;

import java.util.Objects;

/**
 * Represents a directed edge in a graph.
 *
 * @param <T> the type of data stored in the vertices
 */
public class Edge<T> {
    private final Vertex<T> source;
    private final Vertex<T> target;

    /**
     * Constructs a directed edge.
     *
     * @param source the starting vertex
     * @param target the ending vertex
     */
    public Edge(Vertex<T> source, Vertex<T> target) {
        this.source = source;
        this.target = target;
    }

    /**
     * Gets the source vertex of this edge.
     *
     * @return the source vertex
     */
    public Vertex<T> getSource() {
        return source;
    }

    /**
     * Gets the target vertex of this edge.
     *
     * @return the target vertex
     */
    public Vertex<T> getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(source, edge.source)
            && Objects.equals(target, edge.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }

    @Override
    public String toString() {
        return source.getId() + ";" + target.getId();
    }
}