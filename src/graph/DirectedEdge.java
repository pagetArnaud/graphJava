package graph;

public class DirectedEdge extends Edge {
    private int source;


    /**
     *
     * @param id unique to each Edge
     * @param color
     * @param Vsource Vertex who is the source of the Edge
     * @param Vsink Vertex who is the sink of the Edge
     * @param value
     */
    public DirectedEdge(int id, String color,Vertex Vsource,Vertex Vsink, double value) {
        super(id, color, new Vertex[]{Vsource, Vsink}, value);
        this.source = Vsource.getId();
    }

    public Vertex getSource() {
        Vertex[] e=getEnds();
        if (e[0].getId()==source){
            return e[0];
        }else {
            return e[1];
        }
    }
    public Vertex getSink(){
        Vertex[] e=getEnds();
        if (e[0].getId()==source){
            return e[1];
        }else {
            return e[0];
        }
    }

    @Override
    public String toString() {
        return getSource()+"-->"+getSink();
    }
}
