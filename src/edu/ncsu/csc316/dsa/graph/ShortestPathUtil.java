package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * ShortestPathUtil provides a collection of behaviors for computing shortest
 * path spanning trees for a given graph.
 * 
 * The ShortestPathUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class ShortestPathUtil {
    
    /**
     * For a connected graph, returns a map that represents shortest path costs to
     * all vertices computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph the graph for which to compute the shortest path spanning tree
     * @param start the vertex at which to start computing the shorest path spanning
     *              tree
     * @return a map that represents the shortest path costs to all vertices in the
     *         graph
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> graph, Vertex<V> start) {
        
        //NOTE: since Dijkstra's algorithm is very similar to Prim-Jarnik's algorithm,
        //     you should review the provided Prim-Jarnik implementation in the next
        //     section of the lab on Minimum Spanning Trees
    	HeapAdaptablePriorityQueue<Integer, Vertex<V>> pq = new HeapAdaptablePriorityQueue<Integer, Vertex<V>>();
    	Map<Vertex<V>, Entry<Integer, Vertex<V>>> entries = new LinearProbingHashMap<>();
    	Map<Vertex<V>, Integer> known = new LinearProbingHashMap<>();
    	Set<Vertex<V>> set = new HashSet<>();
    	
    	for(Vertex<V> v: graph.vertices()) {
    		if(v == start) {
    			known.put(v, 0);
    		}
    		else {
    			known.put(v, 10000);
    		}
    		int cost = known.get(v);
    		Entry<Integer, Vertex<V>> entry = pq.insert(cost, v);
    		entries.put(v, entry);
    		
    	}
    	while(!pq.isEmpty()) {
    		Entry<Integer, Vertex<V>> entry = pq.deleteMin();
    		Vertex<V> u = entry.getValue();
    		set.add(u);
    		for(Edge<E> edge: graph.outgoingEdges(u)) {
    			Vertex<V> z = graph.opposite(u, edge);
    			if(!set.contains(z)) {
    				int r = edge.getElement().getWeight() + known.get(u);
    				if(r < known.get(z)) {
    					known.put(z, r);
    					pq.replaceKey(entries.get(z), r);
    				}
    			}
    		}
    	}
    	return known;
    }
    
    /**
     * For a connected graph, returns a map that represents shortest path spanning
     * tree edges computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>       the type of data in the graph vertices
     * @param <E>       the type of data in the graph edges
     * @param graph         the graph for which to compute the shortest path spanning
     *                  tree
     * @param start         the vertex at which to start computing the shortest path
     *                  spanning tree
     * @param costs the map of shortest path costs to reach each vertex in the
     *                  graph
     * @return a map that represents the shortest path spanning tree edges
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> graph, Vertex<V> start, Map<Vertex<V>, Integer> costs) {
    	Map<Vertex<V>, Edge<E>> map = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
    	for(Vertex<V> v: costs) {
    		if(!start.equals(v)) {
    			for(Edge<E> edge: graph.incomingEdges(v)) {
    				Vertex<V> u = graph.opposite(v, edge);
    				if(costs.get(v) == (costs.get(u) + edge.getElement().getWeight())) {
    					map.put(v, edge);
    				}
    			}
    		}
    	}
    	return map;
    }
}