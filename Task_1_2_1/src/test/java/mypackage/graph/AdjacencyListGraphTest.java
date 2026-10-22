package mypackage.graph;

class AdjacencyListGraphTest extends GraphTestBase{
    @Override
    protected Graph<String> createGraph() {
        return new AdjacencyListGraph<>();
    }
}