package br.com.torquato.algorithms;

import br.com.torquato.algorithms.data.StringGraphNode;
import br.com.torquato.algorithms.data.WeightedGraph;
import br.com.torquato.algorithms.data.WeightedGraphNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DijkstrasSearchTest {

    @Test
    void shouldSuccessCase1() {
        //given
        StringGraphNode start = new StringGraphNode("start");
        StringGraphNode a = new StringGraphNode("a");
        StringGraphNode b = new StringGraphNode("b");
        StringGraphNode finish = new StringGraphNode("finish");
        var graph = new WeightedGraph<String, StringGraphNode>();

        graph.addNode(start, new WeightedGraphNode<>(a, 6.0), new WeightedGraphNode<>(b, 2.0));
        graph.addNode(a, new WeightedGraphNode<>(finish, 1.0));
        graph.addNode(b, new WeightedGraphNode<>(a, 3.0), new WeightedGraphNode<>(finish, 5.0));

        //when
        var dijkstrasSearch = new DijkstrasSearch<>(graph);
        dijkstrasSearch.search(start, finish);
        List<StringGraphNode> resultPath = dijkstrasSearch.getResultPath();

        //then
        Assertions.assertEquals(List.of(start, b, a, finish), resultPath);
    }

    @Test
    void shouldSuccessCase2() {
        //given
        StringGraphNode start = new StringGraphNode("start");
        StringGraphNode a = new StringGraphNode("a");
        StringGraphNode b = new StringGraphNode("b");
        StringGraphNode c = new StringGraphNode("c");
        StringGraphNode d = new StringGraphNode("d");
        StringGraphNode finish = new StringGraphNode("finish");

        var graph = new WeightedGraph<String, StringGraphNode>();
        //START -> A = 5, START -> B = 2
        graph.addNode(start, new WeightedGraphNode<>(a, 5.0), new WeightedGraphNode<>(b, 2.0));
        //A -> C = 4, A -> D = 2
        graph.addNode(a, new WeightedGraphNode<>(c, 4.0), new WeightedGraphNode<>(d, 2.0));
        //B -> A = 8, B -> D = 7
        graph.addNode(b, new WeightedGraphNode<>(a, 8.0), new WeightedGraphNode<>(d, 7.0));
        //C -> D = 6, C -> FINISH = 3
        graph.addNode(c, new WeightedGraphNode<>(d, 6.0), new WeightedGraphNode<>(finish, 3.0));
        //D -> FINISH = 1
        graph.addNode(d, new WeightedGraphNode<>(finish, 1.0));

        //when
        var dijkstrasSearch = new DijkstrasSearch<>(graph);
        dijkstrasSearch.search(start, finish);

        //then
        Assertions.assertEquals(List.of(start, a, d, finish), dijkstrasSearch.getResultPath());
        Assertions.assertEquals(8.0, dijkstrasSearch.getResultPathCost());
    }

    @Test
    void shouldSuccessCase3() {
        //given
        StringGraphNode start = new StringGraphNode("start");
        StringGraphNode a = new StringGraphNode("a");
        StringGraphNode b = new StringGraphNode("b");
        StringGraphNode c = new StringGraphNode("c");
        StringGraphNode finish = new StringGraphNode("finish");

        var graph = new WeightedGraph<String, StringGraphNode>();
        //START -> A = 10
        graph.addNode(start, new WeightedGraphNode<>(a, 10.0));
        //A -> B = 20
        graph.addNode(a, new WeightedGraphNode<>(b, 20.0));
        //B -> C = 1, B -> FINISH = 30
        graph.addNode(b, new WeightedGraphNode<>(c, 1.0), new WeightedGraphNode<>(finish, 30.0));
        //C -> A = 1
        graph.addNode(c, new WeightedGraphNode<>(a, 1.0));

        //when
        var dijkstrasSearch = new DijkstrasSearch<>(graph);
        dijkstrasSearch.search(start, finish);

        //then
        Assertions.assertEquals(List.of(start, a, b, finish), dijkstrasSearch.getResultPath());
        Assertions.assertEquals(60.0, dijkstrasSearch.getResultPathCost());
    }

    @Test
    void shouldSuccessCase4() {
        //given
        StringGraphNode start = new StringGraphNode("start");
        StringGraphNode a = new StringGraphNode("a");
        StringGraphNode b = new StringGraphNode("b");
        StringGraphNode c = new StringGraphNode("c");
        StringGraphNode finish = new StringGraphNode("finish");

        var graph = new WeightedGraph<String, StringGraphNode>();
        //START -> A = 2, START -> B = 2,
        graph.addNode(start, new WeightedGraphNode<>(a, 10.0), new WeightedGraphNode<>(b, 2.0));
        //A -> C = 2, A -> FINISH = 2
        graph.addNode(a, new WeightedGraphNode<>(c, 2.0), new WeightedGraphNode<>(finish, 2.0));
        //B -> A = 2
        graph.addNode(b, new WeightedGraphNode<>(a, 2.0));
        //C -> B = -1, C -> FINISH = 2
        graph.addNode(c, new WeightedGraphNode<>(b, -1.0), new WeightedGraphNode<>(finish, 2.0));

        //when
        var dijkstrasSearch = new DijkstrasSearch<>(graph);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            dijkstrasSearch.search(start, finish);
        });
    }

}