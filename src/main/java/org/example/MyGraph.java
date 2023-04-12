package org.example;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    public List<Integer> widthFirstTraversalSearch(int startVertex, int endVertex) {
        Map<Integer, Integer> neighbourToItsVertex = new HashMap<>();
        List<Integer> path = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startVertex);
        while (!queue.isEmpty()) {
            Integer currentVertex = queue.poll();
            List<Integer> nonVisitedVertexes = edgesOf(currentVertex).stream()
                    .map(edge -> getNextVertex(edge, currentVertex))
                    .filter(vertex -> !neighbourToItsVertex.containsKey(vertex))
                    .toList();
            for (var neighbourVertex : nonVisitedVertexes) {
                neighbourToItsVertex.put(neighbourVertex, currentVertex);
                if (neighbourVertex.equals(endVertex)) {
                    path = constructPath(neighbourToItsVertex, startVertex, endVertex);
                    break;
                }
                queue.offer(neighbourVertex);
            }
        }
        return path;
    }

    private List<Integer> constructPath(Map<Integer, Integer> neighbourToItsVertex, int startVertex, int endVertex) {
        List<Integer> path = new LinkedList<>();
        int currentVertex = endVertex;
        while (currentVertex != startVertex) {
            path.add(currentVertex);
            currentVertex = neighbourToItsVertex.get(currentVertex);
        }
        path.add(startVertex);
        Collections.reverse(path);
        return path;
    }

}
