package four.one;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Main {


    public static int[] generate_random_unique(int start, int end, int total) {
        if (total > end - start) {
            System.out.println("Error: total numbers requested exceeds the range.");
            return new int[0];
        }
        HashSet<Integer> uniqueNumbers = new HashSet<>();
        Random rand = new Random();
        while (uniqueNumbers.size() < total) {
            int num = rand.nextInt(end - start) + start;
            uniqueNumbers.add(num);
        }
        int[] result = new int[total];
        int index = 0;
        for (int num : uniqueNumbers) {
            result[index++] = num;
        }
        return result;
    }

 
    public static boolean Scan(int[] input, int target) {
        for (int num : input) {
            if (num == target) return true;
        }
        return false;
    }


    public static boolean Stor(int[] input, int target) {
        if (input.length == 0) return false;

        int max = input[0];
        for (int num : input) {
            if (num > max) max = num;
        }

        int[] storage = new int[max + 1];
        for (int num : input) {
            storage[num] = 1;
        }

       

        return target < storage.length && storage[target] == 1;
    }


    public static void main(String[] args) {
        int start = 1;
        int end = 20;
        int total = 10;

        int[] randomArray = generate_random_unique(start, end, total);
        System.out.println("Generated random unique array: " + Arrays.toString(randomArray));

        int target1 = 5;
        System.out.println("Scan for " + target1 + ": " + Scan(randomArray, target1));
        System.out.println("Stor for " + target1 + ": " + Stor(randomArray, target1));

        int target2 = 15;
        System.out.println("Scan for " + target2 + ": " + Scan(randomArray, target2));
        System.out.println("Stor for " + target2 + ": " + Stor(randomArray, target2));
    }
}
