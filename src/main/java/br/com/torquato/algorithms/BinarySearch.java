package br.com.torquato.algorithms;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

/**
 * Algorithm abstraction to find an element in an array of sorted elements.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Binary_search_algorithm">Binary Search</a>
 */
@Slf4j
public class BinarySearch<E extends Comparable<E>> {

    private static final Map<Integer, String> EXPLANATION_MAP = Map.of(
            0, "equal to",
            1, "greater than",
            -1, "smaller than"
    );

    private final E[] sortedElements;

    /**
     * Construct a new BinarySearch
     *
     * @param sortedElements Elements (sorted) that can be searched.
     */
    @SafeVarargs
    public BinarySearch(E... sortedElements) {
        ensureIsSorted(sortedElements);
        this.sortedElements = sortedElements;
    }

    /**
     * Get the index of an element.
     *
     * @param elementToSearch Element that you want to search
     * @return An Optional with value if the element exists, otherwise an empty Optional.
     */
    public Optional<Integer> getIndexOfElement(E elementToSearch) {
        int iterationsCount = 0;
        Integer foundIndex = null;
        log.info("Finding element '{}' in an array of {} elements.", elementToSearch, this.sortedElements.length);
        int highIndex = this.sortedElements.length - 1;
        int lowIndex = 0;
        while (lowIndex <= highIndex) {
            iterationsCount++;
            int middleIndex = (highIndex + lowIndex) / 2;

            E middleElement = this.sortedElements[middleIndex];
            int i = elementToSearch.compareTo(middleElement);
            log.info("Element '{}' is {} middle element '{}'.", elementToSearch, getExplanation(i), middleElement);
            if (i == 0) {
                foundIndex = middleIndex;
                break;
            } else if (i > 0) {
                lowIndex = middleIndex + 1;
            } else {
                highIndex = middleIndex - 1;
            }
        }
        log.info("{} iterations performed.", iterationsCount);
        log.info("Element {}", foundIndex != null ? "found." : "not found.");
        return Optional.ofNullable(foundIndex);
    }

    private String getExplanation(int i) {
        return EXPLANATION_MAP.get(i);
    }

    private void ensureIsSorted(E[] elements) {
        if (!isSorted(elements)) {
            throw new IllegalArgumentException("Elements aren't sorted.");
        }
    }

    private boolean isSorted(E[] elements) {
        if (elements.length > 1) {
            for (int i = 0; i < elements.length - 1; i++) {
                E currentElement = elements[i];
                E nextElement = elements[i + 1];
                if (currentElement.compareTo(nextElement) > 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
