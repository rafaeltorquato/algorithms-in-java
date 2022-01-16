package br.com.torquato.algorithms.data;

public record StringGraphNode(String value) implements GraphNode<String> {

    @Override
    public int compareTo(GraphNode<String> o) {
        return this.value.compareTo(o.value());
    }

}
