# Graph Acyclicity Detection System

A Java program that determines whether a directed graph is **acyclic** or **cyclic**, using the **sink-elimination algorithm**. If the graph is cyclic, it reconstructs and displays one full cycle using a **DFS-based** approach.

Built as solo coursework for *Algorithms: Theory, Design and Implementation* (Informatics Institute of Technology, affiliated with University of Westminster).

## How it works

1. The graph is read from a text file as an edge list and stored as an **adjacency list**.
2. **Sink elimination**: the algorithm repeatedly finds a "sink" (a vertex with no outgoing edges), removes it along with its incoming edges, and repeats.
   - If every vertex is eventually removed → the graph is **acyclic**.
   - If no sink can be found while vertices remain → the graph is **cyclic**.
3. **Cycle reconstruction**: if the graph is cyclic, a DFS traversal using a recursion-stack tracker identifies and returns one exact cycle path.
4. Runtime is measured and printed for each run.

## Input file format

The first line is the number of vertices. Every line after that is one directed edge, given as two space-separated vertex indices (`from to`).

Example — a graph with a cycle (`0 → 1 → 2 → 0`, with `3` as a dead-end):

```
4
0 1
1 2
2 0
2 3
```

Example — an acyclic graph:

```
5
0 1
0 2
1 2
1 3
1 4
2 3
4 3
4 5
```

## How to run

```bash
javac Graph.java ListGraph.java
java Graph
```

You'll be prompted for a filename:

```
Enter the name of the file you want to check the graph Acyclicity :
```

Enter the path to your input file, or press Enter to use the default (`example1.txt`).

## Sample output (cyclic graph)

```
-------- Original Graph Adjacency List --------
0 -> [1]
1 -> [2]
2 -> [0, 3]
3 -> []

------------- Sink Elimination ----------------
Removing sink vertex: 3

------- Graph after Sink Elimination ----------
0 -> [1]
1 -> [2]
2 -> [0]
3 -> removed

----------------------------------------------
Total number of sinks committed : 1

----------- Is this Graph Acyclic? -----------
No, this graph is 'Cyclic'
Cycle detected: [0, 1, 2, 0]

----------------- Time Taken -----------------
Elapsed time = 0.003 seconds
```

## Performance

The algorithm was benchmarked on graphs ranging from 160 to 10,240 vertices using a doubling hypothesis. Runtime scaled from **0.018s to 1.02s** as input size grew 64x, consistent with the theoretical worst-case complexity of **O(V × (V + E))** — approximately O(V²) for the sparse graphs tested.

## Tech stack

Java (no external libraries — core data structures and algorithms only).
