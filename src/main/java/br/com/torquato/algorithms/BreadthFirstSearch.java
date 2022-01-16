package br.com.torquato.algorithms;

import br.com.torquato.algorithms.data.Graph;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;
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
    public Optional<Graph.Vertex> search(Graph.Vertex vertex, Predicate<Graph.Vertex> predicate) {
        Deque<Graph.Vertex> deque = new LinkedList<>(graph.getAdjVertices().get(vertex));//fast add
        Set<Graph.Vertex> searchedVertices = new HashSet<>(); //fast search
        Optional<Graph.Vertex> result = Optional.empty();
        while (!deque.isEmpty()) {
            Graph.Vertex next = deque.pop();
            if (searchedVertices.contains(next)) continue;

            log.info("Searching on {}.", next);
            searchedVertices.add(next);
            if (predicate.test(next)) {
                log.info("Found!");
                result = Optional.of(next);
                break;
            } else {
                Optional.ofNullable(graph.getAdjVertices().get(next))
                        .ifPresent(deque::addAll);
            }
        }
        return result;
    }

}
