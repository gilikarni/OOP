import java.util.*;

/**
 * A ColoredVertex is a vertex that has label T and a color VertexColor.
 * A ColorVertex holds data regarding his parents and children labels T, and his
 * outgoing and incoming edges labels T
 */
public class ColoredVertex<T> {
    /*
    Abstraction function:
    A ColoredVertex is a vertex that has label T and a color VertexColor.
    A ColorVertex holds data regarding his parents and children labels T, and his
    outgoing and incoming edges labels T.
    A ColoredVertex supports adding parents and children by label T, checking for
    existence of parents and children by label T, getting parent or child by label T of
    connecting edge and getting lists of parents and children.

    Representation invariant:
    label != null && VertexColor != null &&
    There are no two outgoing edges with same label T &&
    There are no two incoming edges with same label T &&
    There are no null labeled connections &&
    There are no null labeled parents or children &&
    There is only one edge to each parent &&
    There is only one edge to each child
    */

    private T label;
    private HashMap<T, T> parents; // maps edge label to vertex label
    private HashMap<T, T> children; // maps edge label to vertex label
    public enum VertexColor {
        WHITE, BLACK
    };
    private VertexColor color;

    private boolean isDuplicates(Collection<T> collectionToCheck) {
        Set<T> set = new HashSet<>();
        for (T value : collectionToCheck) {
            if (!set.add(value)){
                return true;
            }
        }
        return false;
    }

    private void checkRep() {
        assert label != null && color != null:
                "One of the class fields has null value";
        // incoming and outgoing edges uniqueness asserted by HashMap single value for each key
        assert !parents.containsValue(null) : "vertex contains null parent";
        assert !children.containsValue(null) : "vertex contains null child";
        assert !parents.containsKey(null) : "vertex contains null incoming edge";
        assert !children.containsKey(null) : "vertex contains null outgoing edge";
        assert !isDuplicates(parents.values()) : "vertex contains two incoming edges from the same vertex";
        assert !isDuplicates(children.values()) : "vertex contains two outgoing edges to the same vertex";
    }

    /**
     * Create a new vertex for the graph, each vertex has color - black or white
     * The parents and children maps are empty
     */
    public ColoredVertex(T label, VertexColor color) {
        this.color = color;
        this.label = label;
        parents = new HashMap<>();
        children = new HashMap<>();
        checkRep();
    }

    /**
     * @modifies this
     * @effects Add a new parent to this vertex
     * @throws IllegalArgumentException if there is another parent with the same edge label
     */
    public void addParent(T edgeLabel, T parentLabel) throws IllegalArgumentException {
        if (hasIncomingEdge(edgeLabel)) {
            throw new IllegalArgumentException("A edge with the same label already exist.");
        }
        if (hasParent(parentLabel)) {
            throw new IllegalArgumentException("A parent with the same label already exist.");
        }
        parents.put(edgeLabel, parentLabel);
        checkRep();
    }

    /**
     * @modifies this
     * @effects Add a new child to this vertex
     * @throws IllegalArgumentException if there is another child with the same edge label
     */public void addChild(T edgeLabel, T childLabel) throws IllegalArgumentException {
        if (hasOutgoingEdge(edgeLabel)) {
            throw new IllegalArgumentException("A edge with the same label already exist.");
        }
        if (hasChild(childLabel)) {
            throw new IllegalArgumentException("A child with the same label already exist.");
        }

        children.put(edgeLabel, childLabel);
        checkRep();
    }

    /**
     * @effects Check if the Vertex has a parent with label T vertexLabel
     * @return true if the vertex has a parent with the label T vertexLabel
     */
    public boolean hasParent(T vertexLabel) {
        return parents.containsValue(vertexLabel);
    }

    /**
     * @effects Check if the Vertex has a child with label T vertexLabel
     * @return true if the vertex has a parent with the label T vertexLabel
     */
    public boolean hasChild(T vertexLabel) {
        return children.containsValue(vertexLabel);
    }

    /**
     * @effects Check if the Vertex has an incoming Edge with label T edgeLabel
     * @return true if the Vertex has an incoming Edge with label T edgeLabel
     */
    public boolean hasIncomingEdge(T edgeLabel) {
        return parents.containsKey(edgeLabel);
    }

    /**
     * @effects Check if the Vertex has an outgoing Edge with label T edgeLabel
     * @return true if the Vertex has an outgoing Edge with label T edgeLabel
     */
    public boolean hasOutgoingEdge(T edgeLabel) {
        return children.containsKey(edgeLabel);
    }

    /**
     * @return return true if the vertex is black
     */
    public boolean isVertexBlack() {
        return color == VertexColor.BLACK;
    }

    /**
     * @return return color of vertex
     */
    public VertexColor getVertexColor() {
        return color;
    }

    /**
     * @return return true if the vertex is white
     */
    public boolean isVertexWhite() {
        return color == VertexColor.WHITE;
    }

    /**
     * @return A list with all the vertexes parents
     */
    public ArrayList<T> getParentsList() {
        ArrayList<T> parentsList = new ArrayList<>(parents.values());
        return parentsList;
    }

    /**
     * @return A list with all the vertexes parents
     */
    public ArrayList<T> getChildrenList() {
        ArrayList<T> childrenList = new ArrayList<>(children.values());
        return childrenList;
    }

    /**
     * @return the label of the parent connected with the edge with the label edge label
     * @throws IllegalArgumentException if there is no parent connected with this vertex
     */
    public T getParentLabelByEdgeLabel(T edgeLabel) throws IllegalArgumentException {
        if (!parents.containsKey(edgeLabel)) {
            throw new IllegalArgumentException("No parent with this edge label exist");
        }

        return parents.get(edgeLabel);
    }

    /**
     * @return the label of the child connected with the edge with the label edge label
     * @throws IllegalArgumentException if there is no child connected with this vertex
     */
    public T getChildLabelByEdgeLabel(T edgeLabel) throws IllegalArgumentException {
        if (!children.containsKey(edgeLabel)) {
            throw new IllegalArgumentException("No child with this edge label exist");
        }

        return children.get(edgeLabel);
    }
}
