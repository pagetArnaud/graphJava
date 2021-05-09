package graph;

public class UndirectedEdge extends Edge {


    /**
     *
     * @param id unique to each Edge
     * @param color
     * @param v1
     * @param v2
     * @param value
     */
    public UndirectedEdge(int id, String color,Vertex v1,Vertex v2, double value) {
        super(id, color, new Vertex[]{v1, v2}, value);
    }

    @Override
    public String toString() {
        Vertex[] e=getEnds();
        return e[0]+"---"+e[1];
    }
}
