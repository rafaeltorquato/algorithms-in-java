package br.com.torquato.algorithms.data;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Graph {

    private final Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();

    public void addVertex(Vertex vertex, List<Vertex> neighbors) {
        adjVertices.putIfAbsent(vertex, neighbors);
    }

    public record Vertex(String label) implements Comparable<Vertex> {

        @Override
        public int compareTo(Vertex o) {
            return label.compareTo(o.label);
        }

    }

}
