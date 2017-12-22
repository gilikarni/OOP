import java.util.ArrayList;

public abstract class Filter<T, S> extends ColoredVertex<T> implements Simulatable {
    /*
    Abstraction function:
    Filter f is white vertex that can simulate on working objects. f has a buffer that so it can hols work objects in the
    buffer while evaluating other working objects.

    Representation invariant:
    All the invariants of vertex && workingObjectsBuffer has no null members
    */

    ArrayList<S> workingObjectsBuffer;

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
    public void addWorkingObject(S workingObject) {
        workingObjectsBuffer.add(workingObject);
        checkRep();
    }

    @Override
    protected void checkRep() {
        super.checkRep();

        assert !workingObjectsBuffer.contains(null) : "The buffer contains null objects";
    }
}
