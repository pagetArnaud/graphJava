package graph;

public abstract class Edge {
    private int id;
    private String color;
    private Vertex[] ends;
    private double value;

    /**
     *
     * @param id unique to each Edge
     * @param color
     * @param ends
     * @param value
     */
    public Edge(int id, String color, Vertex[] ends, double value) {
        this.id = id;
        this.color = color;
        this.ends = ends;
        this.value = value;
    }

    public Vertex[] getEnds() {
        return ends;
    }
}
