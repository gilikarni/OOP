import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public abstract class Pipe<T, S> extends ColoredVertex<T> implements Simulatable{
    /*
    Abstraction function:
    Pipe p is a black vertex that has a capacity. The capacity is the number of objects that the pipe can handle
    simultaneously.

    Representation invariant:
    All the invariants of vertex &&
    (hasLimit && capacity >= 0) || (!hasLimit && capacity == 0) &&
    workingObject has no null objects &&
    (hasLimit && workingObjects.size() <= capacity) || !hasLimit
    */

    private boolean hasLimit = false;
    private int capacity = 0;
    protected ArrayList<S> workingObjects = new ArrayList<>();

    /**
     * @effects Create a new vertex of type Pipe for the graph, each vertex has color - black or white
     * The parents and children maps are empty
     */
    public Pipe(T label) {
        super(label, VertexColor.BLACK);
        checkRep();
    }

    /**
     * @effects Create a new vertex of type Pipe for the graph, each vertex has color - black or white
     * The parents and children maps are empty. If capacity is a positive number it is defined to be the capacity of
     * the pipe, else there is no limit to the pipe capacity
     */
    public Pipe(T label, int capacity) {
        super(label, VertexColor.BLACK);

        if (capacity >= 0) {
            hasLimit = true;
            this.capacity = capacity;
        }
        checkRep();
    }

    /**
     * @effects adds a S workobject to workingObjects if it's not full.
     * @returns false if pipe has a limit and it is full or if adding the workobject failed. true o.w.
     */
    public boolean addWorkObject(S workobject){
        if (hasLimit && workingObjects.size() == capacity){
            return false;
        }
        return workingObjects.add(workobject);
    }

    /**
     * @return iterator of the current waiting work objects in the pipe
     */
    protected Iterator<S> getWorkObjectsIterator() {
        return workingObjects.iterator();
    }

    /**
     * @modifies this
     * @effects clear the pipe from work objects
     */
    protected void clearWorkObjects() {
        workingObjects.clear();
    }

    /**
     * @effects verifies that the representation invariants holds, else, crash on assert.
     */
    private void checkRep() {
        if (hasLimit) {
            assert capacity >= 0 : "A non positive limit is defined";
        } else {
            assert capacity == 0 : "If no limit is defined the capacity should be zero";
        }

        assert !workingObjects.contains(null) : "WorkingObjects contains null objects";
        assert !hasLimit || workingObjects.size() <= capacity : "The number of objects in the pipe is larger than the capacity";
    }
}
