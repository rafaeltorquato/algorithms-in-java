package br.com.torquato.algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTest {

    private BinarySearch<Integer> binarySearch;

    @BeforeEach
    public void setup() {
        this.binarySearch = new BinarySearch<>(1, 2, 3, 4, 5, 6, 7, 8);
    }

    @Test
    @DisplayName("Should return null index when searched element not found")
    void shouldReturnNullIndexWhenSearchedElementNotFound() {
        Optional<Integer> optionalIndexOfElement = binarySearch.getIndexOfElement(10);
        assertTrue(optionalIndexOfElement.isEmpty());
    }

    @Test
    @DisplayName("Should return the index of the element when that element exists")
    void shouldReturnTheIndexOfTheElementWhenThatElementExists() {
        List<Integer> elementsThatExists = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        for (Integer elementToSearch : elementsThatExists) {
            Optional<Integer> optionalIndexOfElement = binarySearch.getIndexOfElement(elementToSearch);
            assertTrue(optionalIndexOfElement.isPresent());
        }
    }

    @Test
    @DisplayName("Should return null when searched element not found")
    void shouldReturnNullWhenSearchedElementNotFound() {
        Optional<Integer> optionalElement = this.binarySearch.getElement(10);
        assertTrue(optionalElement.isEmpty());
    }

    @Test
    @DisplayName("Should return the element when that element exists")
    void shouldReturnTheElementWhenThatElementExists() {
        List<Integer> elementsThatExists = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        for (Integer elementToSearch : elementsThatExists) {
            Optional<Integer> optionalElement = binarySearch.getElement(elementToSearch);

            assertTrue(optionalElement.isPresent());
            assertEquals(elementToSearch, optionalElement.get());
        }
    }

}