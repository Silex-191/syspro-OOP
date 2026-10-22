package mypackage.graph;

class AdjacencyMatrixGraphTest extends GraphTestBase {
    @Override
    protected Graph<String> createGraph() {
        return new AdjacencyMatrixGraph<>();
    }
}