package eight.three;
import java.util.Random;

public class Main {
    
    // --- INSERTION SORT IMPLEMENTATION ---
    // InsertionSort (O(N^2) worst/average-case time complexity)
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key, 
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
    
    // --- SHELL SORT IMPLEMENTATION ---
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
    
    // --- MERGE SORT IMPLEMENTATION ---
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        
        while (i < n1)
            arr[k++] = L[i++];
        while (j < n2)
            arr[k++] = R[j++];
    }
    
    // --- MAIN BENCHMARK METHOD ---
    public static void main(String[] args) {
        // Reduced max size for Insertion Sort to finish in a reasonable time
        int[] sizes = {1000, 10000, 25000, 50000}; 
        Random rand = new Random();
        
        System.out.println("Sorting Algorithm Performance Benchmark");
        System.out.println("=" .repeat(70));
        // HEADER: Updated to reflect InsertionSort instead of QuickSort
        System.out.printf("%-15s %-15s %-15s %-15s%n", "Array Size", "InsertionSort(ms)", "ShellSort(ms)", "MergeSort(ms)");
        System.out.println("=" .repeat(70));
        
        for (int size : sizes) {
            int[] original = new int[size];
            for (int i = 0; i < size; i++) {
                original[i] = rand.nextInt(100000);
            }
            
            // 1. InsertionSort Benchmark
            int[] arr1 = original.clone();
            long start = System.nanoTime();
            insertionSort(arr1); // Call the new insertionSort function
            double insertionTime = (System.nanoTime() - start) / 1_000_000.0;
            
            // 2. ShellSort Benchmark
            int[] arr2 = original.clone();
            start = System.nanoTime();
            shellSort(arr2);
            double shellTime = (System.nanoTime() - start) / 1_000_000.0;
            
            // 3. MergeSort Benchmark
            int[] arr3 = original.clone();
            start = System.nanoTime();
            mergeSort(arr3, 0, arr3.length - 1);
            double mergeTime = (System.nanoTime() - start) / 1_000_000.0;
            
            // OUTPUT: Updated to display InsertionSort time
            System.out.printf("%-15d %-15.2f %-15.2f %-15.2f%n", size, insertionTime, shellTime, mergeTime);
        }
        
       
    }
}