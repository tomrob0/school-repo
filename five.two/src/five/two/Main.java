package five.two;
import java.util.Arrays;
import java.util.Random;


public class Main {


    public static int[] RandomizedArray(int size, int start, int end) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(end - start + 1) + start;
        }
        return arr;
    }


    public static void BubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; 
        }
    }

    public static void SelectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int size = 100000; 
        int startRange = 1;
        int endRange = 1000000;

        int[] originalArray = RandomizedArray(size, startRange, endRange);
        int[] arrayForBubble = Arrays.copyOf(originalArray, originalArray.length);
        int[] arrayForSelection = Arrays.copyOf(originalArray, originalArray.length);
        long startBubble = System.currentTimeMillis();
        BubbleSort(arrayForBubble);
        long finishBubble = System.currentTimeMillis();
        long timeBubble = finishBubble - startBubble;
        System.out.println("Sorting a random array size of " + size + " took Bubble Sort " + timeBubble + "ms to complete.");
        long startSelection = System.currentTimeMillis();
        SelectionSort(arrayForSelection);
        long finishSelection = System.currentTimeMillis();
        long timeSelection = finishSelection - startSelection;
        System.out.println("Sorting a random array size of " + size + " took Selection Sort " + timeSelection + "ms to complete.");
    }
}
