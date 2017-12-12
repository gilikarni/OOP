import jdk.jshell.spi.ExecutionControl;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * TODO
 */
public class Vertex<T, S> {
    private T label;
    private Map<S, T> parents;
    private Map<S, T> children;

    public Vertex() {
        throw new NotImplementedException();
    }

    /**
     * @modifies this
     * @effects Add a new parent to this vertex
     */
    public void addParent(S edgeLabel, T parentLabel) {
        throw new NotImplementedException();
    }

    /**
     * @modifies this
     * @effects Add a new son to this vertex
     */
    public void addSon(S edgeLabel, T sonLabel) {
        throw new NotImplementedException();
    }

    /**
     * @modifies this
     * @effects Add a new son to this vertex
     * @return true if the vertex has a parent with the edge label edgeLabel
     */
    public boolean isParentExist(S edgeLabel) {
        throw new NotImplementedException();
    }

    /**
     * @modifies this
     * @effects Add a new son to this vertex
     * @return true if the vertex has a son with the edge label edgeLabel
     */
    public boolean isSonExist(S edgeLabel) {
        throw new NotImplementedException();
    }
}
