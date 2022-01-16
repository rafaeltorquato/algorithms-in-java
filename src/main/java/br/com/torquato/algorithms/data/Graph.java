package br.com.torquato.algorithms.data;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Graph<T extends Comparable<T>> {

    private final Map<Node<T>, List<Node<T>>> edges = new HashMap<>();

    public void addNode(Node<T> vertex, List<Node<T>> neighbors) {
        edges.putIfAbsent(vertex, neighbors);
    }

    public record Node<T extends Comparable<T>>(T label) implements Comparable<Node<T>> {

        @Override
        public int compareTo(Node<T> o) {
            return label.compareTo(o.label);
        }

    }

}
