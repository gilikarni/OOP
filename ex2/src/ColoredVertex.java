import java.util.*;

/**
 * A ColoredVertex is a vertex that has label T and a color VertexColor.
 * A ColorVertex holds data regarding his parents and children labels T, and his
 * outgoing and incoming edges labels S
 */
public class ColoredVertex<T, S> {
    /*
    Abstraction function:
    A ColoredVertex is a vertex that has label T and a color VertexColor.
    A ColorVertex holds data regarding his parents and children labels T, and his
    outgoing and incoming edges labels S.
    A ColoredVertex supports adding parents and children by label T, checking for
    existence of parents and children by label T, getting parent or child by label S of
    connecting edge and getting lists of parents and children.

    Representation invariant:
    label != null && VertexColor != null &&
    There are no two outgoing edges with same label S &&
    There are no two incoming edges with same label S &&
    There is only one edge to each parent &&
    There is only one edge to each child

    */
    private T label;
    private Map<S, T> parents;
    private Map<S, T> children;
    public enum VertexColor {
        WHITE, BLACK
    };

    VertexColor color;

    /**
     * Create a new vertex for the graph, each vertex has color - black or white
     * The parents and children maps are empty
     */
    public ColoredVertex(T label, VertexColor color) {
        this.color = color;
        this.label = label;
    }

    /**
     * @modifies this
     * @effects Add a new parent to this vertex
     * @throws IllegalArgumentException if there is another parent with the same edge label
     */
    public void addParent(S edgeLabel, T parentLabel) throws IllegalArgumentException {
        if (hasIncomingEdge(edgeLabel)) {
            throw new IllegalArgumentException("A edge with the same label already exist.");
        }
        if (hasParent(parentLabel)) {
            throw new IllegalArgumentException("A parent with the same label already exist.");
        }

        parents.put(edgeLabel, parentLabel);
    }

    /**
     * @modifies this
     * @effects Add a new child to this vertex
     * @throws IllegalArgumentException if there is another child with the same edge label
     */
    public void addChild(S edgeLabel, T childLabel) throws IllegalArgumentException {
        if (hasOutgoingEdge(edgeLabel)) {
            throw new IllegalArgumentException("A edge with the same label already exist.");
        }
        if (hasChild(childLabel)) {
            throw new IllegalArgumentException("A child with the same label already exist.");
        }

        children.put(edgeLabel, childLabel);
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
     * @effects Check if the Vertex has an incoming Edge with label S edgeLabel
     * @return true if the Vertex has an incoming Edge with label S edgeLabel
     */
    public boolean hasIncomingEdge(S edgeLabel) {
        return parents.containsKey(edgeLabel);
    }

    /**
     * @effects Check if the Vertex has an outgoing Edge with label S edgeLabel
     * @return true if the Vertex has an outgoing Edge with label S edgeLabel
     */
    public boolean hasOutgoingEdge(S edgeLabel) {
        return children.containsKey(edgeLabel);
    }

    /**
     * @return return true if the vertex is black
     */
    public boolean isVertexBlack() {
        return color == VertexColor.BLACK;
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
        ArrayList<T> parentsList = new ArrayList<>();
        for(Map.Entry<S, T> entry : parents.entrySet()) {
            parentsList.add(entry.getValue());
        }

        return parentsList;
    }

    /**
     * @return A list with all the vertexes parents
     */
    public ArrayList<T> getChildrenList() {
        ArrayList<T> childrenList = new ArrayList<>();
        for(Map.Entry<S, T> entry : children.entrySet()) {
            childrenList.add(entry.getValue());
        }

        return childrenList;
    }

    /**
     * @return the label of the parent connected with the edge with the label edge label
     * @throws IllegalArgumentException if there is no parent connected with this vertex
     */
    public T getParentLabelByEdgeLabel(S edgeLabel) throws IllegalArgumentException {
        if (!parents.containsKey(edgeLabel)) {
            throw new IllegalArgumentException("No parent with this edge label exist");
        }

        return parents.get(edgeLabel);
    }

    /**
     * @return the label of the child connected with the edge with the label edge label
     * @throws IllegalArgumentException if there is no child connected with this vertex
     */
    public T getChildLabelByEdgeLabel(S edgeLabel) throws IllegalArgumentException {
        if (!children.containsKey(edgeLabel)) {
            throw new IllegalArgumentException("No child with this edge label exist");
        }

        return children.get(edgeLabel);
    }
}
