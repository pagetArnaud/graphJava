package graph;

public class TestGraph {

    public static void main(String[] args) {
        Vertex v1=new Vertex(1,null,"red");
        Vertex v2=new Vertex(2,null,"blue");
        Vertex v3=new Vertex(3,null,"blue");
        Vertex v4=new Vertex(4,null,"red");
        Edge dirE=new DirectedEdge(1,"red",v1,v3,8);
        Edge unDirE=new UndirectedEdge(2,"blue", v3, v2,3);
        System.out.println(dirE);
        System.out.println(unDirE);
    }
}
