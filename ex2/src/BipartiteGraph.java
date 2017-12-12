import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.*;

/**
 * A BipartiteGraph is a graph that has two sets of vertexes - black and white.
 * There are edges only between vertexes from different sets.
 * T is the label of a vertex, S is a label of an edge.
 */
public class BipartiteGraph<T, S>{
    Map<T, ColoredVertex<T, S>> vertexes;

    /**
     * TODO
     */
    public BipartiteGraph() {
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @modifies this
     * @effects Create a new white vertex with the label vertexLabel.
     * @throws IllegalArgumentException if there is already a vertex with this label
     */
    public void addWhiteVertex(T vertexLabel) throws IllegalArgumentException{
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @modifies this
     * @effects Create a new black vertex with the label vertexLabel.
     * @throws IllegalArgumentException if there is already a vertex with this label
     */
    public void addBlackVertex(T vertexLabel) throws IllegalArgumentException{
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @modifies this
     * @effects add a new edge to the graph from sourceVertex to targetVertex. The new edge label must be unique for
     * both the vertexes. sourceVertex and targetVertex must be from different sets.
     * @throws IllegalArgumentException if the
     */
    public void addEdge(T sourceVertex, T targetVertex, S edgeLabel) throws IllegalArgumentException{
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @return a list of all the black vertexes labels in the graph
     */
    public List<T> getListOfBlackVertexes() {
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @return a list of all the white vertexes labels in the graph
     */
    public List<T> getListOfWhiteVertexes() {
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @return a list of the parents labels of the vertex with the label vertexLabel
     * @throws IllegalArgumentException if there is no vertex with the label vertexLabel
     */
    public List<T> getListOfVertexParents(T vertexLabel) throws IllegalArgumentException{
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @return a list of the children labels of the vertex with the label vertexLabel
     * @throws IllegalArgumentException if there is no vertex with the label vertexLabel
     */
    public List<T> getListOfVertexChildren(T vertexLabel) throws IllegalArgumentException{
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @return The label of the child vertex
     * @throws IllegalArgumentException if the vertex doesn't exist or the vertex doesn't have a
     * child with the label edgeLabel.
     */
    public T getChildByEdgeLabel(T parentLabel, S edgeLabel) throws IllegalArgumentException{
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @return The label of the parent vertex
     * @throws IllegalArgumentException if the vertex doesn't exist or the vertex doesn't have a
     * parent with the label edgeLabel.
     */
    public T getParentByEdgeLabel(T childLabel, S edgeLabel) throws IllegalArgumentException{
        //TODO: Implement this method
        throw new NotImplementedException();
    }
}