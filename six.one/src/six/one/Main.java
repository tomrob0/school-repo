import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

public class Main {
    
    private static void gapInsertionSort(int[] arr, int gap) {
        int n = arr.length;
        
        for (int i = gap; i < n; i++) {
            int temp = arr[i];
            int j = i;
            
            while (j >= gap && arr[j - gap] > temp) {
                arr[j] = arr[j - gap];
                j -= gap;
            }
            arr[j] = temp;
        }
    }
    
    public static int[] ShellSort(int[] arr, int[] intervals) {
        if (arr == null || arr.length <= 1) return arr;
        int[] result = Arrays.copyOf(arr, arr.length);
        
        for (int gap : intervals) {
            if (gap >= 1) {
                gapInsertionSort(result, gap);
            }
        }
        
        return result;
    }
    
    public static int[] ShellSort(int[] arr) {
        if (arr == null || arr.length <= 1) return arr;
        int n = arr.length;
        int[] result = Arrays.copyOf(arr, arr.length);
        
        int startPower = 1;
        while (startPower * 2 < n) {
            startPower *= 2;
        }
        
        int k = 0;
        int currentGap = startPower;
        
        while (currentGap >= 1) {
            gapInsertionSort(result, currentGap);
            
            if (currentGap == 1) break;
            
            if (k % 2 == 0) {
                currentGap /= 2;
            } else {
                currentGap /= 4;
            }
            
            if (currentGap < 1) currentGap = 1;
            
            k++;
            if (k > n) break;
        }
        
        return result;
    }
    
    public static int[] InsertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return arr;
        int[] result = Arrays.copyOf(arr, arr.length);
        int n = result.length;
        
        for (int i = 1; i < n; i++) {
            int temp = result[i];
            int j = i - 1;
            
            while (j >= 0 && result[j] > temp) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = temp;
        }
        
        return result;
    }
    
    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100000);
        }
        return arr;
    }
    
    private static void runTest(String name, int[] masterArray, Function<int[], int[]> sortFunction) {
        int[] arrayCopy = Arrays.copyOf(masterArray, masterArray.length);
        long startTime = System.currentTimeMillis();
        sortFunction.apply(arrayCopy);
        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime);
        
        System.out.printf("%s: \t %,d ms (Total Time)\n", name, totalTime);
    }
    
    public static void main(String[] args) {
        final int ARRAY_SIZE = 100000;
        
        int[] masterArray = generateRandomArray(ARRAY_SIZE);
        
        System.out.println("Sorting " + ARRAY_SIZE + " random integers...\n");
        
        runTest("Insertion Sort", masterArray, Main::InsertionSort);
        
        runTest("Shell Sort Auto", masterArray, Main::ShellSort);
        
        System.out.println("\nSorting complete!");
    }
}