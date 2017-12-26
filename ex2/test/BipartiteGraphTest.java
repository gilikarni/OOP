import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

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
    @Test
    public void testAddBlackNodeSuccess() {
	    BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
	    String graphName = "graph1";
        ArrayList<String> nodes = new ArrayList<>();
        nodes.add("Node1");
        nodes.add("Node2");
        nodes.add("Node3");
        nodes.add("Node4");
        nodes.add("Node5");

	    driver.createGraph(graphName);
	    int len  = 1;

        for (String nodeName : nodes) {
            driver.addBlackNode(graphName, nodeName);
            String[] list = driver.listBlackNodes(graphName).split(" ");
            assertEquals(len++, list.length);
            for (String s : list) {
                assertTrue(nodes.contains(s));
            }
        }
    }

    @Test
    public void testAddBlackNodeAddTwiceFailure()
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        String graphName = "graph1";
        ArrayList<String> nodes = new ArrayList<>();
        nodes.add("Node1");
        nodes.add("Node2");
        nodes.add("Node3");
        nodes.add("Node4");
        nodes.add("Node5");

        driver.createGraph(graphName);

        for (String nodeName : nodes) {
            driver.addBlackNode(graphName, nodeName);

            for (int i = 0; i < 5; i++) {
                try {
                    driver.addBlackNode(graphName, nodeName);
                    fail("Expected exception to be thrown");
                } catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }
            }
        }
    }

    @Test
    public void testAddWhiteNodeSuccess() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        String graphName = "graph1";
        ArrayList<String> nodes = new ArrayList<>();
        nodes.add("Node1");
        nodes.add("Node2");
        nodes.add("Node3");
        nodes.add("Node4");
        nodes.add("Node5");

        driver.createGraph(graphName);
        int len  = 1;

        for (String nodeName : nodes) {
            driver.addWhiteNode(graphName, nodeName);
            String[] list = driver.listWhiteNodes(graphName).split(" ");
            assertEquals(len++, list.length);
            for (String s : list) {
                assertTrue(nodes.contains(s));
            }
        }
    }

    @Test
    public void testAddWhiteNodeAddTwiceFailure()
    {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        String graphName = "graph1";
        ArrayList<String> nodes = new ArrayList<>();
        nodes.add("Node1");
        nodes.add("Node2");
        nodes.add("Node3");
        nodes.add("Node4");
        nodes.add("Node5");

        driver.createGraph(graphName);

        for (String nodeName : nodes) {
            driver.addWhiteNode(graphName, nodeName);

            for (int i = 0; i < 5; i++) {
                try {
                    driver.addWhiteNode(graphName, nodeName);
                    fail("Expected exception to be thrown");
                } catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }
            }
        }
    }

    @Test
    public void testAddBlackAndThanWhiteWithTheSameLabel() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        String graphName = "graph1";
        ArrayList<String> nodes = new ArrayList<>();
        nodes.add("Node1");
        nodes.add("Node2");
        nodes.add("Node3");
        nodes.add("Node4");
        nodes.add("Node5");

        driver.createGraph(graphName);

        for (String nodeName : nodes) {
            driver.addBlackNode(graphName, nodeName);
        }

        for (String nodeName : nodes) {
            try {
                driver.addWhiteNode(graphName, nodeName);
                fail("Expected exception to be thrown");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }

    @Test
    public void testAddWhiteAndThanBlackWithTheSameLabel() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        String graphName = "graph1";
        ArrayList<String> nodes = new ArrayList<>();
        nodes.add("Node1");
        nodes.add("Node2");
        nodes.add("Node3");
        nodes.add("Node4");
        nodes.add("Node5");

        driver.createGraph(graphName);

        for (String nodeName : nodes) {
            driver.addWhiteNode(graphName, nodeName);
        }

        for (String nodeName : nodes) {
            try {
                driver.addBlackNode(graphName, nodeName);
                fail("Expected exception to be thrown");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }

    @Test
    public void testAddEdgeSuccess() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        String graphName = "graph1";
        ArrayList<String> whiteNodes = new ArrayList<>();
        whiteNodes.add("Node1");
        whiteNodes.add("Node2");
        whiteNodes.add("Node3");
        whiteNodes.add("Node4");
        whiteNodes.add("Node5");
        String blackNode1 = "BlackNode";

        driver.createGraph(graphName);
        Collections.sort(whiteNodes);

        driver.addBlackNode(graphName, blackNode1);
        for (String nodeName : whiteNodes) {
            driver.addWhiteNode(graphName, nodeName);
        }

        for (String nodeName : whiteNodes) {
            driver.addEdge(graphName, nodeName, blackNode1, nodeName + blackNode1);
            driver.addEdge(graphName, blackNode1, nodeName, blackNode1 + nodeName);
        }

        String[] parents = driver.listParents(graphName, blackNode1).split(" ");
        String[] children = driver.listChildren(graphName, blackNode1).split(" ");

        int index = 0;
        for (String nodeName : whiteNodes) {
            assertEquals(nodeName, parents[index]);
            assertEquals(nodeName, children[index]);
            assertEquals(nodeName, driver.getParentByEdgeLabel(graphName, blackNode1, nodeName+blackNode1));
            assertEquals(nodeName, driver.getChildByEdgeLabel(graphName, blackNode1, blackNode1+nodeName));
            assertEquals(blackNode1, driver.listChildren(graphName, nodeName));
            assertEquals(blackNode1, driver.listParents(graphName, nodeName));

            index++;
        }
    }
}
