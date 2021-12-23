package br.com.torquato.algorithms;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    private QuickSort<Integer> quickSort;

    @BeforeEach
    public void setup() {
        this.quickSort = new QuickSort<>(10, 9, 8, 5, 7, 4, 6, 3, 2, 1);
    }

    @Test
    @DisplayName("Should sort an unsorted array")
    void shouldSortAnUnsortedArray() {
        Integer[] sortedArray = this.quickSort.getSortedCopy();
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, sortedArray);
    }

}