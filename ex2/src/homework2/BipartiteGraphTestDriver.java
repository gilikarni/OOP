package homework2;

import java.util.*;

/**
 * This class implements a testing driver for BipartiteGraph. The driver
 * manages BipartiteGraphs whose nodes and edges are Strings.
 */
public class BipartiteGraphTestDriver {

    private Map<String, BipartiteGraph<String>> graphs;

    /**
     * @modifies this
     * @effects Constructs a new test driver.
     */
    public BipartiteGraphTestDriver () {
    	this.graphs = new HashMap<>();
    }

    
    /**
     * @requires graphName is not null
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially
     *          empty.
     */
    public void createGraph(String graphName) {
        if (graphs.containsKey(graphName)) {
            return;
        }
        BipartiteGraph<String> graph = new BipartiteGraph<>();
        graphs.put(graphName, graph);
    }

    
    /**
     * @requires createGraph(graphName)
     *           and nodeName is not null
     *           and neither addBlackNode(graphName,nodeName)
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a black node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addBlackNode(String graphName, String nodeName) {
        ColoredVertex<String> vertex = new ColoredVertex<String>(nodeName, ColoredVertex.VertexColor.BLACK);
        graphs.get(graphName).addVertex(vertex);
    }

    
    /**
     * @requires createGraph(graphName)
     *           and nodeName is not null
     *           and neither addBlackNode(graphName,nodeName)
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a white node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addWhiteNode(String graphName, String nodeName) {
        ColoredVertex<String> vertex = new ColoredVertex<>(nodeName, ColoredVertex.VertexColor.WHITE);
        graphs.get(graphName).addVertex(vertex);
    }

    
    /**
     * @requires createGraph(graphName)
     *           and ((addBlackNode(parentName) and addWhiteNode(childName))
     *              || (addWhiteNode(parentName) and addBlackNode(childName)))
     *           and edgeLabel is not null
     *           and node named parentName has no other outgoing edge labeled
     * 				edgeLabel
     *           and node named childName has no other incoming edge labeled
     * 				edgeLabel
     * @modifies graph named graphName
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph graphName. The new edge's label is the String
     * 			edgeLabel.
     */
    public void addEdge(String graphName,
    					String parentName, String childName, 
                        String edgeLabel) {
        graphs.get(graphName).addEdge(parentName, childName, edgeLabel);
    }

    private String createStringFromList(ArrayList<String> list) {
        String string = new String("");

        Collections.sort(list);

        boolean first = true;

        for (String s : list) {
            if (!first) {
                string = string + " ";
            }
            string = string + s;
            first = false;
        }

        return string;
    }
    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listBlackNodes(String graphName) {
        ArrayList<String> list = new ArrayList<>();
        for (ColoredVertex<String> vertex: graphs.get(graphName).getListOfBlackVertexes()){
            list.add(vertex.getVertexLabel());
        }
        return createStringFromList(list);
    }

    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listWhiteNodes(String graphName) {
        ArrayList<String> list = new ArrayList<>();
        for (ColoredVertex<String> vertex: graphs.get(graphName).getListOfWhiteVertexes()){
            list.add(vertex.getVertexLabel());
        }
        return createStringFromList(list);
    }

    
    /**
     * @requires createGraph(graphName) and createNode(parentName)
     * @return a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     */
    public String listChildren(String graphName, String parentName) {
        ArrayList<String> list = new ArrayList<>();
        for (ColoredVertex<String> vertex: graphs.get(graphName).getListOfVertexChildren(parentName)){
            list.add(vertex.getVertexLabel());
        }
        return createStringFromList(list);
    }

    
    /**
     * @requires createGraph(graphName) and createNode(childName)
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph graphName, in alphabetical order.
     */
    public String listParents(String graphName, String childName) {
        ArrayList<String> list = new ArrayList<>();
        for (ColoredVertex<String> vertex: graphs.get(graphName).getListOfVertexParents(childName)){
            list.add(vertex.getVertexLabel());
        }
        return createStringFromList(list);
    }

    
    /**
     * @requires addEdge(graphName, parentName, str, edgeLabel) for some
     * 			 string str
     * @return the name of the child of parentName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getChildByEdgeLabel(String graphName, String parentName,
    								   String edgeLabel) {
        return graphs.get(graphName).getChildByEdgeLabel(parentName, edgeLabel).getVertexLabel();
    }

    
    /**
     * @requires addEdge(graphName, str, childName, edgeLabel) for some
     * 			 string str
     * @return the name of the parent of childName that is connected by the 
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getParentByEdgeLabel(String graphName, String childName,
    									String edgeLabel) {
    	return graphs.get(graphName).getParentByEdgeLabel(childName, edgeLabel).getVertexLabel();
    }
}
