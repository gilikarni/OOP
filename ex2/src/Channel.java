import java.util.ArrayList;
import java.util.Iterator;

public class Channel extends Pipe<String, Transaction> {
    private int maxPayments = 0;

    public Channel(String name, int maxPayments) {
        super(name);
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
        if (workobject.getValue() > maxPayments) {
            return bSuccess;
        }

        bSuccess = super.addWorkObject(workobject);

        if (bSuccess) {
            maxPayments -= workobject.getValue();
        }

        checkRep();
        return bSuccess;
    }
}
