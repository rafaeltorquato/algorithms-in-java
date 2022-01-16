package br.com.torquato.algorithms;

import br.com.torquato.algorithms.data.Graph;
import br.com.torquato.algorithms.data.GraphNode;
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
public class BreadthFirstSearch<R, T extends GraphNode<R>> {

    private final Graph<R, T> graph;

    /**
     * Search the nearest node that satisfies a condition
     *
     * @param node      Start the search from this node
     * @param predicate Condition
     * @return An Optional wih the nearest node or an empty Optional.
     */
    public Optional<T> search(T node, Predicate<T> predicate) {
        Deque<T> deque = new LinkedList<>(graph.getEdges().get(node));//fast add
        Set<T> searchedNodes = new HashSet<>(); //fast search
        Optional<T> result = Optional.empty();
        while (!deque.isEmpty()) {
            T next = deque.pop();
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
