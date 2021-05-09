package graphImpl;

import graph.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class IncidenceArrayGraph implements Graph {

    private Vertex[] vertices;
    private Edge[] edges;
    private Edge[][] incident;
    private int vertexIndice;
    private int edgeIndice;
    private EdgeKind kind;

    /***
     *
     * @param maxVertex maximum vertices of this graph
     * @param maxEdges maximum edges of this graph
     * @param kind kind of edge that can be in this graph (directed or undirected)
     */
    public IncidenceArrayGraph(int maxVertex, int maxEdges, EdgeKind kind) {
        vertices = new Vertex[maxVertex];
        edges = new Edge[maxEdges];
        incident = new Edge[maxVertex][maxEdges];
        vertexIndice = 0;
        edgeIndice = 0;
        this.kind = kind;

    }

    @Override
    public int nbOfVertices() {
        return vertexIndice;
    }

    @Override
    public int nbOfEdges() {
        return edgeIndice;
    }

    /**
     *
     * @param v add v in the graph only if the graph contain less than the maximum vertice authorized
     */
    @Override
    public void addVertex(Vertex v) {
        if (vertexIndice < vertices.length) {
            vertices[vertexIndice] = v;
            vertexIndice++;
        }
    }

    /***
     *
     * @param v vertex to find
     * @return return the index of the vertex, or -1 if not find
     */
    private int findIndiceVertex(Vertex v) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null && vertices[i].getId() == v.getId()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param vertexIndice index of the associated vertex in the matrix
     * @param e            the edge that we connect to the vertex
     * @return true if fine
     */
    private boolean insertInIncident(int vertexIndice, Edge e) {
        for (int i = 0; i < incident[vertexIndice].length; i++) {
            if (incident[vertexIndice][i] == null) {
                incident[vertexIndice][i] = e;
                return true;
            }
        }
        return false;
    }

    /***
     *
     * @param v1 the first Vertex (source if EdgeKind is directed)
     * @param v2 the second Vertex (sink if EdgKind is directed)
     * @param kind 'd' for directed 'u' for undirected
     */
    @Override
    public void addEdge(Vertex v1, Vertex v2, EdgeKind kind) {
        if (kind != this.kind) {
            System.out.println("Error EdgeKind is not the same as the graph");
            return;
        }

        if (edgeIndice < edges.length) {
            int indexV1 = findIndiceVertex(v1);
            int indexV2 = findIndiceVertex(v2);
            if (indexV1 == -1) {
                //TODO check if c'est inseré correctement
                indexV1 = vertexIndice;
                addVertex(v1);
                if (vertexIndice == indexV1) {
                    System.out.println("You cannot add more Vertex in this graph");
                    return;
                }
            }
            if (indexV2 == -1) {
                indexV2 = vertexIndice;
                addVertex(v2);
                if (vertexIndice == indexV1) {
                    System.out.println("You cannot add more Vertex in this graph");
                    return;
                }
            }

            switch (this.kind) {

                case DIRECTED:
                    edges[edgeIndice] = new DirectedEdge(edgeIndice, "blue", v1, v2, 39);
                    insertInIncident(indexV1, edges[edgeIndice]);
                    insertInIncident(indexV2, edges[edgeIndice]);
                    edgeIndice++;
                    break;
                case UNDIRECTED:
                    edges[edgeIndice] = new UndirectedEdge(edgeIndice, "red", v1, v2, 93);
                    insertInIncident(indexV1, edges[edgeIndice]);
                    insertInIncident(indexV2, edges[edgeIndice]);

                    edgeIndice++;
                    break;
                default:
                    break;
            }


        } else {
            System.out.println("Error you can't add more edges to this graph");
        }
    }

    /***
     *
     * @param v1 vertex
     * @param v2 vertex
     * @return true of those vertices are connected
     */
    @Override
    public boolean isConnected(Vertex v1, Vertex v2) {

        int indexV1 = findIndiceVertex(v1);
        int indexV2 = findIndiceVertex(v2);
        if (indexV1 == -1) {
            return false;
        }
        if (indexV2 == -1) {
            return false;
        }
        if (indexV1 == indexV2) {
            return true;
        }
        Edge[] e1 = incident[indexV1];

        for (int i = 0; i < e1.length; i++) {
            if (e1[i] != null) {
                Vertex tempv1 = e1[i].getEnds()[0];
                Vertex tempv2 = e1[i].getEnds()[1];

                if (tempv1 == v2 || tempv2 == v2) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     *
     * @return true if  all vertices are interconnected
     */
    @Override
    public boolean isConnected() {
        int i = 0;
        boolean notconnected = true;
        while (notconnected && i < vertexIndice) {
            notconnected = isConnected(vertices[0], vertices[i]);
            i++;
        }
        return notconnected;
    }

    /**
     *
     * @param v1 vertex
     * @param v2 vertex
     * @return give edge(s) connecting these vertices
     */
    @Override
    public Edge[] getEdges(Vertex v1, Vertex v2) {
        int idxv1 = findIndiceVertex(v1);
        int idxv2 = findIndiceVertex(v2);
        if(idxv1==-1 || idxv2==-1){
            System.out.println("You gave a vertex that is not in the graph");
            return null;
        }
        Edge[] output = new Edge[incident[idxv1].length];
        int i = 0;
        for (Edge e : incident[idxv1]) {
            for (Edge e2 : incident[idxv2]) {
                if (e != null && e == e2) {
                    output[i] = e;
                    i++;
                }
            }
        }
        output= Arrays.copyOfRange(output,0,i);
        return output;
    }

    /**
     *
     * @return all edges of the graph
     */
    @Override
    public Edge[] getEdges() {

        return Arrays.copyOfRange(edges,0,edgeIndice);
    }

    /**
     *
     * @return all vertices of the graph
     */
    @Override
    public Vertex[] getVertices() {
        return Arrays.copyOfRange(vertices,0,vertexIndice);
    }

    /**
     *
     * @param v a vertice of the graph
     * @return all edges connected to this vertex
     */
    @Override
    public Edge[] getNeighborEdges(Vertex v) {
        int idx = findIndiceVertex(v);
        if (idx == -1) {
            return null;
        }
        return incident[idx].clone();
    }
}
