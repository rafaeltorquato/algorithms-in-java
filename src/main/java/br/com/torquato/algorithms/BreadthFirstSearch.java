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

/**
 * The Breath First Search Algorithm to find the nearest vertex that satisfies a condition.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Breadth-first_search">Breath First Search Algorithm</a>
 */
@Slf4j
@RequiredArgsConstructor
public class BreadthFirstSearch {

    private final Graph graph;

    /**
     * Search the nearest vertex that satisfies a condition
     *
     * @param vertex    Start the search from this vertex
     * @param predicate Condition
     * @return An Optional wih the nearest vertex or an empty Optional.
     */
    public Optional<Vertex> search(Vertex vertex, Predicate<Vertex> predicate) {
        Deque<Vertex> deque = new LinkedList<>(graph.adjVertices.get(vertex));//fast add
        List<Vertex> searched = new LinkedList<>(); //fast add
        while (!deque.isEmpty()) {
            Vertex next = deque.pop();
            if (searched.contains(next)) continue;

            log.info("Searching on {}.", next);
            searched.add(next);
            if (predicate.test(next)) {
                log.info("Found!");
                return Optional.of(next);
            } else {
                Optional.ofNullable(graph.adjVertices.get(next))
                        .ifPresent(deque::addAll);
            }
        }
        return Optional.empty();
    }

    public record Vertex(String label) {}

    public static class Graph {

        private final Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();

        public void addVertex(Vertex vertex, List<Vertex> neighbors) {
            adjVertices.putIfAbsent(vertex, neighbors);
        }

    }

}
