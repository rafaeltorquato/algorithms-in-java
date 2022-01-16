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
 * The Breath First Search Algorithm to find the nearest node that satisfies a condition.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Breadth-first_search">Breath First Search Algorithm</a>
 */
@Slf4j
@RequiredArgsConstructor
public class BreadthFirstSearch<T extends Comparable<T>> {

    private final Graph<T> graph;

    /**
     * Search the nearest node that satisfies a condition
     *
     * @param node    Start the search from this node
     * @param predicate Condition
     * @return An Optional wih the nearest node or an empty Optional.
     */
    public Optional<Graph.Node<T>> search(Graph.Node<T> node, Predicate<Graph.Node<T>> predicate) {
        Deque<Graph.Node<T>> deque = new LinkedList<>(graph.getEdges().get(node));//fast add
        Set<Graph.Node<T>> searchedNodes = new HashSet<>(); //fast search
        Optional<Graph.Node<T>> result = Optional.empty();
        while (!deque.isEmpty()) {
            Graph.Node<T> next = deque.pop();
            if (searchedNodes.contains(next)) continue;

            log.info("Searching on {}.", next);
            searchedNodes.add(next);
            if (predicate.test(next)) {
                log.info("Found!");
                result = Optional.of(next);
                break;
            } else {
                Optional.ofNullable(graph.getEdges().get(next))
                        .ifPresent(deque::addAll);
            }
        }
        return result;
    }

}
