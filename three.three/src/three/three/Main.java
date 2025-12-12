package three.three;

public class Main {
    public static int[] MatrixSearch(int[][] arr, int key) {
        int rows = arr.length;
        int cols = arr[0].length;

        
        for (int i = 0; i < rows; i++) {
            
            int left = 0;
            int right = cols - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (arr[i][mid] == key) {
                    return new int[]{i, mid};
                } else if (arr[i][mid] < key) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[][] arr = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        System.out.println("arr[1][0] = " + arr[1][0]);

        int[] result = MatrixSearch(arr, 5);
        System.out.println("MatrixSearch(arr, 5) = [" + result[0] + "," + result[1] + "]"); 

        result = MatrixSearch(arr, 11);
        System.out.println("MatrixSearch(arr, 11) = [" + result[0] + "," + result[1] + "]"); 

           }
}

