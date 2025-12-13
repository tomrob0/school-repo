package tenone;

public class Main {
    

    static class SinglyLinkedList {
        
        private class Node {
            Object data;
            Node next;
            
            Node(Object data) {
                this.data = data;
                this.next = null;
            }
        }
        
        private Node head;
        private int size;
        
        public SinglyLinkedList() {
            this.head = null;
            this.size = 0;
        }
        
        
        public void append(Object value) {
            Node newNode = new Node(value);
            
            if (head == null) {
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            size++;
        }
        
    
        public void prepend(Object value) {
            Node newNode = new Node(value);
            newNode.next = head;
            head = newNode;
            size++;
        }
        
     
        public void insertAfter(Object target, Object value) {
            Node current = head;
            
            while (current != null) {
                if (current.data.equals(target)) {
                    Node newNode = new Node(value);
                    newNode.next = current.next;
                    current.next = newNode;
                    size++;
                    return;
                }
                current = current.next;
            }
            System.out.println("Target " + target + " not found");
        }
        
        // inserts a new node before the target node
        public void insertBefore(Object target, Object value) {
            if (head == null) {
                System.out.println("List is empty");
                return;
            }
            
            if (head.data.equals(target)) {
                prepend(value);
                return;
            }
            
            Node current = head;
            while (current.next != null) {
                if (current.next.data.equals(target)) {
                    Node newNode = new Node(value);
                    newNode.next = current.next;
                    current.next = newNode;
                    size++;
                    return;
                }
                current = current.next;
            }
            System.out.println("Target " + target + " not found");
        }
        
        // deletes the first node containing the value
        public void delete(Object value) {
            if (head == null) return;
            
            if (head.data.equals(value)) {
                head = head.next;
                size--;
                return;
            }
            
            Node current = head;
            while (current.next != null) {
                if (current.next.data.equals(value)) {
                    current.next = current.next.next;
                    size--;
                    return;
                }
                current = current.next;
            }
        }
        
        // removes the last node
        public void remove() {
            if (head == null) return;
            
            if (head.next == null) {
                head = null;
                size--;
                return;
            }
            
            Node current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
            size--;
        }
        
        // searches for a value in the list
        public boolean search(Object value) {
            Node current = head;
            while (current != null) {
                if (current.data.equals(value)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }
        
        // returns the length of the list
        public int length() {
            return size;
        }
        
        // checks if the list is empty
        public boolean isEmpty() {
            return head == null;
        }
        
 
        public void sort() {
            if (head == null || head.next == null) return;
            
            boolean swapped;
            do {
                swapped = false;
                Node current = head;
                
                while (current.next != null) {
                    // Compare current node with next node
                    if (compare(current.data, current.next.data) > 0) {
                        // Swap data
                        Object temp = current.data;
                        current.data = current.next.data;
                        current.next.data = temp;
                        swapped = true;
                    }
                    current = current.next;
                }
            } while (swapped);
        }
        
        // Helper method to compare two objects
        private int compare(Object a, Object b) {
            if (a instanceof Integer && b instanceof Integer) {
                return ((Integer) a).compareTo((Integer) b);
            } else if (a instanceof String && b instanceof String) {
                return ((String) a).compareTo((String) b);
            }
            return 0;
        }
        
        // Prints the list
        public void print() {
            if (head == null) {
                System.out.println("List is empty");
                return;
            }
            
            Node current = head;
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) {
                    System.out.print(" -> ");
                }
                current = current.next;
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        
        System.out.println("=== Singly Linked List Test Cases ===\n");
        
        // Test 1: Append values
        System.out.println("Test 1: Appending values 5, 2, 9, 1");
        list.append(5);
        list.append(2);
        list.append(9);
        list.append(1);
        System.out.print("List: ");
        list.print();
        System.out.println();
        
        // Test 2: Prepend value
        System.out.println("Test 2: Prepending value 7");
        list.prepend(7);
        System.out.print("List: ");
        list.print();
        System.out.println();
        
        // Test 3: Length
        System.out.println("Test 3: Get length");
        System.out.println("Length: " + list.length());
        System.out.println();
        
        // Test 4: Search
        System.out.println("Test 4: Search for value 9");
        System.out.println("Found 9: " + list.search(9));
        System.out.println("Found 100: " + list.search(100));
        System.out.println();
        
        // Test 5: Insert after
        System.out.println("Test 5: Insert 6 after 5");
        list.insertAfter(5, 6);
        System.out.print("List: ");
        list.print();
        System.out.println();
        
        // Test 6: Insert before
        System.out.println("Test 6: Insert 8 before 9");
        list.insertBefore(9, 8);
        System.out.print("List: ");
        list.print();
        System.out.println();
        
        // Test 7: Delete
        System.out.println("Test 7: Delete value 2");
        list.delete(2);
        System.out.print("List: ");
        list.print();
        System.out.println();
        
        // Test 8: Remove last
        System.out.println("Test 8: Remove last node");
        list.remove();
        System.out.print("List: ");
        list.print();
        System.out.println();
        
        // Test 9: Sort
        System.out.println("Test 9: Sort the list");
        list.sort();
        System.out.print("List: ");
        list.print();
        System.out.println();
        
        System.out.println("Test 10: Insert 6 after 5");
        list.insertAfter(5, 6);
        System.out.print("List: ");
        list.print();
        System.out.println();
        
    }
}