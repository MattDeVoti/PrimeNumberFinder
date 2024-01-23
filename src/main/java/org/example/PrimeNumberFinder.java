package org.example;

public class PrimeNumberFinder {

    // As a quick refresher, a prime number is a number that cannot be divided by any number other
    // than 1 and itself.
    //
    // If we want to just jump right in to this process, we could try something like the following
    // Of course, the following method is very inefficient for a number of reasons we'll explore below
    //
    // Go back to the main method and run "findPrimeNumbersUpToInefficient" to see how long it takes to complete
    public void findPrimeNumbersUpToInefficient(int upperLimit) {
        long startTime = System.currentTimeMillis();

        for(int currentInt = 1; currentInt<=upperLimit; currentInt++){
            boolean isPrime = true;
            for(int i = 1; i<currentInt; i++){
                // We can use the method "isDivisible" below to determine if our "currentInt" is
                // cleanly divisible by the number in question

                if(i!=1 && isDivisible(i, currentInt)){
                    isPrime=false;
                }
            }
            if(isPrime){
                System.out.println(currentInt);
            }
        }
        long endTime = System.currentTimeMillis();
        long runTime = endTime-startTime;
        System.out.println("Took " + runTime + " milliseconds to run.");
    }

    // if the "currentNum" is cleanly divisible by "i" this will return true
    public boolean isDivisible(int i, int currentNum){
        if(currentNum%i==0){
            return true;
        }
        return false;
    }

    // The method "findPrimeNumbersUpToInefficient" got us the result we wanted but it took a while
    // to do it. Let's think about ways we can improve this process
    //
    // First of all, every even number above 2 can be ruled out since they can all be divided by 2 which means they aren't prime
    //
    // Go back to the main method and run "findPrimeNumbersUpToSkipEvensAfterTwo" to see how long it takes to complete
    // (Make sure to comment out "findPrimeNumbersUpToInefficient" so that you don't run both)
    public void findPrimeNumbersUpToSkipEvensAfterTwo(int upperLimit) {
        long startTime = System.currentTimeMillis();

        for(int currentInt = 1; currentInt<=upperLimit; currentInt++){
            boolean isPrime = true;
            for(int i = 1; i<currentInt; i++){
                if(i!=1 && isDivisible(i, currentInt)){
                    isPrime=false;
                }
            }
            if(isPrime){
                System.out.println(currentInt);
            }

            // We can add this condition to increment the currentInt an additional time which allows us to skip
            // all even numbers above two. This should take about half the time to run.
            if(currentInt>2){
                currentInt++;
            }
        }
        long endTime = System.currentTimeMillis();
        long runTime = endTime-startTime;
        System.out.println("Took " + runTime + " milliseconds to run.");
    }

    // This previous method helped to improve efficiency a bit but there's still more that we can do.
    // We don't need to check if numbers are divisible by 1 since all numbers are, so we can skip that step.
    // We don't need to check if numbers are divisible by 2 either since we're removing all even numbers aside
    // from 2.
    //
    // In fact, since we're only dealing with odd numbers after two, we don't need to divide by any even
    // numbers since odd numbers can't be divided by even numbers. Since two acts as an exception, we can print out
    // first two prime numbers and then run things more consistently after. This will help to keep the code more simple
    //
    // Go back to the main method and run "findPrimeNumbersUpToDivideByOdd" to see how long it takes to complete
    // (Make sure to comment out "findPrimeNumbersUpToSkipEvensAfterTwo" so that you don't run both)
    public void findPrimeNumbersUpToDivideByOdd(int upperLimit) {
        long startTime = System.currentTimeMillis();

        // We can print the first two prime numbers here
        System.out.println("1\n2");

        // Now we can start the rest of our search at 3 and increment by 2 each time without the need for special conditions
        for(int currentInt = 3; currentInt<=upperLimit; currentInt+=2){
            boolean isPrime = true;
            for(int i = 3; i<currentInt; i+=2){
                // Now we don't need to check if "i" equals 1 but we do need to check if "i" equals "currentInt"
                if(i!=currentInt && isDivisible(i, currentInt)){
                    isPrime=false;
                }
            }
            if(isPrime){
                System.out.println(currentInt);
            }
        }
        long endTime = System.currentTimeMillis();
        long runTime = endTime-startTime;
        System.out.println("Took " + runTime + " milliseconds to run.");
    }

    // This cuts the amount of time to run through all the numbers into about a quarter of what the last method took
    //
    // There's still more that can be done though. Not only can we break out of the loop if a number is determined
    // to not be prime, we don't even need to test ever number leading up to it to know that it is prime.
    //
    // For example, let's say we're testing the number 99 to determine if it's prime. On our first attempt we'll see that
    // it is divisible by 3, so we can say it is not prime. We don't need to keep looping through other numbers since we
    // already have our answer.
    //
    // On the other hand, let's take the number 97 (a prime number)
    // We don't need to check if it's divisible by 96 since 96 times 3 is already much larger than 97. In this case,
    // we already tested if 97 is divisible by 3 so there's no point in going up this high.
    //
    // So what should our limit be? If we're testing the number 97, should we only go a third of the way up to it?
    // Is stopping at 31 enough? Well, we certainly don't need to test above the number 31, but we can save even more time
    // than that!
    //
    // Let's say we get up to the number 10 while testing 97. 10 times 10 is 100 which as we know is above 97. This means
    // that any number above 10 that we could divide by 97 would yield a result lower than 10 which means that before we even
    // get to 10, we've divided 97 by every number we need to to test if it's a prime number.
    //
    // What this tells us is that if a number multiplied by itself is larger than the number we're testing, we're done testing
    // that number and we can move on to the next one. We know already that it's prime
    //
    // Let's implement this in the method below
    //
    // Go back to the main method and run "findPrimeNumbersUpToStopWhenConclusionReached" to see how long it takes to complete
    // (Make sure to comment out "findPrimeNumbersUpToDivideByOdd" so that you don't run both)
    public void findPrimeNumbersUpToMostEfficient(int upperLimit) {
        long startTime = System.currentTimeMillis();

        // We can print the first two prime numbers here
        System.out.println("1\n2");

        // Now we can start the rest of our search at 3 and increment by 2 each time without the need for special conditions
        for(int currentInt = 3; currentInt<=upperLimit; currentInt+=2){
            boolean isPrime = true;
            //
            for(int i = 3; (i*i)<currentInt; i+=2){
                if(i!=currentInt && isDivisible(i, currentInt)){
                    isPrime=false;
                    // This break moves the process along to the next number if a number is found to not be prime
                    break;
                }
            }
            if(isPrime){
                System.out.println(currentInt);
            }
        }
        long endTime = System.currentTimeMillis();
        long runTime = endTime-startTime;
        System.out.println("Took " + runTime + " milliseconds to run.");
    }

    // This method takes about 1/20th of the time that the previous method took to get the same result and
    // about 1/162nd of the time the initial method did (at least on my computer)
    //
    // For fun, try increasing or decreasing the upperLimit value in main and see how the results change
}
