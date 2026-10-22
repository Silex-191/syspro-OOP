package mypackage.graph;

class AdjacencyMatrixGraphTest extends GraphTest {
    @Override
    protected Graph<String> createGraph() {
        return new AdjacencyMatrixGraph<>();
    }
}