/**
 * Name :- Nethmi Karunaratne
 * UOW ID :- W2 119803
 * IIT ID :- 20241218
 */

import java.io.File;
import java.util.List;
import java.util.Scanner;

public abstract class Graph {

    //checks the number of vertexes in the graph
    int size;

    /**
     * constructor for Graph class
     */
    public Graph(int s){
        size = s;
    }

    /**
     * This method uses the edges in the edge list to create the adjacency list
     */
    public void addEdge(int from, int to){
    }

    /**
     * This method finds the edges defined in the file
     * and calls the addEdge method in the child class
     */
    public void addEdgesFromFile(Scanner s){
        // As long as there are numbers left, read two and use them to add an edge
        while(s.hasNextInt()) {
            addEdge(s.nextInt(), s.nextInt());
        }
    }

    /**
     * This method displays the edge list in the file as an adjacency list to the user
     * @return the string
     */
    public abstract String toString();

    /**
     * main method that initiates the program
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //takes user's input to select the file to read
        System.out.println("Enter the name of the file you want to check the graph Acyclicity : ");
        String file = input.nextLine();

        //if user gives no input, then a default file is opened
        if (file == ""){
            file = "example1.txt";
            System.out.println("Selected default file : 'example1.txt'\n");
        }

        //creates a scanner to read from the loaded file
        Scanner s = null;

        try {
            s = new Scanner(new File(file));
        } catch (Exception e) {
            //shows an error message if the file is not found
            System.out.println("Could not open input file: " + e.getMessage());
            System.exit(0);
        }

        // Read number of vertices as displayed in the top of the file
        int numVertices = s.nextInt();

        // Create ListGraph object
        ListGraph g = new ListGraph(numVertices);

        // Add edges from file
        g.addEdgesFromFile(s);

        // Print original graph

        System.out.println("-------- Original Graph Adjacency List --------");
        System.out.println(g.toString());

        // Sink elimination step by step
        System.out.println("------------- Sink Elimination ----------------");
        int sinkCount = 0;
        int sink;
        while ((sink = g.findSink()) != -1) {
            System.out.println("Removing sink vertex: " + sink);
            g.removeVertex(sink);
            sinkCount ++;
            g.printGraph();
        }
        System.out.println("Total number of sinks committed : " + sinkCount+"\n");


        // Detect whether the graph is Acyclic or not
        System.out.println("----------- Is this Graph Acyclic? -----------");
        List<Integer> cycle = g.findCycle();

        if (cycle == null) {
            //Displays 'Yes' if the graph is Acyclic
            System.out.println("Yes, this is an 'Acyclic' graph");
            System.out.println("No cycle detected in the remaining graph.");
        } else {
            //Displays 'No' if the graph is Cyclic
            System.out.println("No, this is a 'Cyclic' graph");
            System.out.println("Cycle detected: " + cycle);
        }
    }
}
