import java.io.File;
import java.util.Scanner;
import java.util.List;

public class GraphMain {

//    public static void main(String[] args) {
//        String inputFile = "example1.txt"; // your file with edge list
//        Scanner s = null;
//
//        try {
//            s = new Scanner(new File(inputFile));
//        } catch (Exception e) {
//            System.out.println("Could not open input file: " + e.getMessage());
//            System.exit(0);
//        }
//
//        // Read number of vertices
//        int numVertices = s.nextInt();
//
//        // Create ListGraph
//        ListGraph g = new ListGraph(numVertices);
//
//        // Add edges from file
//        g.addEdgesFromFile(s);
//
//        // Print original graph
//        System.out.println("--- Original Graph ---");
//        g.printGraph();
//
//        // Sink elimination step by step
//        System.out.println("--- Sink Elimination ---");
//        int sink;
//        while ((sink = g.findSink()) != -1) {
//            System.out.println("Removing sink vertex: " + sink);
//            g.removeVertex(sink);
//            g.printGraph();
//        }
//
//        // After sinks removed, detect cycle
//        System.out.println("--- Cycle Detection ---");
//        List<Integer> cycle = g.findCycle();
//        if (cycle == null) {
//            System.out.println("No cycle detected in the remaining graph.");
//        } else {
//            System.out.println("Cycle detected: " + cycle);
//        }
//    }
}