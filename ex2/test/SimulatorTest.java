import org.junit.Test;

import static org.junit.Assert.*;


/**
 * BipartiteGraphTest contains JUnit block-box unit tests for BipartiteGraph.
 */
public class SimulatorTest {
    /**
     * integration tests
     */
    @Test
    public void exampleFromSpecTest() {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        String simName = "sim1";

        //create a simulator
        driver.createSimulator(simName);

        //add participants
        driver.addParticipant(simName, "P1", 1);
        driver.addParticipant(simName, "P2", 2);
        driver.addParticipant(simName, "P3", 3);

        //add channels
        driver.addChannel(simName, "C1", 500);
        driver.addChannel(simName, "C2", 600);

        //add an edge
        driver.addEdge(simName, "P1", "C1", "P1C1");
        driver.addEdge(simName, "C1", "P2", "C1P2");
        driver.addEdge(simName, "P2", "C2", "P2C2");
        driver.addEdge(simName, "C2", "P3", "C2P3");

        //add Transaction
        driver.sendTransaction(simName, "C1", new Transaction("P3", 10));
        assertNotEquals("C1 doesn't hold a transaction", "", driver.listContents(simName, "C1"));
        assertEquals("C2 holds a transaction", "", driver.listContents(simName, "C2"));

        // simulate
        driver.simulate(simName);
        // check that p1 took a fee and C2 holds the transaction
        assertEquals("P2 doesn't have the correct balance", 2.0, driver.getParticipantBalace(simName, "P2"), 0.5);
        assertEquals("C1 is not empty", "", driver.listContents(simName, "C1"));
        assertNotEquals("C2 is empty", "", driver.listContents(simName, "C2"));

        // simulate
        driver.simulate(simName);

        // assert that P3 received the transaction, that p2 balance didn't change and that both pipes are empty
        assertEquals("P2 doesn't have the correct balance", 2.0, driver.getParticipantBalace(simName, "P2"), 0.5);
        assertEquals("P3 doesn't have the correct balance", 8.0, driver.getParticipantBalace(simName, "P3"), 0.5);
        assertEquals("C1 is not empty", "", driver.listContents(simName, "C1"));
        assertEquals("C2 is not empty", "", driver.listContents(simName, "C2"));
    }

    @Test
    public void participantWithTwoIncomingChannelsTest() {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        String simName = "sim1";

            //create a simulator
        driver.createSimulator(simName);

        //add participants
        driver.addParticipant(simName, "P1", 1);
        driver.addParticipant(simName, "P2", 2);
        driver.addParticipant(simName, "P3", 3);

        //add channels
        driver.addChannel(simName, "C1", 500);
        driver.addChannel(simName, "C2", 600);

        //add an edge
        driver.addEdge(simName, "P1", "C1", "P1C1");
        driver.addEdge(simName, "C1", "P2", "C1P2");
        driver.addEdge(simName, "P2", "C2", "P2C2");
        driver.addEdge(simName, "C2", "P3", "C2P3");
        // add participant P4 that connects to P2
        driver.addParticipant(simName, "P4", 1);
        driver.addChannel(simName, "C3", 500);
        driver.addEdge(simName, "P4", "C3", "P4C3");
        driver.addEdge(simName, "C3", "P2", "C3P2");

        // send transactions both ways
        driver.sendTransaction(simName, "C1", new Transaction("P3", 10));
        driver.sendTransaction(simName, "C3", new Transaction("P3", 5));
        assertNotEquals("C1 doesn't hold a transaction", "", driver.listContents(simName,"C1"));
        assertNotEquals("C3 doesn't a transaction", "", driver.listContents(simName,"C3"));

        // simulate a step
        driver.simulate(simName);
        assertNotEquals("C2 empty", "", driver.listContents(simName,"C2"));
        assertEquals("P2 doesn't have the correct balance", 4.0, driver.getParticipantBalace(simName, "P2"), 0.5);
        assertEquals("C1 is not empty", "", driver.listContents(simName, "C1"));
        assertEquals("C3 is not empty", "", driver.listContents(simName, "C3"));

        // simulate a step
        driver.simulate(simName);
        assertEquals("C2 not empty", "", driver.listContents(simName,"C2"));
        assertEquals("P2 doesn't have the correct balance", 4.0, driver.getParticipantBalace(simName, "P2"), 0.5);
        assertEquals("P3 doesn't have the correct balance", 11.0, driver.getParticipantBalace(simName, "P3"), 0.5);
        assertEquals("C1 not empty", "", driver.listContents(simName, "C1"));
        assertEquals("C3 not empty", "", driver.listContents(simName, "C3"));
    }

    @Test
    public void feeLargerThanTransactionValueTest() {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        String simName = "sim1";

        //create a simulator
        driver.createSimulator(simName);

        //add participants
        driver.addParticipant(simName, "P1", 1);
        driver.addParticipant(simName, "P2", 15);
        driver.addParticipant(simName, "P3", 3);

        //add channels
        driver.addChannel(simName, "C1", 500);
        driver.addChannel(simName, "C2", 600);

        //add an edge
        driver.addEdge(simName, "P1", "C1", "P1C1");
        driver.addEdge(simName, "C1", "P2", "C1P2");
        driver.addEdge(simName, "P2", "C2", "P2C2");
        driver.addEdge(simName, "C2", "P3", "C2P3");

        //add Transaction
        driver.sendTransaction(simName, "C1", new Transaction("P3", 10));
        assertNotEquals("C1 doesn't hold a transaction", "", driver.listContents(simName, "C1"));
        assertEquals("C2 holds a transaction", "", driver.listContents(simName, "C2"));

        // simulate
        driver.simulate(simName);
        // check that p2 took the entire fee
        assertEquals("P2 doesn't have the correct balance", 10, driver.getParticipantBalace(simName, "P2"), 0.5);
        assertEquals("C1 is not empty", "", driver.listContents(simName, "C1"));
        assertEquals("C2 is not empty", "", driver.listContents(simName, "C2"));
    }

    @Test
    public void transactionsValuesLargerThanChannelLimitTest() {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        String simName = "sim1";

        //create a simulator
        driver.createSimulator(simName);

        //add participants
        driver.addParticipant(simName, "P1", 1);
        driver.addParticipant(simName, "P2", 2);
        driver.addParticipant(simName, "P3", 3);

        //add channels
        driver.addChannel(simName, "C1", 500);
        driver.addChannel(simName, "C2", 15);

        //add an edge
        driver.addEdge(simName, "P1", "C1", "P1C1");
        driver.addEdge(simName, "C1", "P2", "C1P2");
        driver.addEdge(simName, "P2", "C2", "P2C2");
        driver.addEdge(simName, "C2", "P3", "C2P3");

        //add Transaction
        driver.sendTransaction(simName, "C1", new Transaction("P3", 10));
        driver.sendTransaction(simName, "C1", new Transaction("P3", 10));
        assertNotEquals("C1 doesn't hold a transaction", "", driver.listContents(simName, "C1"));
        assertEquals("C2 holds a transaction", "", driver.listContents(simName, "C2"));

        // simulate
        driver.simulate(simName);
        // check that p2 took a fee for both transactions and saved one transaction to itself and C2 holds one transaction
        assertEquals("P2 doesn't have the correct balance", 12, driver.getParticipantBalace(simName, "P2"), 0.5);
        assertEquals("P3 doesn't have the correct balance", 0, driver.getParticipantBalace(simName, "P3"), 0.5);
        assertEquals("C1 is not empty", "", driver.listContents(simName, "C1"));
        assertNotEquals("C2 is empty", "", driver.listContents(simName, "C2"));

        // simulate
        driver.simulate(simName);
        // check that C2 holds no transactions
        assertEquals("P2 doesn't have the correct balance", 12, driver.getParticipantBalace(simName, "P2"), 0.5);
        assertEquals("P3 doesn't have the correct balance", 8, driver.getParticipantBalace(simName, "P3"), 0.5);
        assertEquals("C1 is not empty", "", driver.listContents(simName, "C1"));
        assertEquals("C2 is not empty", "", driver.listContents(simName, "C2"));
    }

    /**
     * unit tests
     */
    @Test(expected = IllegalArgumentException.class)
    public void negativeFeeTest(){
        SimulatorTestDriver driver = new SimulatorTestDriver();
        String simName = "sim1";

        //create a simulator
        driver.createSimulator(simName);

        // add objects
        driver.addParticipant(simName, "P", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroMaxLimitTest(){
        SimulatorTestDriver driver = new SimulatorTestDriver();
        String simName = "sim1";

        //create a simulator
        driver.createSimulator(simName);

        // add objects
        driver.addChannel(simName, "c", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tooBigTransactionTest(){
        SimulatorTestDriver driver = new SimulatorTestDriver();
        String simName = "sim1";

        //create a simulator
        driver.createSimulator(simName);

        // add objects
        driver.addChannel(simName, "C", 1);

        // send transaction of 2
        driver.sendTransaction(simName, "C", new Transaction("A", 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void channelConnectedToChannelTest(){
        SimulatorTestDriver driver = new SimulatorTestDriver();
        String simName = "sim1";

        //create a simulator
        driver.createSimulator(simName);

        // add objects
        driver.addChannel(simName, "C1", 1);
        driver.addChannel(simName, "C2", 1);
        driver.addEdge(simName, "C1", "C2", "C1C2");
        driver.printAllEdges(simName); // Should not get called;
    }

    @Test(expected = IllegalArgumentException.class)
    public void participantConnectedToParticipantTest(){
        SimulatorTestDriver driver = new SimulatorTestDriver();
        String simName = "sim1";

        //create a simulator
        driver.createSimulator(simName);

        // add objects
        driver.addParticipant(simName, "P1", 1);
        driver.addParticipant(simName, "P2", 1);
        driver.addEdge(simName, "P1", "P2", "P1P2");
        driver.printAllEdges(simName); // Should not get called;
    }

    @Test
    public void printEdgesTest(){
        SimulatorTestDriver driver = new SimulatorTestDriver();
        String simName = "sim1";

        //create a simulator
        driver.createSimulator(simName);

        //add participants
        driver.addParticipant(simName, "P1", 1);
        driver.addParticipant(simName, "P2", 2);
        driver.addParticipant(simName, "P3", 3);

        //add channels
        driver.addChannel(simName, "C1", 500);
        driver.addChannel(simName, "C2", 600);

        //add an edge
        driver.addEdge(simName, "P1", "C1", "P1C1");
        driver.addEdge(simName, "C1", "P2", "C1P2");
        driver.addEdge(simName, "P2", "C2", "P2C2");
        driver.addEdge(simName, "C2", "P3", "C2P3");

        System.out.println("edges P1C1, C1P2, P2C2, C2P3 should print in the following line");
        //print edges
        driver.printAllEdges(simName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getParticipantBalaceWithNameOfChannelTest() {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        String simName = "sim1";

        //create a simulator
        driver.createSimulator(simName);

        //add participants
        driver.addChannel(simName, "C1", 1);
        driver.getParticipantBalace(simName, "C1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void listContentWithNameOfParticipantTest() {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        String simName = "sim1";

        //create a simulator
        driver.createSimulator(simName);

        //add participants
        driver.addParticipant(simName, "P1", 1);
        driver.listContents(simName, "P1");
    }
}
