package homework2;

import java.util.*;

/**
 * This class implements a testing driver for Simulator. The driver manages
 * Simulators for payment channels
 */
public class SimulatorTestDriver {

	private Map<String, Simulator<String, Transaction>> simulators;

	/**
	 * @modifies this
	 * @effects Constructs a new test driver.
	 */
	public SimulatorTestDriver() {
        simulators = new HashMap<String, Simulator<String, Transaction>>();
	}

	/**
	 * @requires simName is not null
	 * @modifies this
	 * @effects Creates a new simulator named simName. The simulator's graph is
	 *          initially empty.
	 */
	public void createSimulator(String simName) {
	    Simulator<String, Transaction> newSim = new Simulator<String, Transaction>();
	    simulators.put(simName, newSim);
	}

	/**
	 * @requires createSimulator(simName) 
     *           and channelName is not null and channelName has
	 *           not been used in a previous addChannel()  or
	 *           addParticipant() call on this object
	 *           limit l.t. 0
	 * @modifies simulator named simName
	 * @effects Creates a new Channel named by the String channelName, with a limit, and add it to
	 *          the simulator named simName.
	 */
	public void addChannel(String simName, String channelName, double limit) throws IllegalArgumentException{
	    Channel channel = new Channel(channelName,limit);
	    simulators.get(simName).addPipe(channel);
	}

	/**
	 * @requires createSimulator(simName) and participantName is not null
	 *           and participantName has not been used in a previous addParticipant(), addChannel()
	 *           call on this object
	 *           fee l.t. 0
	 * @modifies simulator named simName
	 * @effects Creates a new Participant named by the String participantName and add
	 *          it to the simulator named simName.
	 */
	public void addParticipant(String simName, String participantName, double fee) throws IllegalArgumentException{
        Participant participant = new Participant(participantName, fee);
        simulators.get(simName).addFilter(participant);
	}

	/**
	 * @requires createSimulator(simName) and ((addPipe(parentName) and
	 *           addFilter(childName)) || (addFilter(parentName) and
	 *           addPipe(childName))) and edgeLabel is not null and node named
	 *           parentName has no other outgoing edge labeled edgeLabel 
	 *           and node named childName has no other incoming edge labeled edgeLabel
	 * @modifies simulator named simName
	 * @effects Adds an edge from the node named parentName to the node named
	 *          childName in the simulator named simName. The new edge's label
	 *          is the String edgeLabel.
	 */
	public void addEdge(String simName, String parentName, String childName, String edgeLabel)
            throws IllegalArgumentException{
        simulators.get(simName).addEdge(parentName,childName,edgeLabel);
	}

	/**
	 * @requires createSimulator(simName) and addChannel(channelName)
	 *           A transaction Transaction is not null
	 * @modifies channel named channelName
	 * @effects pushes the Transaction into the channel named channelName in the
	 *          simulator named simName.
	 */
	public void sendTransaction(String simName, String channelName, Transaction tx) throws IllegalArgumentException {
        simulators.get(simName).addWorkObject(channelName, tx);
    }


	/**
	 * @requires addChannel(channelName)
	 * @return a space-separated list of the Transaction values currently in the
	 *         channel named channelName in the simulator named simName.
	 */
	public String listContents(String simName, String channelName) throws IllegalArgumentException{
        return createStringFromList(simulators.get(simName).getPipeContents(channelName));
	}

	/**
	 * @requires addParticipant(participantName)
	 * @return The sum of all  Transaction values stored in the storage of the participant participantName in the simulator simName
	 */
	public double getParticipantBalace(String simName, String participantName) throws IllegalArgumentException {
        Participant participant = (Participant)simulators.get(simName).getFilter(participantName);
        return participant.getBalance();
	}
	
	/**
	 * @requires createSimulator(simName)
	 * @modifies simulator named simName
	 * @effects runs simulator named simName for a single time slice.
	 */
	public void simulate(String simName) {
        simulators.get(simName).simulate();
	}

	/**
	 * Prints the all edges.
	 *
	 * @requires simName the sim name
	 * @effects Prints the all edges.
	 */
	public void printAllEdges(String simName) {
        System.out.println("All of the edges in the simulator are: "
                + createStringFromList(simulators.get(simName).getEdges()));
	}

    private String createStringFromList(List list) {
        String string = "";

        boolean first = true;

        for (Object s : list) {
            if (!first) {
                string = string + " ";
            }
            string = string + s.toString();
            first = false;
        }

        return string;
    }


}
