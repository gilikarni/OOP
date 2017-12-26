import java.util.*;

/**
 * A BipartiteGraph is a graph that has two sets of vertexes - black and white.
 * There are edges only between vertexes from different sets.
 * T is the label of a vertex, T is a label of an edge.
 */
public class BipartiteGraph<T>{
    // A map from vertex label to V ColoredVertex
    private HashMap<T, ColoredVertex<T>> vertexes;

    /*
    Abstraction function:
    BipartiteGraph b is a graph with Vertexes that has unique labels T and edges with unique labels T. b is a bipartite
    graph which means it has two sets of vertexes: white and black and edges can connect only vertexes from different
    sets. The graph supports adding new vertexes and connect vertexes from different sets.

    Representation invariant:
    vertexes != null &&
    There is no edge between two vertexes with the same color &&
    There no two vertexes with the same label &&
     */

    /**
     * @effects create an empty graph
     */
    public BipartiteGraph() {
        this.vertexes = new HashMap<>();

        checkRep();
    }

    private void checkRep() {
        assert vertexes != null:
                "One of the class fields has null value";
        assert !vertexes.keySet().contains(null):
                "there is a null vertex in the graph";
        assert !vertexes.values().contains(null) :
                "there is a null edge label in the graph";
        assert !isEdgesConnectingSameColorVertexes() : "Some edges connect vertexes from the same color";
    }

    /**
     *  @return true if collection contains a vertex with given color, false otherwise
     */
    private boolean isContainsColor(Collection<ColoredVertex<T>> collectionToCheck, ColoredVertex.VertexColor color) {
        for (ColoredVertex<T> vertex: collectionToCheck) {
            if (vertex.getVertexColor() == color){
                return true;
            }
        }
        return false;
    }

    private boolean isEdgesConnectingSameColorVertexes() {
        for (ColoredVertex<T> vertex : vertexes.values()) {
            if (isContainsColor(vertex.getParentsList(), vertex.getVertexColor())) {
                return true;
            }
            if (isContainsColor(vertex.getChildrenList(), vertex.getVertexColor())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @modifies this
     * @effects create a new vertex with the label vertexLabel.
     * @throws IllegalArgumentException if there is already a vertex with this label
     */
    public void addVertex(ColoredVertex<T> vertexOriginal) throws IllegalArgumentException{
        ColoredVertex<T> vertex = vertexOriginal;
        T vertexLabel = vertex.getVertexLabel();
        if (vertexes.containsKey(vertexLabel)) {
            throw new IllegalArgumentException("Vertex with the same label already exist");
        }
        vertexes.put(vertexLabel, vertex);
        checkRep();
    }

    /**
     * @returns returns the vertex holding vertex label.
     * @throws IllegalArgumentException if there is no vertex with this label
     */
    public ColoredVertex<T> getVertex(T vertexLabel) throws IllegalArgumentException{
        if (!vertexes.containsKey(vertexLabel)){
            throw new IllegalArgumentException("no vertex with given label exists in graph.");
        }
        return vertexes.get(vertexLabel);
    }


    /**
     * @modifies this
     * @effects add a new edge to the graph from sourceVertex to targetVertex. The new edge label must be unique for
     * both the vertexes. sourceVertex and targetVertex must be from different sets.
     * @throws IllegalArgumentException if the vertex sourceVertex or the vertex targetVertex doesn't exist or if
     * sourceVertex and targetVertex are of the same color or if one of the nodes already have an edge with the same label
     */
    public void addEdge(T sourceVertex, T targetVertex, T edgeLabel) throws IllegalArgumentException{
        if (!vertexes.containsKey(sourceVertex) || !vertexes.containsKey(targetVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist");
        }

        ColoredVertex<T> source = vertexes.get(sourceVertex);
        ColoredVertex<T> target = vertexes.get(targetVertex);

        if ((source.isVertexBlack() && target.isVertexBlack()) || (source.isVertexWhite() && target.isVertexWhite())) {
            throw new IllegalArgumentException("Can't connect two vertexes with the same color");
        }

        if (source.hasOutgoingEdge(edgeLabel) || target.hasIncomingEdge(edgeLabel)) {
            throw new IllegalArgumentException("Edge with the same label already exist in one of the vertexes");
        }

        if (source.hasChild(targetVertex) || target.hasParent(sourceVertex)) {
            throw new IllegalArgumentException("Vertexes are already connected");
        }

        source.addChild(edgeLabel, target);
        target.addParent(edgeLabel, source);

        checkRep();
    }

    /**
     * @return a list of all the black vertexes in the graph or an empty list iff there are no black vertexes in
     * the graph
     */
    public List<ColoredVertex<T>> getListOfBlackVertexes(){
        ArrayList<ColoredVertex<T>> blacks = new ArrayList<>();
        for (ColoredVertex<T> vertex : vertexes.values()){
            if (vertex.getVertexColor().equals(ColoredVertex.VertexColor.BLACK)){
                blacks.add(vertex);
            }
        }
        return blacks;
    }

    /**
     * @return a list of all the white vertexes in the graph or an empty list iff there are no white vertexes in
     * the graph
     */
    public List<ColoredVertex<T>> getListOfWhiteVertexes() {
        ArrayList<ColoredVertex<T>> whites = new ArrayList<>();
        for (ColoredVertex<T> vertex : vertexes.values()){
            if (vertex.getVertexColor().equals(ColoredVertex.VertexColor.WHITE)){
                whites.add(vertex);
            }
        }
        return whites;
    }

    /**
     * @return a list of the parents of the vertex with the label vertexLabel
     * @throws IllegalArgumentException if there is no vertex with the label vertexLabel
     */
    public List<ColoredVertex<T>> getListOfVertexParents(T vertexLabel) throws IllegalArgumentException{
        return vertexes.get(vertexLabel).getParentsList();
    }

    /**
     * @return a list of the children of the vertex with the label vertexLabel
     * @throws IllegalArgumentException if there is no vertex with the label vertexLabel
     */
    public List<ColoredVertex<T>> getListOfVertexChildren(T vertexLabel) throws IllegalArgumentException{
        return vertexes.get(vertexLabel).getChildrenList();
    }

    /**
     * @return the child vertex
     * @throws IllegalArgumentException if the vertex doesn't exist or the vertex doesn't have a
     * child with the label edgeLabel.
     */
    public ColoredVertex<T> getChildByEdgeLabel(T parentLabel, T edgeLabel) throws IllegalArgumentException {
        return vertexes.get(parentLabel).getChildByEdgeLabel(edgeLabel);
    }

    /**
     * @return the parent vertex
     * @throws IllegalArgumentException if the vertex doesn't exist or the vertex doesn't have a
     * parent with the label edgeLabel.
     */
    public ColoredVertex<T> getParentByEdgeLabel(T childLabel, T edgeLabel) throws IllegalArgumentException{
        return vertexes.get(childLabel).getParentByEdgeLabel(edgeLabel);
    }

    /**
     *@returns  a list of all of the edges labels
     */
    public List<T> getEdges(){
        ArrayList<T> result = new ArrayList<>();
        for (ColoredVertex<T> vertex : vertexes.values()){
            result.addAll(vertex.getIncomingEdges());
        }
        return result;
    }
}