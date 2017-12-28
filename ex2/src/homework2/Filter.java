package homework2;

import java.util.ArrayList;
import java.util.ListIterator;

public abstract class Filter<T, S> extends ColoredVertex<T> implements Simulatable {
    /*
    Abstraction function:
    Filter f is white vertex that can simulate on working objects. f has a buffer that can hold work objects while
    evaluating other working objects.

    Representation invariant:
    All the invariants of vertex && workingObjectsBuffer has no null members
    */

    private ArrayList<S> workingObjectsBuffer;

    /**
     * @effects create a new filter with label and white color
     */
    public Filter(T label) {
        super(label, VertexColor.WHITE);
        workingObjectsBuffer = new ArrayList<>();
        checkRep();
    }

    /**
     * @effects create a new copy of given filter
     */
    public Filter(Filter<T,S> filter)  {
        super(filter);
        workingObjectsBuffer = new ArrayList<>(filter.workingObjectsBuffer);
        checkRep();
    }


    /**
     * @modifies this
     * @effects Add a new working object to the buffer
     */
    protected void addWorkingObjectToBuffer(S workingObject) {
        workingObjectsBuffer.add(workingObject);
        checkRep();
    }

    /**
     * @return an iterator to go over the working objects buffer
     */
    protected ListIterator<S> getNextWorkingObjectsBufferIterator() {
        return workingObjectsBuffer.listIterator();
    }

    protected void cleanWorkingObjectsBuffer() {
        workingObjectsBuffer.clear();
    }

    /**
     * @effects verifies that the representation invariants holds, else, crash on assert.
     */
    private void checkRep() {
        assert !workingObjectsBuffer.contains(null) : "The buffer contains null objects";
    }
}
