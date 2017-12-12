import jdk.jshell.spi.ExecutionControl;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
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
     * Create a new vertex for the graph..
     */
    public ColoredVertex() {
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @modifies this
     * @effects Add a new parent to this vertex
     */
    public void addParent(S edgeLabel, T parentLabel) {
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @modifies this
     * @effects Add a new son to this vertex
     */
    public void addSon(S edgeLabel, T sonLabel) {
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @modifies this
     * @effects Add a new son to this vertex
     * @return true if the vertex has a parent with the edge label edgeLabel
     */
    public boolean isParentExist(S edgeLabel) {
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @modifies this
     * @effects Add a new son to this vertex
     * @return true if the vertex has a son with the edge label edgeLabel
     */
    public boolean isSonExist(S edgeLabel) {
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @return return true if the vertex is black
     */
    public boolean isVertexBlack() {
        //TODO: Implement this method
        throw new NotImplementedException();
    }

    /**
     * @return return true if the vertex is white
     */
    public boolean isVertexWhite() {
        //TODO: Implement this method
        throw new NotImplementedException();
    }
}
