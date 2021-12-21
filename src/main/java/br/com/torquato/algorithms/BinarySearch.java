package br.com.torquato.algorithms;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Complexity O(log n)
 */
@Slf4j
public class BinarySearch<E extends Comparable<E>> {

    private final E[] sortedElements;

    @SafeVarargs
    public BinarySearch(E... sortedElements) {
        ensureIsSorted(sortedElements);
        this.sortedElements = sortedElements;
    }

    public Optional<E> getElement(E elementToSearch) {
        return getIndexOfElement(elementToSearch)
                .map(index -> this.sortedElements[index]);
    }

    public Optional<Integer> getIndexOfElement(E elementToSearch) {
        int iterationsCount = 0;
        Integer foundIndex = null;

        int highIndex = this.sortedElements.length - 1;
        int lowIndex = 0;
        while (lowIndex <= highIndex) {
            iterationsCount++;
            int middleIndex = (highIndex + lowIndex) / 2;
            E middleElement = this.sortedElements[middleIndex];

            int i = elementToSearch.compareTo(middleElement);
            if (i == 0) {
                foundIndex = middleIndex;
                break;
            } else if (i > 0) {
                lowIndex = middleIndex + 1;
            } else {
                highIndex = middleIndex - 1;
            }
        }
        log.info("Iterations count: {}", iterationsCount);
        log.info("Element {}", foundIndex != null ? "found." : "not found.");
        return Optional.ofNullable(foundIndex);
    }

    private void ensureIsSorted(E[] elements) {
        if (!isSorted(elements)) {
            throw new IllegalArgumentException("Elements aren't sorted.");
        }
    }

    private boolean isSorted(E[] elements) {
        if (elements.length == 0 || elements.length == 1) {
            return true;
        }
        for (int i = 0; i < elements.length - 1; i++) {
            E currentElement = elements[i];
            E nextElement = elements[i + 1];
            if (currentElement.compareTo(nextElement) > 0) {
                return false;
            }
        }
        return true;
    }

}
