package br.com.torquato.algorithms;

import br.com.torquato.algorithms.data.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BreadthFirstSearchTest {

    private BreadthFirstSearch breadthFirstSearch;

    @BeforeEach
    void setup() {
        var graph = new Graph();
        var julia = new Graph.Vertex("Julia");
        var ana = new Graph.Vertex("Ana");
        var mark = new Graph.Vertex("Mark");
        var carlos = new Graph.Vertex("Carlos");
        var john = new Graph.Vertex("John");
        var spencer = new Graph.Vertex("Spencer");
        var logan = new Graph.Vertex("Logan");
        var bruce = new Graph.Vertex("Bruce");

        graph.addVertex(julia, List.of(ana, mark));
        graph.addVertex(ana, List.of(julia, bruce));
        graph.addVertex(bruce, List.of(spencer, john, carlos));
        graph.addVertex(carlos, List.of(john, julia, mark, ana));
        graph.addVertex(spencer, List.of(logan));

        this.breadthFirstSearch = new BreadthFirstSearch(graph);
    }

    @Test
    @DisplayName("Should find Logan from Ana")
    void shouldFindLoganFromAna() {
        var ana = new Graph.Vertex("Ana");
        var logan = new Graph.Vertex("Logan");

        assertTrue(this.breadthFirstSearch.search(ana, logan::equals).isPresent());
    }

    @Test
    @DisplayName("Should not find Rafael from Ana")
    void shouldNotFindRafaelFromAna() {
        var ana = new Graph.Vertex("Ana");
        var logan = new Graph.Vertex("Rafael");

        assertTrue(this.breadthFirstSearch.search(ana, logan::equals).isEmpty());
    }

}