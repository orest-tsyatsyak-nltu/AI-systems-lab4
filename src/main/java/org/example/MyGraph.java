package org.example;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class MyGraph extends SimpleGraph<Integer, DefaultEdge> {

    public MyGraph() {
        super(DefaultEdge.class);
    }

    public List<Integer> depthFirstTraversal(int startVertex) {
        Set<Integer> visitedVertexes = new LinkedHashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);
        visitedVertexes.add(startVertex);
        while (!stack.empty()) {
            Integer currentVertex = stack.peek();
            Optional<Integer> uncheckedVertex = edgesOf(currentVertex).stream()
                    .map(edge -> getNextVertex(edge, currentVertex))
                    .filter(vertex -> !visitedVertexes.contains(vertex))
                    .findFirst();
            if (uncheckedVertex.isEmpty()) {
                stack.pop();
            } else {
                Integer vertex = uncheckedVertex.get();
                stack.push(vertex);
                visitedVertexes.add(vertex);
            }
        }
        return visitedVertexes.stream().toList();
    }

    private Integer getNextVertex(DefaultEdge edge, Integer currentVertex) {
        Integer edgeSource = getEdgeSource(edge);
        return edgeSource.equals(currentVertex) ? getEdgeTarget(edge) : edgeSource;
    }

    public List<Integer> widthFirstTraversal(int startVertex){
        Set<Integer> visitedVertexes = new LinkedHashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startVertex);
        visitedVertexes.add(startVertex);
        while(!queue.isEmpty()){
            Integer currentVertex = queue.poll();
            edgesOf(currentVertex).stream()
                    .map(edge -> getNextVertex(edge, currentVertex))
                    .filter(vertex -> !visitedVertexes.contains(vertex))
                    .forEach(vertex -> {
                        queue.offer(vertex);
                        visitedVertexes.add(vertex);
                    });
        }
        return visitedVertexes.stream().toList();
    }

}
