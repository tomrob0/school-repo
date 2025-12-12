import java.util.Random;

public class Main {
    
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = arr[high];
            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (arr[j] <= pivot) {
                    i++;
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            int temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;
            int pi = i + 1;
            
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }
    
    public static void main(String[] args) {
        int size = 100000;
        Random rand = new Random();
        
        int[] arr1 = new int[size];
        for (int i = 0; i < size; i++) {
            arr1[i] = rand.nextInt(10000);
        }
        int[] arr2 = arr1.clone();
        
        long start = System.nanoTime();
        quickSort(arr1, 0, arr1.length - 1);
        long quickTime = System.nanoTime() - start;
        
        start = System.nanoTime();
        shellSort(arr2);
        long shellTime = System.nanoTime() - start;
        
        System.out.println("Array Size: " + size);
        System.out.printf("QuickSort: %.2f ms%n", quickTime / 1_000_000.0);
        System.out.printf("ShellSort: %.2f ms%n", shellTime / 1_000_000.0);
    }
}