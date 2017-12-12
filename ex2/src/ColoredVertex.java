import java.util.*;

/**
 * TODO
 */
public class ColoredVertex<T, S> {
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
    public ColoredVertex(VertexColor color) {
        this.color = color;
    }

    /**
     * @modifies this
     * @effects Add a new parent to this vertex
     * @throws IllegalArgumentException if there is another parent with the same edge label
     */
    public void addParent(S edgeLabel, T parentLabel) throws IllegalArgumentException {
        if (parents.containsKey(edgeLabel)) {
            throw new IllegalArgumentException("A parent with the same edge label already exist.")
        }

        parents.put(edgeLabel, parentLabel);
    }

    /**
     * @modifies this
     * @effects Add a new child to this vertex
     * @throws IllegalArgumentException if there is another child with the same edge label
     */
    public void addChild(S edgeLabel, T childLabel) throws IllegalArgumentException {
        if (parents.containsKey(edgeLabel)) {
            throw new IllegalArgumentException("A parent with the same edge label already exist.")
        }

        parents.put(edgeLabel, childLabel);
    }

    /**
     * @effects Check if the parent exist
     * @return true if the vertex has a parent with the edge label edgeLabel
     */
    public boolean isParentExist(S edgeLabel) {
        return parents.containsKey(edgeLabel);
    }

    /**
     * @modifies this
     * @effects Check if the child exist
     * @return true if the vertex has a child with the edge label edgeLabel
     */
    public boolean isChildExist(S edgeLabel) {
        return parents.containsKey(edgeLabel);
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
    public T getParentLabelByEdgeLabel(S edgeLabel) throws IllegalAccessException {
        if (!parents.containsKey(edgeLabel)) {
            throw new IllegalAccessException("No parent with this edge label exist");
        }

        return parents.get(edgeLabel);
    }

    /**
     * @return the label of the child connected with the edge with the label edge label
     * @throws IllegalArgumentException if there is no child connected with this vertex
     */
    public T getChildLabelByEdgeLabel(S edgeLabel) throws IllegalAccessException {
        if (!children.containsKey(edgeLabel)) {
            throw new IllegalAccessException("No child with this edge label exist");
        }

        return children.get(edgeLabel);
    }
}
