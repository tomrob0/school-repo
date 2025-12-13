package tenone;
public class Main {
    

    static class MyDoublyLinkedList {
        
       
        protected class Node {
            Object data;
            Node prev;
            Node next;
            
            Node(Object data) {
                this.data = data;
                this.prev = null;
                this.next = null;
            }
        }
        
        protected Node head;
        protected Node tail;
        protected int size;
        
       
        public MyDoublyLinkedList() {
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
        
       
        public Object removeLast() {
            if (tail == null) return null;
            
            Object data = tail.data;
            if (head == tail) {
                head = tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }
            size--;
            return data;
        }
        
       
        public Object removeFirst() {
            if (head == null) return null;
            
            Object data = head.data;
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            size--;
            return data;
        }
        
       
        public Object peekFirst() {
            return head != null ? head.data : null;
        }
        
     
        public Object peekLast() {
            return tail != null ? tail.data : null;
        }
        
      
        public boolean isEmpty() {
            return size == 0;
        }
       
        public int size() {
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
    }
    
   
    interface Stack {
        void push(Object value);
        Object pop();
        Object peek();
    }
    
   
    interface Queue {
        void enqueue(Object value);
        Object dequeue();
        Object front();
    }
    
  
    static class MyStack extends MyDoublyLinkedList implements Stack {
        
      
        public void push(Object value) {
            append(value);
        }
        
        
        public Object pop() {
            if (isEmpty()) {
                System.out.println("Stack is empty!");
                return null;
            }
            return removeLast();
        }
        
    
        public Object peek() {
            if (isEmpty()) {
                System.out.println("Stack is empty!");
                return null;
            }
            return peekLast();
        }
        
        
        public void printStack() {
            System.out.print("Stack (bottom to top): ");
            print();
        }
    }
    
    
    static class MyQueue extends MyDoublyLinkedList implements Queue {
        
        
        public void enqueue(Object value) {
            append(value);
        }
        
      
        public Object dequeue() {
            if (isEmpty()) {
                System.out.println("Queue is empty!");
                return null;
            }
            return removeFirst();
        }
        
        
        public Object front() {
            if (isEmpty()) {
                System.out.println("Queue is empty!");
                return null;
            }
            return peekFirst();
        }
        
       
        public void printQueue() {
            System.out.print("Queue (front to rear): ");
            print();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("Stack and Queue Implementation using Doubly Linked List");
        System.out.println("=".repeat(60));
        
       
       
        
        MyStack stack = new MyStack();
        
      
        System.out.println("Test 1: Push elements 10, 20, 30, 40, 50");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.printStack();
        System.out.println("Stack size: " + stack.size());
        System.out.println();
        
        
        System.out.println("Test 2: Peek at top element");
        System.out.println("Top element: " + stack.peek());
        stack.printStack();
        System.out.println();
        
  
        System.out.println("Test 3: Pop 2 elements");
        System.out.println("Popped: " + stack.pop());
        System.out.println("Popped: " + stack.pop());
        stack.printStack();
        System.out.println("Stack size: " + stack.size());
        System.out.println();
        
       
        System.out.println("Test 4: Push 60 and 70");
        stack.push(60);
        stack.push(70);
        stack.printStack();
        System.out.println();
        
       
        System.out.println("Test 5: Pop all elements");
        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }
        stack.printStack();
        System.out.println();
        
        
    
        
        MyQueue queue = new MyQueue();
        
     
        System.out.println("Test 6: Enqueue elements A, B, C, D, E");
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");
        queue.printQueue();
        System.out.println("Queue size: " + queue.size());
        System.out.println();
        
      
        System.out.println("Test 7: Peek at front element");
        System.out.println("Front element: " + queue.front());
        queue.printQueue();
        System.out.println();
        
        
        System.out.println("Test 8: Dequeue 2 elements");
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());
        queue.printQueue();
        System.out.println("Queue size: " + queue.size());
        System.out.println();
        
      
        System.out.println("Test 9: Enqueue F and G");
        queue.enqueue("F");
        queue.enqueue("G");
        queue.printQueue();
        System.out.println();
        
      
            System.out.println("Test 10: Peek at front element");
            System.out.println("Front element: " + queue.front());
            queue.printQueue();
            System.out.println();
            
            
        
           
        MyStack stack2 = new MyStack();
        MyQueue queue2 = new MyQueue();
        
        System.out.println("Adding 1, 2, 3, 4, 5 to both Stack and Queue");
        for (int i = 1; i <= 5; i++) {
            stack2.push(i);
            queue2.enqueue(i);
        }
        
        System.out.println("Stack");
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop() + " ");
        }
        
        System.out.println("Queue ");
        while (!queue2.isEmpty()) {
            System.out.print(queue2.dequeue() + " ");
        }
        
       }
}