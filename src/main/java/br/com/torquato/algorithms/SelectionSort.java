package br.com.torquato.algorithms;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Complexity O(n²)
 */
public class SelectionSort<E extends Comparable<E>> {

    public E[] sort(E[] unsortedArray) {
        E[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
        Arrays.fill(sortedArray, null);
        for (int i = 0; i < unsortedArray.length; i++) {
            int smallestIndex = findSmallestIndex(unsortedArray, sortedArray);
            sortedArray[i] = unsortedArray[smallestIndex];
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
                .toArray(value -> (E[]) Array.newInstance(smallestClass, value));

        BinarySearch<E> eBinarySearch = new BinarySearch<>(safeControlArray);
        for (int index = 1; index < searchableArray.length; index++) {
            E smallestCandidate = searchableArray[index];
            boolean isSmallestCandidateAlreadyAdded = eBinarySearch.getElement(smallestCandidate).isPresent();
            boolean isSmallestCandidateSmallerThanSmallest = smallestCandidate.compareTo(smallest) < 0;
            if (!isSmallestCandidateAlreadyAdded && isSmallestCandidateSmallerThanSmallest) {
                smallest = smallestCandidate;
                smallestIndex = index;
            }
        }
        return smallestIndex;
    }

}