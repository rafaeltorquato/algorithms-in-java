package br.com.torquato.algorithms;

import br.com.torquato.algorithms.data.Graph;
import br.com.torquato.algorithms.data.StringGraphNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BreadthFirstSearchTest {

    private BreadthFirstSearch<String, StringGraphNode> breadthFirstSearch;

    @BeforeEach
    void setup() {
        var graph = new Graph<String, StringGraphNode>();
        var julia = new StringGraphNode("Julia");
        var ana = new StringGraphNode("Ana");
        var mark = new StringGraphNode("Mark");
        var carlos = new StringGraphNode("Carlos");
        var john = new StringGraphNode("John");
        var spencer = new StringGraphNode("Spencer");
        var logan = new StringGraphNode("Logan");
        var bruce = new StringGraphNode("Bruce");

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
        var ana = new StringGraphNode("Ana");
        var logan = new StringGraphNode("Logan");

        assertTrue(this.breadthFirstSearch.search(ana, logan::equals).isPresent());
    }

    @Test
    @DisplayName("Should not find Rafael from Ana")
    void shouldNotFindRafaelFromAna() {
        var ana = new StringGraphNode("Ana");
        var logan = new StringGraphNode("Rafael");

        assertTrue(this.breadthFirstSearch.search(ana, logan::equals).isEmpty());
    }

}