package one;



public class Main {
    

    static class Entry {
        String key;
        Object value;
        boolean deleted; 
        
        Entry(String key, Object value) {
            this.key = key; 
            this.value = value;
            this.deleted = false;
        }
    }

  
    static class HashTableOpenAddressing {
        private Entry[] table;
        private int size;
        private int capacity;
        
        public HashTableOpenAddressing(int capacity) {
            this.capacity = capacity;
            initiate();
        }
        
      
        private void initiate() {
            table = new Entry[capacity];
            this.size = 0;
        }
        
    
        private int getHashing(String key) {
            return Math.abs(key.hashCode()) % capacity;
        }
        
    
        private int probing(int index) {
            return (index + 1) % capacity;
        }
        
     
        public void add(String key, Object value) {
     
            if ((double) size / capacity >= 0.5) resize();
            
            int index = getHashing(key);
            
         
            while (table[index] != null && !table[index].deleted) {
              
                if (table[index].key.equals(key)) {
                    table[index].value = value;
                    return;
                }
                index = probing(index);
            }
            
            
            table[index] = new Entry(key, value);
            size++;
        }
        
        
        public Object get(String key) {
            int index = getHashing(key);
            int start = index;
            
           
            while (table[index] != null) {
             
                if (!table[index].deleted && table[index].key.equals(key)) {
                    return table[index].value;
                }
                
                index = probing(index);
                
                
                if (index == start) break;
            }
            return null;
        }
        
        
        public boolean remove(String key) {
            int index = getHashing(key);
            int start = index;
            
            
            while (table[index] != null) {
                
                if (!table[index].deleted && table[index].key.equals(key)) {
                   
                    table[index].deleted = true; 
                    size--;
                    return true;
                }
                
                index = probing(index);
                
                if (index == start) break;
            }
            return false;
        }
        
     
        private void resize() {
            Entry[] old = table;
            capacity *= 2;
            size = 0; 
            initiate(); 
            
            for (Entry e : old) {
                
                if (e != null && !e.deleted) {
                    add(e.key, e.value); 
                }
            }
        }
        
        
        public void display() {
            System.out.println("--- Hash Table (Linear Probing / Open Addressing) ---");
            System.out.println("Capacity: " + capacity + ", Current Size: " + size);
            for (int i = 0; i < capacity; i++) {
                System.out.print("Index " + i + ": ");
                if (table[i] == null) {
                    System.out.println("empty-since-start");
                } else if (table[i].deleted) {
                    System.out.println("empty-after-delete (TOMBSTONE)");
                } else {
                    System.out.println("[" + table[i].key + "=" + table[i].value + "]");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("Hash Table Demonstration (Linear Probing)");
        System.out.println("=".repeat(60));
        
        HashTableOpenAddressing ht = new HashTableOpenAddressing(5);
        
        System.out.println("\n*** 1. INITIAL ADDS & PROBING ***");
        ht.add("Red", 100);
        ht.add("Green", 200);
        ht.add("Blue", 300); 
        ht.display();
        
        System.out.println("\n*** 2. HASH SEARCH (GET) ***");
        System.out.println("Get Red: " + ht.get("Red"));
        System.out.println("Get Blue (Probed): " + ht.get("Blue"));
        System.out.println("Get White (Non-existent): " + ht.get("White"));
        
        System.out.println("\n*** 3. HASH REMOVE (Creating Tombstone) ***");
        System.out.println("Remove Red:");
        ht.remove("Red"); 
        ht.display();
        
        System.out.println("\n*** 4. SEARCH OVER TOMBSTONE ***");
        System.out.println("Get Blue (After Red removed): " + ht.get("Blue"));
        
        System.out.println("\n*** 5. ADD REUSING TOMBSTONE ***");
        ht.add("Yellow", 400); 
        ht.display();
        
        System.out.println("\n*** 6. RESIZE ***");
        ht.add("Orange", 500);
        ht.display();
        
    }
}