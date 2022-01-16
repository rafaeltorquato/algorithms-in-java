package br.com.torquato.algorithms.data;

public record WeightedGraphNode<R, T extends GraphNode<R>>(T node, Double weight) {
}
