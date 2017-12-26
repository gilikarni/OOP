import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;


/**
 * BipartiteGraphTest contains JUnit block-box unit tests for BipartiteGraph.
 */
public class SimulatorTest {

    @Test
    public void testExample() {
        SimulatorTestDriver driver = new SimulatorTestDriver();

        //create a simulator
        driver.createSimulator("sim1");

        //add participants
        driver.addParticipant("sim1", "P1", 1);
        driver.addParticipant("sim1", "P2", 2);
        driver.addParticipant("sim1", "P3", 3);

        //add channels
        driver.addChannel("sim1", "C1", 500);
        driver.addChannel("sim1", "C2", 600);

        //add an edge
        driver.addEdge("sim1", "P1", "C1", "P1C1");
        driver.addEdge("sim1", "C1", "P2", "C1P2");
        driver.addEdge("sim1", "P2", "C2", "P2C2");
        driver.addEdge("sim1", "C2", "P3", "C2P3");

        //print edges
        driver.printAllEdges("sim1");

        //add Transaction
        driver.sendTransaction("sim1", "C1", new Transaction("P3", 10));
        assertNotEquals("C1 doesn't hold a transaction", "", driver.listContents("sim1", "C1"));
        assertEquals("C2 holds a transaction", "", driver.listContents("sim1", "C2"));

        // simulate
        driver.simulate("sim1");
        // check that p1 took a fee and C2 holds the transaction
        assertEquals("P2 doesn't have the correct balance", 2.0, driver.getParticipantBalace("sim1", "P2"), 0.5);
        assertEquals("C1 is not empty", "", driver.listContents("sim1", "C1"));
        assertNotEquals("C2 is empty", "", driver.listContents("sim1", "C2"));

        // simulate
        driver.simulate("sim1");

        // assert that P3 received the transaction, that p2 balance didn't change and that both pipes are empty
        assertEquals("P2 doesn't have the correct balance", 2.0, driver.getParticipantBalace("sim1", "P2"), 0.5);
        assertEquals("P3 doesn't have the correct balance", 8.0, driver.getParticipantBalace("sim1", "P3"), 0.5);
        assertEquals("C1 is not empty", "", driver.listContents("sim1", "C1"));
        assertEquals("C2 is not empty", "", driver.listContents("sim1", "C2"));
    }
        @Test
    public void testTwoIncomingChannels() {
        SimulatorTestDriver driver = new SimulatorTestDriver();

        //create a simulator
        driver.createSimulator("sim1");

        //add participants
        driver.addParticipant("sim1", "P1", 1);
        driver.addParticipant("sim1", "P2", 2);
        driver.addParticipant("sim1", "P3", 3);

        //add channels
        driver.addChannel("sim1", "C1", 500);
        driver.addChannel("sim1", "C2", 600);

        //add an edge
        driver.addEdge("sim1", "P1", "C1", "P1C1");
        driver.addEdge("sim1", "C1", "P2", "C1P2");
        driver.addEdge("sim1", "P2", "C2", "P2C2");
        driver.addEdge("sim1", "C2", "P3", "C2P3");
        // add participant P4 that connects to P2
        driver.addParticipant("sim1", "P4", 1);
        driver.addChannel("sim1", "C3", 500);
        driver.addEdge("sim1", "P4", "C3", "P4C3");
        driver.addEdge("sim1", "C3", "P2", "C3P2");

        // send transactions both ways
        driver.sendTransaction("sim1", "C1", new Transaction("P3", 10));
        driver.sendTransaction("sim1", "C3", new Transaction("P3", 5));
        assertNotEquals("C1 doesn't hold a transaction", "", driver.listContents("sim1","C1"));
        assertNotEquals("C3 doesn't a transaction", "", driver.listContents("sim1","C3"));

        // simulate
        driver.simulate("sim1");
        assertNotEquals("C2 doesn't hold a transaction", "", driver.listContents("sim1","C2"));


    }

    /**
     * black box tests
     */
    @Test(expected = AssertionError.class)
    public void negativeFeeTest(){
        SimulatorTestDriver driver = new SimulatorTestDriver();

        //create a simulator
        driver.createSimulator("sim1");

        // add objects
        driver.addParticipant("sim1", "P", -1);
    }

//    @Test
//    public void TooBigTranscation
}
