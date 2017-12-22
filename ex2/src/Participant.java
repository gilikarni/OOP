import java.util.ArrayList;
import java.util.ListIterator;

public class Participant extends Filter<String, Transaction> {
    private final double fee;
    private double balance = 0;
    private ArrayList<Transaction> transactionsToPass = new ArrayList<>();

    /**
     * @param name
     * @effects create a new filter with label and black color
     */
    public Participant(String name, double fee) {
        super(name);
        this.fee = fee;
    }

    @Override
    public void simulate(BipartiteGraph graph) {

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
     * @modifies this, the son of the participant
     * @effects move all the transactions in the waiting list to the pipe
     */
    public void passTransactions() {

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
}
