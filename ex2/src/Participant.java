import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Participant extends Filter<String, Transaction> {
    private final double fee;
    private double balance = 0;
    private ArrayList<Transaction> transactionsToPass = new ArrayList<>();

    /**
     * @effects create a new filter with label and black color
     */
    public Participant(String name, double fee) {
        super(name);
        this.fee = fee;
    }

    /**
     * @modifies this, the child of the participant
     * @effects passes all the transactions to the child of this participant and update the balance according to the
     * new transactions of the participant
     */
    @Override
    public void simulate(BipartiteGraph graph) {
        passTransactions();
        evaluateParticipantTransactions();
    }

    /**
     * @modifies this, transaction
     * @effects adds a new transaction for this object to handle. If the transaction is meant for this participant leave
     * it in the buffer.
     * Else, Create two new transactions:
     * 1. transaction to this participant with the fee it takes for passing a transaction.
     * 2. transaction for the dest participant with the original value less the fee.
     */
    public void addTransaction(Transaction transaction) {
        if (transaction.getDest().equals(getVertexLabel())) { /* The transaction is for this participant */
            addWorkingObjectToBuffer(transaction);
        } else { /* This is not the target of the of the transaction */
            /* If the value of the transaction is larger than the fee, the fee will be all the value of the transaction */
            double actualFee = Double.min(fee, transaction.getValue());
            Transaction feeTransaction = new Transaction(getVertexLabel(), actualFee);
            addWorkingObjectToBuffer(feeTransaction);
            if (transaction.getValue() > actualFee) { /* There is something left to move forward */
                Transaction passTransaction = new Transaction(transaction.getDest(),
                        transaction.getValue() - actualFee);
                transactionsToPass.add(passTransaction);
            }
        }

    }

    /**
     * @modifies this, the child of the participant
     * @effects move all the transactions in the waiting list to the pipe
     */
    public void passTransactions() {
        ArrayList<ColoredVertex<String>> children = getChildrenList();
        assert children.size() <= 1 : "Participant has " + children.size() + "children";

        /* Nowhere to pass the transaction to */
        if (children.size() == 0) {
            return;
        }

        Channel target = (Channel)children.get(0);

        Iterator<Transaction> iterator = transactionsToPass.iterator();
        ArrayList<Transaction> transactionsToRemove = new ArrayList<>();

        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();

            boolean bSuccess = target.addWorkObject(transaction);

            /* Remove only successful transactions */
            if (bSuccess) {
                transactionsToRemove.add(transaction);
            }
        }

        /* Remove all successful transactions */
        for (Transaction transaction : transactionsToRemove) {
            transactionsToPass.remove(transaction);
        }
    }

    /**
     * @modifies this
     * @effects evaluate the transactions in the buffer and fix the balance accordingly
     */
    public void evaluateParticipantTransactions() {
        ListIterator<Transaction> iterator = getNextWorkingObjectsBufferIterator();
        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            balance += transaction.getValue();
        }

        cleanWorkingObjectsBuffer();
    }

    /**
     * @return the balance of the participant
     */
    public double getBalance() {
        return balance;
    }
}
