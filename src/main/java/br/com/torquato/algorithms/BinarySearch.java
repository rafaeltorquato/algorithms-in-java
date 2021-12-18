package br.com.torquato.algorithms;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
public class BinarySearch<E extends Comparable<E>> {

    private final List<E> sortedElements;

    public BinarySearch(List<E> elements) {
        ArrayList<E> copyOfElements = new ArrayList<>(elements);
        Collections.sort(copyOfElements);
        this.sortedElements = copyOfElements;
    }

    public Optional<E> getElement(E elementToSearch) {
        return getIndexOfElement(elementToSearch)
                .map(this.sortedElements::get);
    }

    public Optional<Integer> getIndexOfElement(E elementToSearch) {
        int iterationsCount = 0;
        Integer foundIndex = null;

        int highIndex = this.sortedElements.size() - 1;
        int lowIndex = 0;
        while (lowIndex <= highIndex) {
            iterationsCount++;
            int middleIndex = (highIndex + lowIndex) / 2;
            E middleElement = this.sortedElements.get(middleIndex);

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

}
