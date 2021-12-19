package br.com.torquato.algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SelectionSortTest {

    private SelectionSort<Integer> selectionSort;

    @BeforeEach
    public void setup() {
        this.selectionSort = new SelectionSort<>();
    }

    @Test
    @DisplayName("Should sort an unsorted array")
    public void shouldSortAnUnsortedArray() {
        Integer[] array = new Integer[]{10, 9, 8, 5, 7, 4, 6, 3, 2, 1};
        Integer[] sortedArray = this.selectionSort.sort(array);
        assertTrue(Arrays.equals(sortedArray, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

}