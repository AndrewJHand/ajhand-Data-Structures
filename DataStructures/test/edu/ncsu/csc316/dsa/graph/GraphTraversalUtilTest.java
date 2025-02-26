package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for graphTraversalUtil
 * Checks the expected outputs of depth first search
 * and breadth first search
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class GraphTraversalUtilTest {


	/**
	 * the map
	 */
	private Map<Vertex<String>, Edge<Integer>> map;
	/**
	 * graph with edges and vertices
	 */
	private Graph<String, Integer> graph;
	
	/**
     * Create a new instance of an edge list graph before each test case executes
     */ 
    @Before
    public void setUp() {
       graph = new EdgeListGraph<String, Integer>();
    }
    
    /**
     * Test the output of depth first search on a graph
     */ 
    @Test
    public void testDepthFirstSearch() {
    	Vertex<String> v1 = graph.insertVertex("Raleigh");
        Vertex<String> v2 = graph.insertVertex("Asheville");
        Vertex<String> v3 = graph.insertVertex("Wilmington");
        Vertex<String> v4 = graph.insertVertex("Durham");
        Vertex<String> v5 = graph.insertVertex("Greenville");
        
        Edge<Integer> e1 = graph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = graph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = graph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = graph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = graph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = graph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = graph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = graph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = graph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = graph.insertEdge(v4, v5, 50);
        
        map = GraphTraversalUtil.depthFirstSearch(graph, v1);
        assertEquals(4, map.size());
        assertEquals(map.get(v2), e1);
        assertEquals(map.get(v3), e5);
        assertEquals(map.get(v4), e8);
        assertEquals(map.get(v5), e10);
    }
    
    /**
     * Test the output of the breadth first search
     */ 
    @Test
    public void testBreadthFirstSearch() {
    	Vertex<String> v1 = graph.insertVertex("Raleigh");
        Vertex<String> v2 = graph.insertVertex("Asheville");
        Vertex<String> v3 = graph.insertVertex("Wilmington");
        Vertex<String> v4 = graph.insertVertex("Durham");
        Vertex<String> v5 = graph.insertVertex("Greenville");
        
        Edge<Integer> e1 = graph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = graph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = graph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = graph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = graph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = graph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = graph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = graph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = graph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = graph.insertEdge(v4, v5, 50);
        
        map = GraphTraversalUtil.breadthFirstSearch(graph, v1);
        assertEquals(4, map.size());
        
    }
    
}