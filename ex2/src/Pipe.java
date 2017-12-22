public abstract class Pipe<T> extends ColoredVertex<T> implements Simulatable<T>{
    /*
    Abstraction function:
    Pipe p is a vertex that has a capacity. The capacity is the number of objects that the pipe can handle
    simultaneously.

    Representation invariant:
    All the invariants of vertex &&
    (hasLimit && capacity > 0) || (!hasLimit && capacity == 0)
    */

    private boolean hasLimit = false;
    private int capacity = 0;

    /**
     * Create a new vertex of type Pipe for the graph, each vertex has color - black or white
     * The parents and children maps are empty
     */
    public Pipe(T label, VertexColor color) {
        super(label, color);
    }

    /**
     * Create a new vertex of type Pipe for the graph, each vertex has color - black or white
     * The parents and children maps are empty
     */
    public Pipe(T label, VertexColor color, int capacity) {
        super(label, color);

        hasLimit = true;
        this.capacity = capacity;
    }

    @Override
    protected void checkRep() {
        super.checkRep();
        if (hasLimit) {
            assert capacity > 0 : "A non positive limit is defined";
        } else {
            assert capacity == 0 : "If no limit is defined the capacity should be zero";
        }
    }


    /**
     * @param graph
     */
    @Override
    public abstract void simulate(BipartiteGraph<T> graph);
}
