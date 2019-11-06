package search;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Update this to make things go faster or take longer for timing studies.
        final int ARRAY_SIZE = 100000000;
        Random random = new Random();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < ARRAY_SIZE; ++i) {
            numbers.add(random.nextInt(ARRAY_SIZE));
        }

        Instant startTime = Instant.now();
        System.out.println(searchArray(numbers.get(1), numbers));
        System.out.println(searchArray(numbers.get(5), numbers));
        System.out.println(searchArray(numbers.get(900), numbers));
        System.out.println(searchArray(numbers.get(3200), numbers));
        System.out.println(searchArray(numbers.get(7400), numbers));
        System.out.println(searchArray(numbers.get(9876), numbers));
        Instant endTime = Instant.now();
        Duration totalTime = Duration.between(startTime, endTime);
        System.out.println("Total time was " + (totalTime.getNano() / 1000000) + " milliseconds");

        startTime = Instant.now();
        System.out.println(searchArray(2000000, numbers));
        System.out.println(searchArray(-45, numbers));
        endTime = Instant.now();
        totalTime = Duration.between(startTime, endTime);
        System.out.println("Total time was " + (totalTime.getNano() / 1000000) + " milliseconds");
    }

    private static boolean searchArray(int target, ArrayList<Integer> list) throws InterruptedException {
        // You can replace ThreadedSearch with LinearSearch to see this work with
        // the given linear search code.

//        Searcher<Integer> searcher = new LinearSearch<>();

        // This specifies 4 threads for the tests. It would be a good idea to play
        // with this and see how that changes things. Keep in mind that your number
        // of threads *may* need to evenly divide the length of the list being
        // searched (ARRAY_SIZE in this case).
         Searcher<Integer> searcher = new ThreadedSearch<>(4);

        /* ----- TEST DATA FOR ThreadedSearch VS LinearSearch -----
         *
         * Before adding another if statement to run() in ThreadedSearch:
         * LinearSearch Results (1):
         * true
         * true
         * true
         * true
         * true
         * true
         * Total time was 4 milliseconds
         * true
         * false
         * Total time was 354 milliseconds
         *
         * ThreadedSearch Results (1):
         * true
         * true
         * true
         * true
         * true
         * true
         * Total time was 673 milliseconds
         * true
         * false
         * Total time was 276 milliseconds
         *
         * LinearSearch Results (2):
         * true
         * true
         * true
         * true
         * true
         * true
         * Total time was 4 milliseconds
         * true
         * false
         * Total time was 191 milliseconds
         *
         * ThreadedSearch Results (2):
         * true
         * true
         * true
         * true
         * true
         * true
         * Total time was 635 milliseconds
         * true
         * false
         * Total time was 237 milliseconds
         *
         * Obviously something is going wrong here and I am not quite sure yet what it is. Threads should not be taking
         * longer than Linear according to the README.MD statement.
         *
         * Added an if statement to the join function and it caused NullPointerExceptions
         * Added an if statement to the run() before the for loop that makes sure answer is not true. (FOUND IT) - thought I did
         *
         *
         * After adding another if statement to run() in ThreadedSearch:
         * ThreadedSearch Results (1):
         * true
         * true
         * true
         * true
         * true
         * true
         * Total time was 175 milliseconds
         * true
         * false
         * Total time was 269 milliseconds
         *
         * LinearSearch Results (3):
         * true
         * true
         * true
         * true
         * true
         * true
         * Total time was 5 milliseconds
         * true
         * false
         * Total time was 281 milliseconds
         *
         * this is more like what i want to see.
         *
         * LinearSearch Results (3):
         * true
         * true
         * true
         * true
         * true
         * true
         * Total time was 3 milliseconds
         * false
         * false
         * Total time was 377 milliseconds
         *
         * In conclusion, Linear got lucky once but now Threading is faster by a small margin, which follows what was stated in the README.MD.
         * There are time when linear breaks a win in faster than threads but it is a smaller percentage than threads beating linear. This could be for many factors.
         *
         * WAIT!
         *
         * Moved if statement into the for loop and now it contests with LinearSearch's first total time
         *
         * ThreadedSearch Results (1):
         * true
         * true
         * true
         * true
         * true
         * true
         * Total time was 9 milliseconds
         * true
         * false
         * Total time was 295 milliseconds
         *
         * End of Tests!
         *
         */


        return searcher.search(target, list);
    }

}
