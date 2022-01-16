package br.com.torquato.algorithms.data;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class WeightedGraph<R, T extends GraphNode<R>> {

    private final Map<T, List<T>> edges = new HashMap<>();
    private final Map<T, List<Double>> edgesWeight = new HashMap<>();

    public void addNode(T node, List<WeightedGraphNode<R, T>> edges) {
        List<T> neighbor = edges
                .stream()
                .map(WeightedGraphNode::node)
                .collect(Collectors.toList());
        this.edges.putIfAbsent(node, neighbor);
        List<Double> weights = edges
                .stream()
                .map(WeightedGraphNode::weight)
                .collect(Collectors.toList());
        this.edgesWeight.putIfAbsent(node, weights);
    }

    @SafeVarargs
    public final void addNode(T node, WeightedGraphNode<R, T>... neighbors) {
        this.addNode(node, List.of(neighbors));
    }

}
