import java.util.ArrayList;
import java.util.Iterator;

/** A Channel is a pipe that pass transactions between participants. The channel has a limit for the amount it can pass,
 * if a transaction will make the channel pass the limit it wouldn't except it.
 */
public class Channel extends Pipe<String, Transaction> {
    /*
    Abstraction function:
    Channel c is a pipe that passes transaction. Its capacity is infinity but it has a total limit for the amount it
    can pass in a simulation.

    Representation invariant:
    All the invariants of Pipe && the Channel never pass more than its limit
    */
    private final double maxPayments;
    private double passedAmount = 0;


    public Channel(String name, double maxPayments) throws IllegalArgumentException{
        super(name);
        if (maxPayments <= 0){
            throw new IllegalArgumentException("maxPayment must be positive");
        }
        this.maxPayments = maxPayments;

        checkRep();
    }

    @Override
    public void simulate(BipartiteGraph graph) {
        ArrayList<ColoredVertex<String>> children = getChildrenList();
        assert children.size() <= 1 : "Channel has " + children.size() + "children";

        /* Nowhere to pass the transaction to */
        if (children.size() == 0) {
            return;
        }

        Participant target = (Participant)children.get(0);

        Iterator<Transaction> iterator = getWorkObjectsIterator();
        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            target.addTransaction(transaction);
        }

        clearWorkObjects();
        checkRep();
    }

    /**
     * @modifies this
     * @effects adds a new transaction to the pipe if the value of the transaction is not higher than the current
     * maximum allowed value for the channel.
     * @return true if the transaction was added successfully, false otherwise
     */
    @Override
    public boolean addWorkObject(Transaction workobject) {
        boolean bSuccess = false;
        if (workobject.getValue() > (maxPayments - passedAmount)) {
            return bSuccess;
        }

        bSuccess = super.addWorkObject(workobject);

        if (bSuccess) {
            passedAmount += workobject.getValue();
        }

        checkRep();
        return bSuccess;
    }

    /**
     * @effects verifies that the representation invariants holds, else, crash on assert.
     */
    private void checkRep() {
        assert passedAmount <= maxPayments: "The passed amount is larger than the maximum allowed amount to pass at channel" + getVertexLabel();
    }
}
