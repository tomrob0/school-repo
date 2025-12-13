package twelvethree;

public class Main {
    
    static class ArrayBasedList {
        private Object[] array;
        private int size;
        private int capacity;
        
        public ArrayBasedList() {
            capacity = 10;
            array = new Object[capacity];
            size = 0;
        }
        
        public ArrayBasedList(int initialCapacity) {
            capacity = initialCapacity;
            array = new Object[capacity];
            size = 0;
        }
        
        private void resize() {
            capacity *= 2;
            Object[] newArray = new Object[capacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        
        public void append(Object value) {
            if (size == capacity) {
                resize();
            }
            array[size++] = value;
        }
        
        public void prepend(Object value) {
            if (size == capacity) {
                resize();
            }
            for (int i = size; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = value;
            size++;
        }
        
        public void insertAt(int index, Object value) {
            if (index < 0 || index > size) {
                System.out.println("Invalid index");
                return;
            }
            if (size == capacity) {
                resize();
            }
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = value;
            size++;
        }
        
        public Object removeAt(int index) {
            if (index < 0 || index >= size) {
                System.out.println("Invalid index");
                return null;
            }
            Object removed = array[index];
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            array[--size] = null;
            return removed;
        }
        
        public int search(Object value) {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(value)) {
                    return i;
                }
            }
            return -1;
        }
        
        public void sort(boolean ascending) {
            for (int i = 0; i < size - 1; i++) {
                for (int j = 0; j < size - i - 1; j++) {
                    boolean shouldSwap = false;
                    
                    if (array[j] instanceof Integer && array[j + 1] instanceof Integer) {
                        int a = (Integer) array[j];
                        int b = (Integer) array[j + 1];
                        shouldSwap = ascending ? (a > b) : (a < b);
                    } else if (array[j] instanceof String && array[j + 1] instanceof String) {
                        String a = (String) array[j];
                        String b = (String) array[j + 1];
                        shouldSwap = ascending ? (a.compareTo(b) > 0) : (a.compareTo(b) < 0);
                    }
                    
                    if (shouldSwap) {
                        Object temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }
        
        public int size() {
            return size;
        }
        
        public boolean isEmpty() {
            return size == 0;
        }
        
        public void print() {
            if (size == 0) {
                System.out.println("List is empty");
                return;
            }
            System.out.print("[");
            for (int i = 0; i < size; i++) {
                System.out.print(array[i]);
                if (i < size - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Array-Based List Test ===\n");
        
        ArrayBasedList list = new ArrayBasedList();
        
        System.out.println("APPEND:");
        list.append(5);
        list.append(10);
        list.append(15);
        list.print();
        
        System.out.println("\nPREPEND:");
        list.prepend(1);
        list.print();
        
        System.out.println("\nINSERT AT index 2:");
        list.insertAt(2, 7);
        list.print();
        
        System.out.println("\nSEARCH for 10:");
        int index = list.search(10);
        System.out.println("Found at index: " + index);
        
        System.out.println("\nREMOVE AT index 1:");
        Object removed = list.removeAt(1);
        System.out.println("Removed: " + removed);
        list.print();
        
        System.out.println("\nSORT Ascending:");
        list.sort(true);
        list.print();
        
        System.out.println("\nSORT Assending");
        list.sort(true);
        list.print();
        
        System.out.println("\n=== String List Test ===\n");
        
        ArrayBasedList strList = new ArrayBasedList();
        strList.append("Dog");
        strList.append("Cat");
        strList.append("Bird");
        strList.prepend("Ant");
        
        System.out.println("Original:");
        strList.print();
        
        System.out.println("\nSort Ascending:");
        strList.sort(true);
        strList.print();
        
        System.out.println("\nSort Descending:");
        strList.sort(false);
        strList.print();
    }
}