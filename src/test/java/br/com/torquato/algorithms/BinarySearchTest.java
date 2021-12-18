package br.com.torquato.algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTest {

    private BinarySearch<Integer> binarySearch;

    @BeforeEach
    public void setup() {
        List<Integer> sortedElements = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        this.binarySearch = new BinarySearch<>(sortedElements);
    }

    @Test
    @DisplayName("Should return null index when searched element not found")
    public void shouldReturnNullIndexWhenSearchedElementNotFound() {
        Integer elementFound = this.binarySearch.getIndexOfElement(10);
        assertNull(elementFound);
    }

    @Test
    @DisplayName("Should return the index of the element when that element exists")
    public void shouldReturnTheIndexOfTheElementWhenThatElementExists() {
        List<Integer> elementsThatExists = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        for (Integer elementToSearch : elementsThatExists) {
            Integer indexOfElement = binarySearch.getIndexOfElement(elementToSearch);
            assertTrue(indexOfElement > -1);
        }
    }

    @Test
    @DisplayName("Should return null when searched element not found")
    public void shouldReturnNullWhenSearchedElementNotFound() {
        Integer elementFound = this.binarySearch.getElement(10);
        assertNull(elementFound);
    }

    @Test
    @DisplayName("Should return the element when that element exists")
    public void shouldReturnTheElementWhenThatElementExists() {
        List<Integer> elementsThatExists = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        for (Integer elementToSearch : elementsThatExists) {
            Integer elementFound = binarySearch.getElement(elementToSearch);

            assertNotNull(elementFound);
            assertEquals(elementToSearch, elementFound);
        }
    }

}