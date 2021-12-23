package br.com.torquato.algorithms;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Algorithm abstraction to sort an array of elements.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Selection_sort">Selection Sort</a>
 */
public class SelectionSort<E extends Comparable<E>> {

    private final E[] unsortedArray;

    public SelectionSort(E... elements) {
        this.unsortedArray = elements;
    }

    /**
     * Makes a sorted copy of an array
     *
     * @return A sorted array copy
     */
    public E[] getSortedCopy() {
        E[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
        Arrays.fill(sortedArray, null);
        for (int index = 0; index < unsortedArray.length; index++) {
            int smallestIndex = findSmallestIndex(unsortedArray, sortedArray);
            sortedArray[index] = unsortedArray[smallestIndex];
        }

        return sortedArray;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private int findSmallestIndex(E[] searchableArray, E[] controlArray) {
        int smallestIndex = 0;
        E smallest = searchableArray[0];

        Class<? extends Comparable> smallestClass = smallest.getClass();
        E[] safeControlArray = Stream.of(controlArray)
                .filter(Objects::nonNull)
                .toArray(size -> (E[]) Array.newInstance(smallestClass, size));

        BinarySearch<E> eBinarySearch = new BinarySearch<>(safeControlArray);
        for (int index = 1; index < searchableArray.length; index++) {
            E smallestCandidate = searchableArray[index];
            boolean isSmallestCandidateAlreadyAdded = eBinarySearch.getIndexOfElement(smallestCandidate).isPresent();
            boolean isSmallestCandidateSmallerThanSmallest = smallestCandidate.compareTo(smallest) < 0;
            if (!isSmallestCandidateAlreadyAdded && isSmallestCandidateSmallerThanSmallest) {
                smallest = smallestCandidate;
                smallestIndex = index;
            }
        }
        return smallestIndex;
    }

}
