package edu.ncsu.csc316.dsa.graph;

//import edu.ncsu.csc316.dsa.graph.AdjacencyMapGraph.AMVertex;
//import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * An AdjacencyMatrixGraph is an implementation of the {@link Graph} abstract
 * data type. AdjacencyMatrixGraph maintains a list of vertices in the graph and
 * a list of edges in the graph. In addition, AdjacencyMatrixGraph maintains a
 * 2-dimensional array to store edges based on the endpoints of the edges
 * 
 * The AdjacencyMatrixGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author // Your Name Here 
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyMatrixGraph<V, E> extends EdgeListGraph<V, E> {

    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King
     *
     */
    private class MatrixVertex extends GraphVertex {

        /** The integer index of the vertex **/
        private int index;

        /**
         * Creates a new adjacency matrix vertex.
         * 
         * @param data       the data to store in the vertex
         */
        public MatrixVertex(V data) {
            super(data);
            index = getVertexIndex();
            System.out.println("Vertex " + data + " has index " + index); 
        }

        /**
         * Returns the row/column index of the vertex in the matrix
         * 
         * @return the index of the vertex in the matrix
         */
        public int getIndex() {
            return index;
        }
    }
    /**
     * the 2d matrix of edges
     */
    private GraphEdge[][] matrix;

    /**
     * indexer
     */
    private int vertexIndexer;

    /**
     * Creates a new undirected adjacency matrix graph
     */
    public AdjacencyMatrixGraph() {
        this(false);
        vertexIndexer = 0;
    }

    /**
     * Creates a new adjacency matrix graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */
    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(boolean directed) {
        super(directed);
        matrix = (GraphEdge[][]) (new AbstractGraph.GraphEdge[0][0]);
    }

    protected Vertex<V> createVertex(V vertexData) {
        return new MatrixVertex(vertexData);
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        MatrixVertex v1 = validate(vertex1);
        MatrixVertex v2 = validate(vertex2);
        return matrix[v1.getIndex()][v2.getIndex()];
    }

    private int getVertexIndex() {
        vertexIndexer++;
        return vertexIndexer - 1;
    }

    @SuppressWarnings("unchecked")
    private void growArray() {
        GraphEdge[][] temp = new AbstractGraph.GraphEdge[matrix.length + 1][matrix.length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        matrix = temp;
    }

    private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
        MatrixVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        for (int i = 0; i < matrix.length; i++) {
            GraphEdge e = matrix[i][v.getIndex()];
            if (e != null) {
                list.addLast(e);
            }
        }
        return list;
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return incomingEdgeList(vertex);
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return incomingEdgeList(vertex).size();
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) {
    	
    	if(isDirected()) {
    		MatrixVertex v1 = validate(vertex1);
            MatrixVertex v2 = validate(vertex2);
            GraphEdge edge = validate(super.insertEdge(v1, v2, edgeData));
            int row = v1.getIndex();
            int col = v2.getIndex();
            edge.getEndpoints()[0] = v1;
            edge.getEndpoints()[1] = v2;
            matrix[row][col] = edge;
            return edge;
    	}
    	else {
    		MatrixVertex v1 = validate(vertex1);
            MatrixVertex v2 = validate(vertex2);
            GraphEdge edge = validate(super.insertEdge(v1, v2, edgeData));
            int row = v1.getIndex();
            int col = v2.getIndex();
            edge.getEndpoints()[0] = v1;
            edge.getEndpoints()[1] = v2;
            matrix[row][col] = edge;
            matrix[col][row] = edge;
            return edge;
    	}
        
        //matrix[col][row] = edge;
        
        
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        growArray();
        return super.insertVertex(vertexData);
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return outgoingEdgeList(vertex).size();
    }

    private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
    	MatrixVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        for (int i = 0; i < matrix.length; i++) {
            GraphEdge e = matrix[v.getIndex()][i];
            if (e != null) {
                list.addLast(e);
            }
        }
        return list;
    }
    
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return outgoingEdgeList(vertex);
    }

    @Override
    public void removeEdge(Edge<E> edge) {
    	if(isDirected()) {
    		Vertex<V>[] arr = super.endVertices(edge);
            super.removeEdge(edge);
            MatrixVertex v1 = validate(arr[0]);
            MatrixVertex v2 = validate(arr[1]);
            matrix[v1.getIndex()][v2.getIndex()] = null;
    	}
    	else {
    		Vertex<V>[] arr = super.endVertices(edge);
            super.removeEdge(edge);
            MatrixVertex v1 = validate(arr[0]);
            MatrixVertex v2 = validate(arr[1]);
            matrix[v1.getIndex()][v2.getIndex()] = null;
            matrix[v2.getIndex()][v1.getIndex()] = null;
    	}
    	
        
    }
    
    /**
     * Safely casts a Vertex to a graph vertex
     * 
     * @return a graph vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid graph vertex
     * @param v is the vertex to validate
     */
    private MatrixVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMatrixGraph.MatrixVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency matrix vertex.");
        }
        return (MatrixVertex) v;
    }
}