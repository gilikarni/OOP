import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.*;

/**
 * A BipartiteGraph is a graph that has two sets of vertexes - black and white.
 * There are edges only between vertexes from different sets.
 * T is the label of a vertex, S is a label of an edge.
 */
public class BipartiteGraph<T, S>{
    public enum BipartiteGraphReturnVelue {
        SUCCESS,
        VERTEX_ALREADY_EXIST,
        VERTEX_DOESNT_EXIST,
        EDGE_ALREADY_EXIST,
        EDGE_DOESNT_EXIST,
        ILLEGAL_EDGE,
    }

    Map<T, Vertex<T, S>> vertexes;

    /**
     * TODO
     */
    public BipartiteGraph() {
        throw new NotImplementedException();
    }

    /**
     * @modifies this
     * @effects Create a new white vertex with the label vertexLabel.
     * @return BipartiteGraphReturnVelue TODO
     */
    public BipartiteGraphReturnVelue addWhiteVertex(T vertexLabel) {
        throw new NotImplementedException();
    }

    /**
     * @modifies this
     * @effects Create a new black vertex with the label vertexLabel.
     * @return BipartiteGraphReturnVelue TODO
     */
    public BipartiteGraphReturnVelue addBlackVertex(T vertexLabel) {
        throw new NotImplementedException();
    }

    /**
     * @modifies this
     * @effects add a new edge to the graph from sourceVertex to targetVertex. The new edge label must be unique for
     * both the vertexes. sourceVertex and targetVertex must be from different sets.
     * @return BipartiteGraphReturnVelue TODO
     */
    public BipartiteGraphReturnVelue addEdge(T sourceVertex, T targetVertex, S edgeLabel) {
        throw new NotImplementedException();
    }
}