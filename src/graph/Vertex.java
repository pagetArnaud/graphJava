package graph;

public class Vertex {
    private int id;
    private Object info;
    private String color;

    /**
     *
     * @param id unique to each vertex
     * @param info
     * @param color
     */

    public Vertex(int id, Object info, String color) {
        this.id = id;
        this.info = info;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return ""+id;
    }
}
