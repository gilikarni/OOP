import java.util.*;

/**
 * A Simulator simulates a system of pipes and filters using a BipartiteGraph.
 */
public class Simulator<T, S>{
    /*
    Abstraction function:
    Simulator enables simulating a system of Simulatable pipes and filters, by running the function
    simulate on each one of them in every step - first on the pipes, and then the filters.
    The pipes and filters are stored as vertexes of a BipartiteGraph - pipes being the black vertexes,
    filters being the white vertexes.

    Representation invariant:
    graph != null &&
    graph black vertexes are all pipes &&
    graph white vertexes are all filters
    */
    private BipartiteGraph<T> graph;

    /**
     * @effects create a Simulator
     */
    public Simulator(BipartiteGraph<T> graph) {
        this.graph = graph;
        checkRep();
    }

    /**
     * @effects create a Simulator
     */
    public Simulator() {
        this.graph = new BipartiteGraph<>();
        checkRep();
    }

    protected void checkRep() {
        assert graph != null: "Simulator does not hold graph";
        for (ColoredVertex<T> vertex : graph.getListOfWhiteVertexes()){
            assert vertex instanceof Filter: "a vertex in whiteVertexes is not a filter";
        }
        for (ColoredVertex<T> vertex : graph.getListOfBlackVertexes()){
            assert vertex instanceof Pipe: "a vertex in blackVertexes is not a pipe";
        }
    }

    /**
     * @effects adds a pipe to the graph
     */
    public void addPipe(Pipe<T, S> pipe) throws IllegalArgumentException{
        graph.addVertex(pipe);
        checkRep();
    }

    /**
     * @effects adds a filter to the graph
     */
    public void addFilter(Filter<T, S> filter) throws IllegalArgumentException{
        graph.addVertex(filter);
        checkRep();
    }

    /**
     * @effects adds an edge to the graph
     */
    public void addEdge(T sourceVertex, T targetVertex, T edgeLabel) throws IllegalArgumentException{
        graph.addEdge(sourceVertex, targetVertex, edgeLabel);
        checkRep();
    }

    /**
     * @returns return a copy of the filter holding given Label
     */

    /**
     * @effects adds a S workobject to the T labeled pipe
     * @throws IllegalArgumentException if no pipe exists with label pipeName or if pipe is full.
     */
    public void addWorkObject(T pipeName, S workobject) throws IllegalArgumentException {
        Pipe<T,S> pipe = getPipe(pipeName);
        if (!pipe.addWorkObject(workobject)){
            throw new IllegalArgumentException("pipe with pipeName is full");
        }
    }

    /**
     *@returns  a list of all of the edges labels
     */
    public List<T> getEdges(){
        return graph.getEdges();
    }

    /**
     * @modifies this, graph
     * @effects Simulates this pipe or filter in a system modeled by graph
     */
    public void simulate() {
        for (ColoredVertex<T> vertex : graph.getListOfBlackVertexes()) {
            Pipe<T,S> pipe = (Pipe<T,S>)vertex;
            pipe.simulate(graph);
        }
        for (ColoredVertex<T> vertex: graph.getListOfWhiteVertexes()) {
            Filter<T,S> filter = (Filter<T,S>)vertex;
            filter.simulate(graph);
        }
    }

    /**
     *@returns a list of the contents of the pipe with the label pipeName
     *@throws IllegalArgumentException if no pipe with label pipeName exists.
     */
    public List<S> getPipeContents(T pipeName) throws IllegalArgumentException{
        Pipe<T,S> pipe = getPipe(pipeName);
        ArrayList<S> contents = new ArrayList<>(pipe.workingObjects);
        return contents;
    }

    /**
     *@returns a the filter with the label filterName
     *@throws IllegalArgumentException if no filter with label filterName exists.
     */
    public Filter<T,S> getFilter(T filterName){
        ColoredVertex<T> vertex = graph.getVertex(filterName);
        if (!(vertex instanceof Filter)){
            throw new IllegalArgumentException("vertex with label filterName is not a filter");
        }
        return (Filter<T,S>)vertex;
    }

    /**
     *@returns the pipe with the label pipeName
     *@throws IllegalArgumentException if no pipe with label pipeName exists.
     */
    private Pipe<T,S> getPipe(T pipeName){
        ColoredVertex<T> vertex = graph.getVertex(pipeName);
        if (!vertex.isVertexBlack()){
            throw new IllegalArgumentException("vertex with label pipeName is not a pipe");
        }
        return (Pipe<T,S>)vertex;
    }

}
