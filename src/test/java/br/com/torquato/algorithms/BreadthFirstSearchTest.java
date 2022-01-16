package br.com.torquato.algorithms;

import br.com.torquato.algorithms.data.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BreadthFirstSearchTest {

    private BreadthFirstSearch<String> breadthFirstSearch;

    @BeforeEach
    void setup() {
        var graph = new Graph<String>();
        var julia = new Graph.Node<>("Julia");
        var ana = new Graph.Node<>("Ana");
        var mark = new Graph.Node<>("Mark");
        var carlos = new Graph.Node<>("Carlos");
        var john = new Graph.Node<>("John");
        var spencer = new Graph.Node<>("Spencer");
        var logan = new Graph.Node<>("Logan");
        var bruce = new Graph.Node<>("Bruce");

        graph.addNode(julia, ana, mark);
        graph.addNode(ana, julia, bruce);
        graph.addNode(bruce, spencer, john, carlos);
        graph.addNode(carlos, john, julia, mark, ana);
        graph.addNode(spencer, logan);

        this.breadthFirstSearch = new BreadthFirstSearch<>(graph);
    }

    @Test
    @DisplayName("Should find Logan from Ana")
    void shouldFindLoganFromAna() {
        var ana = new Graph.Node<>("Ana");
        var logan = new Graph.Node<>("Logan");

        assertTrue(this.breadthFirstSearch.search(ana, logan::equals).isPresent());
    }

    @Test
    @DisplayName("Should not find Rafael from Ana")
    void shouldNotFindRafaelFromAna() {
        var ana = new Graph.Node<>("Ana");
        var logan = new Graph.Node<>("Rafael");

        assertTrue(this.breadthFirstSearch.search(ana, logan::equals).isEmpty());
    }

}