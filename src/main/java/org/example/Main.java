package org.example;

public class Main {
    public static void main(String[] args) {
        PrimeNumberFinder primeNumberFinder = new PrimeNumberFinder();
        int upperLimit = 100000;

        // So we want to find every prime number between 1 and a limit that we set.
        // Let's look at how we can accomplish this and make this process more efficient.
        // Jump into the "PrimeNumberFinder" class to start reading about the process

        // Uncomment the methods below as you reach them in the explanation in "primeNumberFinder"
        // to test how efficient (or inefficient) they are

        primeNumberFinder.primeNumberCheckInefficient(upperLimit);
        //primeNumberFinder.primeNumberCheckSkipEvensAfterTwo(upperLimit);
        //primeNumberFinder.primeNumberCheckDivideByOdd(upperLimit);
        //primeNumberFinder.primeNumberCheckMostEfficient(upperLimit);
    }
}