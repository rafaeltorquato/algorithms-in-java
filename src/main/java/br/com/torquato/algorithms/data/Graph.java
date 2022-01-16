package br.com.torquato.algorithms.data;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Graph<R, T extends GraphNode<R>> {

    private final Map<T, List<T>> edges = new HashMap<>();

    public void addNode(T node, List<T> edges) {
        this.edges.putIfAbsent(node, edges);
    }

    @SafeVarargs
    public final void addNode(T node, T... neighbors) {
        this.addNode(node, List.of(neighbors));
    }

}
