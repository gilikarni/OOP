import java.util.*;

/**
 * A BipartiteGraph is a graph that has two sets of vertexes - black and white.
 * There are edges only between vertexes from different sets.
 * T is the label of a vertex, T is a label of an edge.
 */
public class BipartiteGraph<T>{
    // A map from vertex label to V ColoredVertex
    private HashMap<T, ColoredVertex<T>> vertexes;

    // A list containing all the black vertexes in the graph
    private HashSet<T> blackVertexes;

    // A list containing all the white vertexes in the graph
    private HashSet<T> whiteVertexes;

    /*
    Abstraction function:
    BipartiteGraph b is a graph with Vertexes that has unique labels T and edges with unique labels T. b is a bipartite
    graph which means it has two sets of vertexes: white and black and edges can connect only vertexes from different
    sets. The graph supports adding new vertexes and connect vertexes from different sets.

    Representation invariant:
        vertexes != null && blackVertexes != null && whiteVertexes != null &&
        There is no edge between two vertexes with the same color &&
        There no two vertexes with the same label &&
        There are no white vertexes in the blackVertexes list &&
        There are no black vertexes in the whiteVertexes list
     */
    private boolean isContainsColor(Collection<T> collectionToCheck, ColoredVertex.VertexColor color) {
        for (T vertexLabel: collectionToCheck) {
            if (vertexes.get(vertexLabel).getVertexColor() == color){
                return true;
            }
        }
        return false;
    }

    private boolean isVertexMapEqualsColorSetsAndColorSetsContainAlienLabels() {
        HashSet<T> unitedColorSet = new HashSet<>();
        HashSet<T> vertexesSet = new HashSet<>(vertexes.keySet());

        if (!unitedColorSet.addAll(blackVertexes)){
            return false;
        }
        if (!unitedColorSet.addAll(whiteVertexes)){
            return false;
        }
        if (!vertexesSet.equals(unitedColorSet)){
            return false;
        }
        return true;
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

    private void checkRep() {
        assert vertexes != null && blackVertexes != null && whiteVertexes != null :
                "One of the class fields has null value";
        assert !isContainsColor(blackVertexes, ColoredVertex.VertexColor.WHITE) :
                "There is a white vertex in the blackVertexes collection";
        assert !isContainsColor(whiteVertexes, ColoredVertex.VertexColor.BLACK) :
                "There is a white vertex in the blackVertexes collection";
        assert isVertexMapEqualsColorSetsAndColorSetsContainAlienLabels() :
                "Either there is the same vertex label in black and white sets or black and white list and vertex" +
                        " lists aren't equal";
        assert !isEdgesConnectingSameColorVertexes() : "Some edges connect vertexes from the same color";
    }

    /**
     * @effects create an empty graph
     */
    public BipartiteGraph() {
        this.vertexes = new HashMap<>();
        this.blackVertexes = new HashSet<>();
        this.whiteVertexes = new HashSet<>();

        checkRep();
    }

    /**
     * @modifies this
     * @effects create a new white vertex with the label vertexLabel.
     * @throws IllegalArgumentException if there is already a vertex with this label
     */
    public void addWhiteVertex(T vertexLabel) throws IllegalArgumentException{
        if (!vertexes.containsKey(vertexLabel)) {
            throw new IllegalArgumentException("Vertex with the same label already exist");
        }

        ColoredVertex<T> vertex = new ColoredVertex<>(vertexLabel, ColoredVertex.VertexColor.WHITE);
        vertexes.put(vertexLabel, vertex);
        whiteVertexes.add(vertexLabel);

        checkRep();
    }

    /**
     * @modifies this
     * @effects create a new black vertex with the label vertexLabel.
     * @throws IllegalArgumentException if there is already a vertex with this label
     */
    public void addBlackVertex(T vertexLabel) throws IllegalArgumentException{
        if (!vertexes.containsKey(vertexLabel)) {
            throw new IllegalArgumentException("Vertex with the same label already exist");
        }

        ColoredVertex<T> vertex = new ColoredVertex<>(vertexLabel, ColoredVertex.VertexColor.BLACK);
        vertexes.put(vertexLabel, vertex);
        blackVertexes.add(vertexLabel);

        checkRep();
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

        source.addChild(edgeLabel, targetVertex);
        target.addParent(edgeLabel, sourceVertex);

        checkRep();
    }

    /**
     * @return a list of all the black vertexes labels in the graph or an empty list iff there are no black vertexes in
     * the graph
     */
    public List<T> getListOfBlackVertexes(){
        return new ArrayList<>(blackVertexes);
    }

    /**
     * @return a list of all the white vertexes labels in the graph or an empty list iff there are no white vertexes in
     * the graph
     */
    public List<T> getListOfWhiteVertexes() {
        return new ArrayList<>(whiteVertexes);
    }

    /**
     * @return a list of the parents labels of the vertex with the label vertexLabel
     * @throws IllegalArgumentException if there is no vertex with the label vertexLabel
     */
    public List<T> getListOfVertexParents(T vertexLabel) throws IllegalArgumentException{
        return vertexes.get(vertexLabel).getParentsList();
    }

    /**
     * @return a list of the children labels of the vertex with the label vertexLabel
     * @throws IllegalArgumentException if there is no vertex with the label vertexLabel
     */
    public List<T> getListOfVertexChildren(T vertexLabel) throws IllegalArgumentException{
        return vertexes.get(vertexLabel).getChildrenList();
    }

    /**
     * @return the label of the child vertex
     * @throws IllegalArgumentException if the vertex doesn't exist or the vertex doesn't have a
     * child with the label edgeLabel.
     */
    public T getChildByEdgeLabel(T parentLabel, T edgeLabel) throws IllegalArgumentException {
        return vertexes.get(parentLabel).getChildLabelByEdgeLabel(edgeLabel);
    }

    /**
     * @return the label of the parent vertex
     * @throws IllegalArgumentException if the vertex doesn't exist or the vertex doesn't have a
     * parent with the label edgeLabel.
     */
    public T getParentByEdgeLabel(T childLabel, T edgeLabel) throws IllegalArgumentException{
        return vertexes.get(childLabel).getParentLabelByEdgeLabel(edgeLabel);
    }
}