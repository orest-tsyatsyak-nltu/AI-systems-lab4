package org.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);
        MyGraph graph = getGraphForVariant9();
        System.out.print("Enter start vertex: ");
        int startVertex = Integer.parseInt(consoleScanner.nextLine());
        System.out.print("Enter end vertex: ");
        int endVertex = Integer.parseInt(consoleScanner.nextLine());
        System.out.println("Depth first traversal: " + graph.depthFirstTraversal(startVertex));
        System.out.println("Width first traversal search: " + graph.widthFirstTraversalSearch(startVertex, endVertex));
    }

    private static MyGraph getGraphForVariant9() {
        MyGraph graph = new MyGraph();
        graph.addVertex(1);
        graph.addVertex(6);
        graph.addVertex(5);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 6);
        graph.addEdge(6, 5);
        graph.addEdge(6, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        return graph;
    }
}
