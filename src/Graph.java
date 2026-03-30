import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.Stack;
import java.util.HashMap;
import java.util.Scanner;

public class Graph {

    int size;
    
    public Graph(int s){
        size = s;
    }
    
    public void addEdge(int from, int to){
    }
    
    public void removeVertex(int sink){
    }

    /**
     * This method finds the edges defined in the file
     * and calls the addEdge method in the child class
     */
    public void addEdgesFromFile(Scanner s){
        // As long as there are numbers left, read two and use them to add an edge
        while(s.hasNextInt()) {
            addEdge(s.nextInt(), s.nextInt());
            //removeEdge(s.nextInt(), s.nextInt());
        }
    }
    
    public String toString(){
        // Override in subclasses
        return "";
    }



    public static void main(String[] args) {
        String inputFile = "example1.txt"; // your file with edge list
        Scanner s = null;

        try {
            s = new Scanner(new File(inputFile));
        } catch (Exception e) {
            System.out.println("Could not open input file: " + e.getMessage());
            System.exit(0);
        }

        // Read number of vertices
        int numVertices = s.nextInt();

        // Create ListGraph
        ListGraph g = new ListGraph(numVertices);

        // Add edges from file
        g.addEdgesFromFile(s);

        // Print original graph
        System.out.println("--- Original Graph ---");
        g.printGraph();

        // Sink elimination step by step
        System.out.println("--- Sink Elimination ---");
        int sink;
        while ((sink = g.findSink()) != -1) {
            System.out.println("Removing sink vertex: " + sink);
            g.removeVertex(sink);
            g.printGraph();
        }

        // After sinks removed, detect cycle
        System.out.println("--- Cycle Detection ---");
        List<Integer> cycle = g.findCycle();
        if (cycle == null) {
            System.out.println("No cycle detected in the remaining graph.");
        } else {
            System.out.println("Cycle detected: " + cycle);
        }
    }
}
