import java.util.List;
import java.util.ArrayList;

public class ListGraph extends Graph {

    private ArrayList<ArrayList<Integer>> adjacency;
    private boolean[] removed; // tracks removed vertices

    public ListGraph(int s) {
        super(s);
        adjacency = new ArrayList<>(size);
        removed = new boolean[size];
        for (int i = 0; i < size; i++) {
            adjacency.add(new ArrayList<>());
            removed[i] = false;
        }
    }

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
        if (vertex < 0 || vertex >= size || removed[vertex]) return;

        removed[vertex] = true;

        // Remove incoming edges
        for (int i = 0; i < size; i++) {
            adjacency.get(i).remove(Integer.valueOf(vertex));
        }
    }

    /** Checks if all vertices have been removed */
    public boolean isEmpty() {
        for (boolean r : removed) {
            if (!r) return false;
        }
        return true;
    }

    /** Print the graph state (for debugging / sink elimination steps) */
    public void printGraph() {
        System.out.println("Graph state:");
        for (int i = 0; i < size; i++) {
            if (!removed[i]) {
                System.out.println(i + " -> " + adjacency.get(i));
            } else {
                System.out.println(i + " -> removed");
            }
        }
        System.out.println("----------------------------------------------");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(i).append(": ");
            sb.append(adjacency.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Task 5: Detect a cycle using DFS
     * Returns an ArrayList containing a cycle if one exists, otherwise null
     */
    public List<Integer> findCycle() {
        boolean[] visited = new boolean[size];
        boolean[] recStack = new boolean[size]; // recursion stack for DFS

        for (int i = 0; i < size; i++) {
            if (!removed[i] && !visited[i]) {
                List<Integer> path = new ArrayList<>();
                if (dfsCycle(i, visited, recStack, path)) {
                    return path; // cycle found
                }
            }
        }
        return null; // no cycle
    }

    /** Helper for DFS cycle detection */
    private boolean dfsCycle(int v, boolean[] visited, boolean[] recStack, List<Integer> path) {
        visited[v] = true;
        recStack[v] = true;
        path.add(v);

        for (int neighbor : adjacency.get(v)) {
            if (removed[neighbor]) continue;
            if (!visited[neighbor]) {
                if (dfsCycle(neighbor, visited, recStack, path)) return true;
            } else if (recStack[neighbor]) {
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

}