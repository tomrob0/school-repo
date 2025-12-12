package three.two;

public class Main {

    /**
     * Function 1: Check if a string has all unique characters.
     * @param str The input string.
     * @return true if all characters are unique, false otherwise.
     */
    public static boolean unique(String str) {
        // Convert string to lowercase to make comparison case-insensitive
        str = str.toLowerCase();
        
        // Loop through each character in the string
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            // Check if this character appears again in the rest of the string
            if (str.indexOf(c, i + 1) != -1) {
                return false; // Duplicate found
            }
        }
        return true; // All characters are unique
    }

    /**
     * Function 2: Count occurrences of a word in a string.
     * @param str The input string.
     * @param word The word to search for.
     * @return Number of occurrences of the word in the string.
     */
    public static int occurrence(String str, String word) {
        int count = 0;
        str = str.toLowerCase();
        word = word.toLowerCase();

        int index = str.indexOf(word);
        while (index != -1) {
            count++;
            // Move past this occurrence
            index = str.indexOf(word, index + word.length());
        }
        return count;
    }

    public static void main(String[] args) {
        // Test Function 1
        System.out.println("unique(\"stack\") → " + unique("stack")); // true
        System.out.println("unique(\"queue\") → " + unique("queue")); // false

        // Test Function 2
        String text = "In the beginning God created the heavens and the earth.";
        System.out.println("Occurrence of \"the\" → " + occurrence(text, "the")); // 3
        System.out.println("Occurrence of \"begin\" → " + occurrence(text, "begin")); // 1
        System.out.println("Occurrence of \"recreate\" → " + occurrence(text, "recreate")); // 0
    }
}


