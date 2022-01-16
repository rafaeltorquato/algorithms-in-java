package br.com.torquato.algorithms;

import br.com.torquato.algorithms.data.GraphNode;
import br.com.torquato.algorithms.data.WeightedGraph;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Algorithm abstraction to find the cheapest path from a start node to another node.
 * <br/> Isn't thread safe.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm">Dijkstra's Search Algorithm</a>
 */
@RequiredArgsConstructor
public class DijkstrasSearch<R, T extends GraphNode<R>> {

    private final WeightedGraph<R, T> graph;
    private final Map<T, Double> travelCosts = new HashMap<>();
    private final Map<T, T> parents = new HashMap<>();
    private final Set<T> processed = new HashSet<>();

    /**
     * The result path ordered
     */
    @Getter
    private List<T> resultPath;
    /**
     * The cost of the resultPath
     */
    @Getter
    private Double resultPathCost;

    /**
     * Search the cheapest path from start node to end node. Results on resulPath and resultPathCost attributes.
     *
     * @param start Start node
     * @param end   End node
     */
    public void search(T start, T end) {
        if (this.resultPath != null) throw new IllegalStateException("Search already performed.");

        this.travelCosts.put(end, Double.MAX_VALUE);
        this.parents.put(end, null);

        recursiveSearch(start, 0.0);

        LinkedList<T> path = new LinkedList<>();
        T current = end;
        do {
            path.add(current);
        } while ((current = parents.get(current)) != null);
        Collections.reverse(path);
        this.resultPath = path;
        this.resultPathCost = this.travelCosts.get(end);
    }

    private void recursiveSearch(T node, Double nodeWeight) {
        List<T> neighbors = this.graph.getEdges().get(node);
        if (neighbors == null) return;

        List<Double> neighborsCurrentWeight = this.graph.getEdgesWeight().get(node);

        for (int i = 0; i < neighbors.size(); i++) {
            T neighbor = neighbors.get(i);
            Double neighborWeight = neighborsCurrentWeight.get(i);
            if (neighborWeight < 0.0) {
                throw new IllegalArgumentException("Dijkstra algorithm can't compute negative values.");
            }

            Double newTravelCost = neighborWeight + nodeWeight;
            Double neighborPreviousCost = this.travelCosts.get(neighbor);
            // newTravelCost is cheaper than neighborPreviousCost
            if (neighborPreviousCost == null || neighborPreviousCost > newTravelCost) {
                this.travelCosts.put(neighbor, newTravelCost);
                this.parents.put(neighbor, node);
            }
        }
        this.processed.add(node);

        Map.Entry<T, Double> minEntry = getMinEntry();
        recursiveSearch(minEntry.getKey(), minEntry.getValue());
    }

    private Map.Entry<T, Double> getMinEntry() {
        return this.travelCosts
                .entrySet()
                .stream()
                .filter(entry -> !this.processed.contains(entry.getKey()))
                .min(Map.Entry.comparingByValue())
                .orElseThrow();
    }

}
