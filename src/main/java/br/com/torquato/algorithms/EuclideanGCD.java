package br.com.torquato.algorithms;

import lombok.extern.slf4j.Slf4j;

/**
 * The Euclidean Algorithm for finding the Greatest Common Divisor (GCD) of two integers A and B, the largest integer
 * that divides both A and B.
 *
 * @see
 * <a href="https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/the-euclidean-algorithm">Euclidean Algorithm</a>
 */
@Slf4j
public class EuclideanGCD {

    private final int a;
    private final int b;

    /**
     * @param a Integer A
     * @param b Integer B
     */
    public EuclideanGCD(int a, int b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Calculate the Greatest Common Divisor (GCD)
     *
     * @return the GCD
     */
    public int calculate() {
        return calculate(Math.max(this.a, this.b), Math.min(this.a, this.b));
    }

    private static int calculate(int max, int min) {
        log.info("GCD({},{})", max, min);
        if (max == 0) return min;
        if (min == 0) return max;
        return calculate(min, max % min);
    }

}
