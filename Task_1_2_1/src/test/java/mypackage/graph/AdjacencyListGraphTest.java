package mypackage.graph;

class AdjacencyListGraphTest extends GraphTest {
    @Override
    protected Graph<String> createGraph() {
        return new AdjacencyListGraph<>();
    }
}