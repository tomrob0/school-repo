package tenone;
public class Main {
    
 
    static class DoublyLinkedList {
        
    
        private class Node {
            Object data;
            Node prev;
            Node next;
            
            Node(Object data) {
                this.data = data;
                this.prev = null;
                this.next = null;
            }
        }
        
        private Node head;
        private Node tail;
        private int size;
        
      
        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }
        
   
        public void append(Object value) {
            Node newNode = new Node(value);
            
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            size++;
        }
        
     
        public void prepend(Object value) {
            Node newNode = new Node(value);
            
            if (head == null) {
                head = tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
            size++;
        }
        
     
        public void insertAfter(Object target, Object value) {
            Node current = head;
            
            while (current != null) {
                if (current.data.equals(target)) {
                    Node newNode = new Node(value);
                    newNode.next = current.next;
                    newNode.prev = current;
                    
                    if (current.next != null) {
                        current.next.prev = newNode;
                    } else {
                        tail = newNode;
                    }
                    current.next = newNode;
                    size++;
                    return;
                }
                current = current.next;
            }
            System.out.println("Target " + target + " not found");
        }
        
   
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
            while (current != null) {
                if (current.data.equals(target)) {
                    Node newNode = new Node(value);
                    newNode.next = current;
                    newNode.prev = current.prev;
                    
                    if (current.prev != null) {
                        current.prev.next = newNode;
                    }
                    current.prev = newNode;
                    size++;
                    return;
                }
                current = current.next;
            }
            System.out.println("Target " + target + " not found");
        }
        

        public void removeAfter(Object target) {
            Node current = head;
            
            while (current != null) {
                if (current.data.equals(target)) {
                    if (current.next == null) {
                        System.out.println("No node after " + target);
                        return;
                    }
                    
                    Node toRemove = current.next;
                    current.next = toRemove.next;
                    
                    if (toRemove.next != null) {
                        toRemove.next.prev = current;
                    } else {
                        tail = current;
                    }
                    size--;
                    return;
                }
                current = current.next;
            }
            System.out.println("Target " + target + " not found");
        }
        
      
        public void removeBefore(Object target) {
            Node current = head;
            
            while (current != null) {
                if (current.data.equals(target)) {
                    if (current.prev == null) {
                        System.out.println("No node before " + target);
                        return;
                    }
                    
                    Node toRemove = current.prev;
                    if (toRemove.prev != null) {
                        toRemove.prev.next = current;
                        current.prev = toRemove.prev;
                    } else {
                        head = current;
                        current.prev = null;
                    }
                    size--;
                    return;
                }
                current = current.next;
            }
            System.out.println("Target " + target + " not found");
        }
        
    
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
        
  
        public void sort() {
            if (head == null || head.next == null) return;
            
            Node current = head.next;
            
            while (current != null) {
                Node nextNode = current.next;
                Object key = current.data;
                Node position = current.prev;
                
    
                while (position != null && compare(position.data, key) > 0) {
                    position = position.prev;
                }
                
          
                if (current.prev != null) {
                    current.prev.next = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                
             
                if (position == null) {
                    current.next = head;
                    current.prev = null;
                    head.prev = current;
                    head = current;
                } else {
                    current.next = position.next;
                    current.prev = position;
                    if (position.next != null) {
                        position.next.prev = current;
                    } else {
                        tail = current;
                    }
                    position.next = current;
                }
                
                current = nextNode;
            }
        }
        
    
        private int compare(Object a, Object b) {
            if (a instanceof Integer && b instanceof Integer) {
                return ((Integer) a).compareTo((Integer) b);
            } else if (a instanceof String && b instanceof String) {
                return ((String) a).compareTo((String) b);
            }
            return 0;
        }
        
   
        public int length() {
            return size;
        }
        
      
        public void print() {
            if (head == null) {
                System.out.println("List is empty");
                return;
            }
            
            Node current = head;
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) {
                    System.out.print(" <-> ");
                }
                current = current.next;
            }
            System.out.println();
        }
        
       
        public void printReverse() {
            if (tail == null) {
                System.out.println("List is empty");
                return;
            }
            
            Node current = tail;
            while (current != null) {
                System.out.print(current.data);
                if (current.prev != null) {
                    System.out.print(" <-> ");
                }
                current = current.prev;
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        
        System.out.println("=== Doubly Linked List Test Cases ===\n");
        
        // Test 1: Append values
        System.out.println("Test 1: Appending values 15, 8, 23, 4, 12");
        list.append(15);
        list.append(8);
        list.append(23);
        list.append(4);
        list.append(12);
        System.out.print("List (Forward): ");
        list.print();
        System.out.print("List (Backward): ");
        list.printReverse();
        System.out.println();
        
        // Test 2: Prepend value
        System.out.println("Test 2: Prepending value 30");
        list.prepend(30);
        System.out.print("List: ");
        list.print();
        System.out.println();
        
        // Test 3: Insert after
        System.out.println("Test 3: Insert 20 after 15");
        list.insertAfter(15, 20);
        System.out.print("List: ");
        list.print();
        System.out.println();
        
        // Test 4: Insert before
        System.out.println("Test 4: Insert 18 before 23");
        list.insertBefore(23, 18);
        System.out.print("List: ");
        list.print();
        System.out.println();
        
        // Test 5: Search
        System.out.println("Test 5: Search for values");
        System.out.println("Found 23: " + list.search(23));
        System.out.println("Found 100: " + list.search(100));
        System.out.println();
        
        // Test 6: Remove after
        System.out.println("Test 6: Remove node after 15");
        list.removeAfter(15);
        System.out.print("List: ");
        list.print();
        System.out.println();
        
        // Test 7: Remove before
        System.out.println("Test 7: Remove node before 23");
        list.removeBefore(23);
        System.out.print("List: ");
        list.print();
        System.out.println();
        
        // Test 8: Length
        System.out.println("Test 8: Get list length");
        System.out.println("Length: " + list.length());
        System.out.println();
        
        // Test 9: Sort using Insertion Sort
        System.out.println("Test 9: Sort the list using Insertion Sort");
        System.out.print("Before sort: ");
        list.print();
        list.sort();
        System.out.print("After sort: ");
        list.print();
        System.out.print("After sort (Backward): ");
        list.printReverse();
        System.out.println();
        
        // Test 10: Additional operations
        System.out.println("Test 10: Add more values and demonstrate bidirectional traversal");
        list.append(25);
        list.prepend(2);
        System.out.print("List (Forward): ");
        list.print();
        System.out.print("List (Backward): ");
        list.printReverse();
        System.out.println();
        
        System.out.println("=== All Tests Completed ===");
    }
}