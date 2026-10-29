package mypackage.graph;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Function;
import mypackage.io.GraphFileIo;

/**
 * Abstract base class for Graph implementations providing common equals, hashCode, toString
 * methods, and IO delegation to avoid code duplication.
 *
 * @param <T> the type of data stored in the vertices
 */
public abstract class AbstractGraph<T> implements Graph<T> {

    @Override
    public void loadFromFile(Path path, Function<String, T> parser) throws IOException {
        GraphFileIo.load(this, path, parser);
    }

    @Override
    public void saveToFile(Path path, Function<T, String> formatter) throws IOException {
        GraphFileIo.save(this, path, formatter);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Graph<?> other)) {
            return false;
        }
        return new HashSet<>(this.getVertices()).equals(new HashSet<>(other.getVertices()))
            && new HashSet<>(this.getEdges()).equals(new HashSet<>(other.getEdges()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(new HashSet<>(getVertices()), new HashSet<>(getEdges()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<T> vertex : getVertices()) {
            sb.append(vertex).append(" -> ");
            sb.append(getNeighbors(vertex));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}