package graphImpl;

import graph.Graph;
import graph.Vertex;

import java.util.Arrays;

public class TestGraphImpl {

    public static void main(String[] args) {
        IncidenceArrayGraph graph=new IncidenceArrayGraph(10,10, Graph.EdgeKind.DIRECTED);
        Vertex v1=new Vertex(1,null,"re");
        Vertex v2=new Vertex(2,null,"re");
        Vertex v3=new Vertex(3,null,"re");
        graph.addVertex(v1);

        graph.addEdge(v1,v3, Graph.EdgeKind.DIRECTED);
        graph.addEdge(v1,v3, Graph.EdgeKind.DIRECTED);
        graph.addEdge(v2,v3, Graph.EdgeKind.UNDIRECTED);
        System.out.println("List of edges: "+Arrays.toString(graph.getEdges()));
        System.out.println("List of vertices: "+Arrays.toString(graph.getVertices()));
        System.out.println("Is the graph connected ? "+graph.isConnected());
        System.out.println("Is the V1 connected to V2 ? "+graph.isConnected(v1,v2));
        System.out.println("Is the V1 connected to V3 ? "+graph.isConnected(v1,v3));
        System.out.println("Is the V3 connected to V2 ? "+graph.isConnected(v3,v2));
        System.out.println("List of edges connected to V1 "+Arrays.toString(graph.getNeighborEdges(v1)));
        System.out.println("List of edges commom with V1 and V3 "+Arrays.toString(graph.getEdges(v1, v3)));

    }
}
