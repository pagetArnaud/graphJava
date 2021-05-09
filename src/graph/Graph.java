package graph;

public interface Graph {

enum EdgeKind{
    DIRECTED,
    UNDIRECTED
}


    int nbOfVertices();

    int nbOfEdges();

    void addVertex(Vertex v);

    /**
     *
     * @param v1 the first Vertex (source if EdgeKind is directed)
     * @param v2 the second Vertex (sink if EdgKind is directed)
     * @param kind 'd' for directed 'u' for undirected
     */
    void addEdge(Vertex v1, Vertex v2,EdgeKind kind);

    /**
     *
     * @param v1
     * @param v2
     * @return true if those are connected
     */
    boolean isConnected(Vertex v1, Vertex v2);

    /**
     *
     * @return says whether all vertices are interconnecte
     */
    boolean isConnected();

    /**
     *
     * @param v1
     * @param v2
     * @return edge(s) connecting these vertice
     */
    Edge[] getEdges(Vertex v1, Vertex v2);

    /**
     *
     * @return give all edges of the graph
     */
    Edge[] getEdges();

    /**
     *
     * @return give all vertices of the graph
     */
    Vertex[] getVertices();

    /**
     *
     * @param v
     * @return give edges connected to this vertex
     */
    Edge[] getNeighborEdges(Vertex v);
}
