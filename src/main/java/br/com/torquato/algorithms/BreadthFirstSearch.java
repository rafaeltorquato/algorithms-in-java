package br.com.torquato.algorithms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@RequiredArgsConstructor
public class BreadthFirstSearch {

    private final Graph graph;

    public boolean search(Vertex vertex, Predicate<Vertex> predicate) {
        Deque<Vertex> deque = new LinkedList<>(graph.adjVertices.get(vertex));
        List<Vertex> searched = new LinkedList<>();
        while (!deque.isEmpty()) {
            Vertex next = deque.pop();
            if(searched.contains(next)) continue;

            log.info("Searching on {}.", next);

            searched.add(next);
            if (predicate.test(next)) {
                log.info("Found!");
                return true;
            } else {
                Optional.ofNullable(graph.adjVertices.get(next))
                                .ifPresent(deque::addAll);
            }
        }
        return false;
    }

    public record Vertex(String label) {}

    public static class Graph {

        private final Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();

        public void addVertex(Vertex vertex, List<Vertex> neighbors) {
            adjVertices.putIfAbsent(vertex, neighbors);
        }

    }

}
