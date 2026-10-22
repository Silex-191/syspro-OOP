package mypackage.graph;

class IncidenceMatrixGraphTest extends GraphTestBase {
    @Override
    protected Graph<String> createGraph() {
        return new IncidenceMatrixGraph<>();
    }
}