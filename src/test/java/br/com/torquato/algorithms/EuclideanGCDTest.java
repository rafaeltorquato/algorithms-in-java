package br.com.torquato.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EuclideanGCDTest {

    @Test
    @DisplayName("Should calculate that the GCD between 270 and 192 is 6")
    void shouldCalculateThatTheGcdBetween270and192is6() {
        EuclideanGCD euclideanGCD = new EuclideanGCD(270, 192);
        int gcd = euclideanGCD.get();

        assertEquals(6, gcd);
    }

    @Test
    @DisplayName("Should calculate that the GCD between 1680 and 640 is 80")
    void shouldCalculateThatTheGcdBetween1680and192is80() {
        EuclideanGCD euclideanGCD = new EuclideanGCD(1680, 640);
        int gcd = euclideanGCD.get();

        assertEquals(80, gcd);
    }

}