package br.com.torquato.algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BreadthFirstSearchTest {

    private BreadthFirstSearch breadthFirstSearch;

    @BeforeEach
    void setup() {
        var graph = new BreadthFirstSearch.Graph();
        var julia = new BreadthFirstSearch.Vertex("Julia");
        var ana = new BreadthFirstSearch.Vertex("Ana");
        var mark = new BreadthFirstSearch.Vertex("Mark");
        var carlos = new BreadthFirstSearch.Vertex("Carlos");
        var john = new BreadthFirstSearch.Vertex("John");
        var spencer = new BreadthFirstSearch.Vertex("Spencer");
        var logan = new BreadthFirstSearch.Vertex("Logan");
        var bruce = new BreadthFirstSearch.Vertex("Bruce");

        graph.addVertex(julia, List.of(ana, mark));
        graph.addVertex(ana, List.of(julia, bruce));
        graph.addVertex(bruce, List.of(spencer, john, carlos));
        graph.addVertex(carlos, List.of(john, julia, mark, ana));
        graph.addVertex(spencer, List.of(logan));

        this.breadthFirstSearch = new BreadthFirstSearch(graph);
    }

    @Test
    void shouldFoundLoganFromAna() {
        var ana = new BreadthFirstSearch.Vertex("Ana");
        var logan = new BreadthFirstSearch.Vertex("Logan");

        assertTrue(this.breadthFirstSearch.search(ana, logan::equals).isPresent());
    }

    @Test
    void shouldNotFoundRafaelFromAna() {
        var ana = new BreadthFirstSearch.Vertex("Ana");
        var logan = new BreadthFirstSearch.Vertex("Rafael");

        assertTrue(this.breadthFirstSearch.search(ana, logan::equals).isEmpty());
    }

}