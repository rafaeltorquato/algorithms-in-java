package br.com.torquato.algorithms;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.partitioningBy;

/**
 * Algorithm abstraction to sort an array of elements.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Quicksort">Quick Sort</a>
 */
public class QuickSort<E extends Comparable<E>> {

    private final E[] elements;

    @SafeVarargs
    public QuickSort(E... elements) {
        this.elements = elements;
    }

    /**
     * Get a sorted copy of the array.
     *
     * @return the sorted copy.
     */
    @SuppressWarnings({"unchecked"})
    public E[] getSortedCopy() {
        return quickSort(new ArrayList<>(Arrays.asList(this.elements)))
                .toArray(size -> (E[]) Array.newInstance(this.elements[0].getClass(), size));
    }

    private static <E extends Comparable<E>> List<E> quickSort(List<E> elements) {
        if (elements.size() < 2) {
            return elements;
        }
        if (elements.size() == 2) {
            return getSortedListOfTwoElements(elements);
        }
        int randomPivotIndex = (int) (System.currentTimeMillis() % elements.size());
        E pivotElement = elements.remove(randomPivotIndex);

        Map<Boolean, List<E>> greaterSmallerPartition = elements
                .stream()
                .collect(partitioningBy(element -> element.compareTo(pivotElement) >= 0));
        List<E> elementsGreaterThenPivot = greaterSmallerPartition.get(true);
        List<E> elementsSmallerThanPivot = greaterSmallerPartition.get(false);

        ArrayList<E> sortedElements = new ArrayList<>(elements.size());
        sortedElements.addAll(quickSort(elementsSmallerThanPivot));
        sortedElements.add(pivotElement);
        sortedElements.addAll(quickSort(elementsGreaterThenPivot));
        return sortedElements;
    }

    private static <E extends Comparable<E>> List<E> getSortedListOfTwoElements(List<E> elements) {
        E firstElement = elements.get(0);
        E secondElement = elements.get(1);
        boolean smallerThenOrEqual = firstElement.compareTo(secondElement) <= 0;
        return smallerThenOrEqual ? elements : List.of(secondElement, firstElement);
    }

}
