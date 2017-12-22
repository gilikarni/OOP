public class Channel<T, S> extends Pipe<T, S> {
    private int maxPayments = 0;

    public Channel(T label, int maxPayments) {
        super(label);
        this.maxPayments = maxPayments;

        checkRep();
    }

    @Override
    public void simulate(BipartiteGraph graph) {

    }
}
