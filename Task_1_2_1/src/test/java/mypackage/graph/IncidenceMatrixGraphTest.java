package mypackage.graph;

class IncidenceMatrixGraphTest extends GraphTest {
    @Override
    protected Graph<String> createGraph() {
        return new IncidenceMatrixGraph<>();
    }
}