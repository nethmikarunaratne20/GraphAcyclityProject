/**
 * Name :- Nethmi Karunaratne
 * UOW ID :- W2119803
 * IIT ID :- 20241218
 */

import java.util.List;
import java.util.ArrayList;

public class ListGraph extends Graph {

    //creates an arrayList to create the adjacency list of the graph
    private ArrayList<ArrayList<Integer>> adjacency;

    //creates a boolean array to store vertices as removed or not
    private boolean[] removed;

    /**
     * Constructor for ListGraph
     * @param s is the number of vertices in the graph
     */
    public ListGraph(int s) {
        //calls the constructor of Graph class
        super(s);

        //creates a new ArrayList named adjacency
        adjacency = new ArrayList<>(size);

        //creates an instance of the above instance variable
        removed = new boolean[size];
        for (int i = 0; i < size; i++) {
            //creates an array list for each index in the adjacency ArrayList
            adjacency.add(new ArrayList<>());

            //initially sets the removed status as false
            removed[i] = false;
        }
    }

    /**
     * The values read from the file are stored a from and to
     * from, this value is stored as the vertex and index value of the adjacency list
     * to, this values is stored as the value stored inside the from list
     */
    @Override
    public void addEdge(int from, int to) {
        if (from >= 0 && from < size && to >= 0 && to < size) {
            adjacency.get(from).add(to);
        }
    }

    /**
     * Finds a sink (vertex with no outgoing edges) that has not been removed.
     * @return index of sink or -1 if none exists
     */
    public int findSink() {
        for (int i = 0; i < size; i++) {
            if (!removed[i] && adjacency.get(i).isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Safely removes a vertex from the graph:
     * 1. Marks as removed
     * 2. Removes incoming edges to this vertex
     */
    public void removeVertex(int vertex) {
        if (vertex < 0 || vertex >= size || removed[vertex]) {
            return;
        }

        //mark vertex as removed
        removed[vertex] = true;

        // Remove incoming edges for the vertex
        for (int i = 0; i < size; i++) {
            adjacency.get(i).remove(Integer.valueOf(vertex));
        }
    }

    /**
     * Print the graph state (for debugging / sink elimination steps)
     */
    @Override
    public void printGraph() {

        for (int i = 0; i < size; i++) {
            if (!removed[i]) {
                System.out.println(i + " -> " + adjacency.get(i));
            } else {
                System.out.println(i + " -> removed");
            }
        }
    }

    /**
     * Detect a cycle using DFS
     * Returns an ArrayList containing a cycle if one exists, otherwise null
     * There can be multiple cycles, but this code displays only one cycle
     */
    public List<Integer> findCycle() {
        //creates an array to store the visited vertices
        boolean[] visited = new boolean[size];

        //creates an array to record the current vertex in a stack
        boolean[] recStack = new boolean[size];

        for (int i = 0; i < size; i++) {
            //if a vertex is not removed and not visited
            if (!removed[i] && !visited[i]) {

                //creates a new array 'path' to store the cycle path
                List<Integer> path = new ArrayList<>();

                //calls dfsCycle() to find the cycle
                if (dfsCycle(i, visited, recStack, path)) {
                    return path; // cycle found
                }
            }
        }
        return null; // no cycle
    }

    /**
     * Method for DFS cycle detection
     */
    private boolean dfsCycle(int v, boolean[] visited, boolean[] recStack, List<Integer> path) {

        //stores the current vertex as visited, in the stack and records in the path array
        visited[v] = true;
        recStack[v] = true;
        path.add(v);

        //iterate through every element inside the vertex in adjacency
        //neighbour mean the vertex that the current vertex is pointing to
        for (int neighbor : adjacency.get(v)) {

            //if a neighbour is removed, move to the next iteration
            if (removed[neighbor]){
                continue;
            }

            //if a neighbour is not visited, visit the neighbour
            //impersonates the behaviour of a stack
            if (!visited[neighbor]) {

                //creates a recursive function to check all pointing vertices
                if (dfsCycle(neighbor, visited, recStack, path)) {
                    return true;
                }
            }
            else if (recStack[neighbor]) {
                // cycle detected, trim path to cycle
                int idx = path.indexOf(neighbor);
                List<Integer> cycle = new ArrayList<>(path.subList(idx, path.size()));
                cycle.add(neighbor); // close the cycle
                path.clear();
                path.addAll(cycle);
                return true;
            }
        }

        recStack[v] = false;
        path.remove(path.size() - 1);
        return false;
    }

    /**
     * Checks if all vertices have been removed
     */
    public boolean isEmpty() {
        for (boolean r : removed) {
            if (!r) return false;
        }
        return true;
    }

    /**
     * checks if the graph is acyclic based on the remaining number of vertices
     * @return true if graph is acyclic, false if not
     */
    public boolean isAcyclic() {
        while (true) {
            if (isEmpty()) {
                return true;  // Step 1
            }
            int sink = findSink();
            if (sink == -1) {
                return false; // Step 2
            }
        }
    }

}