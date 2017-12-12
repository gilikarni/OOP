import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.*;

/**
 * A BipartiteGraph is a graph that has two sets of vertexes - black and white.
 * There are edges only between vertexes from different sets.
 * T is the label of a vertex, S is a label of an edge.
 */
public class BipartiteGraph<T, S>{
    // A map from vertex label to V ColoredVertex
    private Map<T, ColoredVertex<T, S>> vertexes;

    // A list containing all the black vertexes in the graph
    private ArrayList<T> blackVertexes;

    // A list containing all the white vertexes in the graph
    private ArrayList<T> whiteVertexes;

    /**
     * @effects create an empty graph
     */
    public BipartiteGraph() {}

    /**
     * @modifies this
     * @effects create a new white vertex with the label vertexLabel.
     * @throws IllegalArgumentException if there is already a vertex with this label
     */
    public void addWhiteVertex(T vertexLabel) throws IllegalArgumentException{
        if (!vertexes.containsKey(vertexLabel)) {
            throw new IllegalArgumentException("Vertex with the same label already exist");
        }

        ColoredVertex<T, S> vertex = new ColoredVertex<>(vertexLabel, ColoredVertex.VertexColor.WHITE);
        vertexes.put(vertexLabel, vertex);
        whiteVertexes.add(vertexLabel);
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

        ColoredVertex<T, S> vertex = new ColoredVertex<>(vertexLabel, ColoredVertex.VertexColor.BLACK);
        vertexes.put(vertexLabel, vertex);
        blackVertexes.add(vertexLabel);
    }

    /**
     * @modifies this
     * @effects add a new edge to the graph from sourceVertex to targetVertex. The new edge label must be unique for
     * both the vertexes. sourceVertex and targetVertex must be from different sets.
     * @throws IllegalArgumentException if the vertex sourceVertex or the vertex targetVertex doesn't exist or if
     * sourceVertex and targetVertex are of the same color or if one of the nodes already have an edge with the same label
     */
    public void addEdge(T sourceVertex, T targetVertex, S edgeLabel) throws IllegalArgumentException{
        if (!vertexes.containsKey(sourceVertex) || !vertexes.containsKey(targetVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist");
        }

        ColoredVertex<T, S> source = vertexes.get(sourceVertex);
        ColoredVertex<T, S> target = vertexes.get(targetVertex);

        if ((source.isVertexBlack() && target.isVertexBlack()) || (source.isVertexWhite() && target.isVertexWhite())) {
            throw new IllegalArgumentException("Can't connect two vertexes with the same color");
        }

        if (source.isChildExist(edgeLabel) || target.isParentExist(edgeLabel)) {
            throw new IllegalArgumentException("Edge with the same label already exist in one of the vertexes");
        }

        source.addChild(edgeLabel, targetVertex);
        target.addParent(edgeLabel, sourceVertex);
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
    public T getChildByEdgeLabel(T parentLabel, S edgeLabel) throws IllegalArgumentException {
        return vertexes.get(parentLabel).getChildLabelByEdgeLabel(edgeLabel);
    }

    /**
     * @return the label of the parent vertex
     * @throws IllegalArgumentException if the vertex doesn't exist or the vertex doesn't have a
     * parent with the label edgeLabel.
     */
    public T getParentByEdgeLabel(T childLabel, S edgeLabel) throws IllegalArgumentException{
        return vertexes.get(childLabel).getParentLabelByEdgeLabel(edgeLabel);
    }
}