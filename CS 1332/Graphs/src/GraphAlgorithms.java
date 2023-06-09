import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Start and graph can't be null.");
        } else {
            if (!graph.getVertices().contains(start)) {
                throw new IllegalArgumentException("Start isn't in the graph.");
            } else {
                Queue<Vertex<T>> vq = new LinkedList<Vertex<T>>();
                List<Vertex<T>> visted = new ArrayList<Vertex<T>>();

                visted.add(start);
                vq.add(start);


                while (!vq.isEmpty()) {
                    Vertex<T> v = vq.remove();
                    for (VertexDistance<T> vert : graph.getAdjList().get(v)) {
                        if (!visted.contains(vert.getVertex())) {
                            visted.add(vert.getVertex());
                            vq.add(vert.getVertex());
                        }
                    }
                }
                return visted;
            }
        }
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Start and graph can't be null.");
        } else {
            if (!graph.getVertices().contains(start)) {
                throw new IllegalArgumentException("Start isn't in graph.");
            } else {
                List<Vertex<T>> visted = new ArrayList<Vertex<T>>();

                Set<Vertex<T>> lVertices = new HashSet<Vertex<T>>();
                for (Vertex<T> element : graph.getVertices()) {
                    lVertices.add(element);
                }

                dHelper(start, graph, visted, lVertices);
                return visted;
            }
        }
    }

    /**
     * DFS helper method.
     * @param vertex Vertex of type T
     * @param graph Graph of type T
     * @param lVisted List of Vertex objects of type T
     * @param verts Set of Vertex objects of type T
     * @param <T> type T
     */
    private static <T> void dHelper(Vertex<T> vertex, Graph<T> graph, List<Vertex<T>> lVisted, Set<Vertex<T>> verts) {
        if (!verts.isEmpty()) {
            lVisted.add(vertex);
            verts.remove(vertex);

            for (VertexDistance<T> vert : graph.getAdjList().get(vertex)) {
                if (!lVisted.contains(vert.getVertex())) {
                    dHelper(vert.getVertex(), graph, lVisted, verts);
                }
            }

        } else {
            return;
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Start and graph can't be null.");
        } else {
            if (!graph.getVertices().contains(start)) {
                throw new IllegalArgumentException("Start is not in graph.");
            } else {
                List<Vertex<T>> visted = new ArrayList<Vertex<T>>();
                Queue<VertexDistance<T>> pq = new PriorityQueue<VertexDistance<T>>();
                Map<Vertex<T>, Integer> dMap = new HashMap<Vertex<T>, Integer>();
                int d = 0;

                Set<Vertex<T>> lVertices = new HashSet<Vertex<T>>();
                lVertices = graph.getVertices();


                for (Vertex<T> vert : graph.getVertices()) {
                    if (vert.equals(start)) {
                        dMap.put(vert, 0);
                    } else {
                        dMap.put(vert, Integer.MAX_VALUE);
                    }
                }

                VertexDistance<T> nStart = new VertexDistance<T>(start, 0);
                pq.add(nStart);

                while (!pq.isEmpty() && !lVertices.isEmpty()) {
                    VertexDistance<T> curr = pq.remove();
                    d = curr.getDistance();

                    if (!visted.contains(curr.getVertex())) {
                        visted.add(curr.getVertex());
                        lVertices.remove(curr.getVertex());
                        dMap.put(curr.getVertex(), d);

                        for (VertexDistance<T> vert : graph.getAdjList().get(curr.getVertex())) {
                            int nd = d + vert.getDistance();

                            if (!visted.contains(vert.getVertex())) {
                                VertexDistance<T> nCurr = new VertexDistance<T>(vert.getVertex(), nd);
                                pq.add(nCurr);
                            }
                        }
                    }
                }
                return dMap;
            }
        }
    }

    /**
     * Runs Kruskal's algorithm on the given graph and returns the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * Kruskal's will also require you to use a Disjoint Set which has been
     * provided for you. A Disjoint Set will keep track of which vertices are
     * connected given the edges in your current MST, allowing you to easily
     * figure out whether adding an edge will create a cycle. Refer
     * to the DisjointSet and DisjointSetNode classes that
     * have been provided to you for more information.
     *
     * You should NOT allow self-loops or parallel edges into the MST.
     *
     * By using the Disjoint Set provided, you can avoid adding self-loops and
     * parallel edges into the MST.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Set, and any class that implements the aforementioned
     * interfaces.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param graph the graph we are applying Kruskals to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph can't be null.");
        } else {
            Set<Edge<T>> mst = new HashSet<Edge<T>>();
            Queue<Edge<T>> pq = new PriorityQueue<>();
            DisjointSet<Vertex<T>> dSet = new DisjointSet<Vertex<T>>();
            pq.addAll(graph.getEdges());

            while (!pq.isEmpty() && mst.size() < graph.getEdges().size() - 1) {
                Edge<T> edge = pq.poll();
                Vertex<T> u = edge.getU();
                Vertex<T> v = edge.getV();

                if (dSet.find(u) != dSet.find(v)) {
                    Edge<T> nEdge = new Edge<>(v, u, edge.getWeight());

                    dSet.union(dSet.find(u), dSet.find(v));
                    mst.add(edge);
                    mst.add(nEdge);
                }
            }

            if (mst.size() < 2 * (graph.getVertices().size() - 1)) {
                return null;
            }

            return mst;
        }
    }
}
