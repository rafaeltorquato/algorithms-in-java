package br.com.torquato.algorithms.data;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Graph<T extends Comparable<T>> {

    private final Map<Node<T>, List<Node<T>>> edges = new HashMap<>();

    public void addNode(Node<T> node, List<Node<T>> edges) {
        this.edges.putIfAbsent(node, edges);
    }

    @SafeVarargs
    public final void addNode(Node<T> node, Node<T>... edges) {
        this.addNode(node, List.of(edges));
    }

    public record Node<T extends Comparable<T>>(T label) implements Comparable<Node<T>> {

        @Override
        public int compareTo(Node<T> o) {
            return this.label.compareTo(o.label);
        }

    }

}
