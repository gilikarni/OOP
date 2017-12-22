import java.util.ArrayList;
import java.util.ListIterator;

public abstract class Filter<T, S> extends ColoredVertex<T> implements Simulatable {
    /*
    Abstraction function:
    Filter f is white vertex that can simulate on working objects. f has a buffer that so it can hols work objects in the
    buffer while evaluating other working objects.

    Representation invariant:
    All the invariants of vertex && workingObjectsBuffer has no null members
    */

    private ArrayList<S> workingObjectsBuffer = new ArrayList<>();

    /**
     * @effects create a new filter with label and black color
     */
    public Filter(T label) {
        super(label, VertexColor.BLACK);

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
        return (ListIterator<S>) workingObjectsBuffer.iterator();
    }

    protected void cleanWorkingObjectsBuffer() {
        workingObjectsBuffer.clear();
    }

    /**
     * @effects verifies that the representation invariants holds, else, crash on assert.
     */
    @Override
    protected void checkRep() {
        super.checkRep();

        assert !workingObjectsBuffer.contains(null) : "The buffer contains null objects";
    }
}
