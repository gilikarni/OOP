import org.junit.Test;
import static org.junit.Assert.*;


/**
 * BipartiteGraphTest contains JUnit block-box unit tests for BipartiteGraph.
 */
public class BipartiteGraphTest {

	@Test
    public void testExample() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        
        //create a graph
        driver.createGraph("graph1");
        
        //add a pair of nodes
        driver.addBlackNode("graph1", "n1");
        driver.addWhiteNode("graph1", "n2");
        
        //add an edge
        driver.addEdge("graph1", "n1", "n2", "edge");
        
        //check neighbors
        assertEquals("wrong black nodes", "n1", driver.listBlackNodes("graph1"));
        assertEquals("wrong white nodes", "n2", driver.listWhiteNodes("graph1"));
        assertEquals("wrong children", "n2", driver.listChildren ("graph1", "n1"));
        assertEquals("wrong children", "", driver.listChildren ("graph1", "n2"));
        assertEquals("wrong parents", "", driver.listParents ("graph1", "n1"));
        assertEquals("wrong parents", "n1", driver.listParents ("graph1", "n2"));
    }
    
    
    //  TODO: Add black-box tests
    public void blackBoxTests() {
	    BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
	    String graphName = "graph1";

	    driver.createGraph(graphName);

	    driver.addBlackNode(graphName, "blackNode");

    }

    static void main() {
	    BipartiteGraphTest bTest = new BipartiteGraphTest();
	    bTest.testExample();
    }
}
