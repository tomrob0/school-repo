package fiveteen;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    
 
    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}


class SearchTree {
    private TreeNode root;
    public SearchTree() {
        this.root = null;
    }
    
   
    public void insert(int value) {
        root = insertRecursive(root, value);
    }
    
    
    private TreeNode insertRecursive(TreeNode node, int value) {
     
        if (node == null) {
            return new TreeNode(value);
        }
        
     
        if (value == node.value) {
            return node;
        }
        
     
        if (value < node.value) {
            node.left = insertRecursive(node.left, value);
        } else {
            node.right = insertRecursive(node.right, value);
        }
        
        return node;
    }
    
   
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }
    
   
    private TreeNode deleteRecursive(TreeNode node, int value) {
    
        if (node == null) {
            return null;
        }
        
     
        if (value < node.value) {
            node.left = deleteRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = deleteRecursive(node.right, value);
        } else {
           
            
           
            if (node.left == null && node.right == null) {
                return null;
            }
            
        
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            
          
            TreeNode successor = findMin(node.right);
            node.value = successor.value;
            node.right = deleteRecursive(node.right, successor.value);
        }
        
        return node;
    }
    
   
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
 
    public boolean search(int value) {
        return searchRecursive(root, value);
    }
    
  
    private boolean searchRecursive(TreeNode node, int value) {
       
        if (node == null) {
            return false;
        }
        
       
        if (node.value == value) {
            return true;
        }
        
        
        if (value < node.value) {
            return searchRecursive(node.left, value);
        } else {
            return searchRecursive(node.right, value);
        }
    }
    
   
    public void inorderTraversal() {
        inorderRecursive(root);
        System.out.println();
    }
    
  
    private void inorderRecursive(TreeNode node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.value + " ");
            inorderRecursive(node.right);
        }
    }
    
  
    public void reverseOrderTraversal() {
        reverseOrderRecursive(root);
        System.out.println();
    }
    
   
    private void reverseOrderRecursive(TreeNode node) {
        if (node != null) {
            reverseOrderRecursive(node.right);
            System.out.print(node.value + " ");
            reverseOrderRecursive(node.left);
        }
    }
    
  
    public void preorderTraversal() {
        preorderRecursive(root);
        System.out.println();
    }
    
  
    private void preorderRecursive(TreeNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorderRecursive(node.left);
            preorderRecursive(node.right);
        }
    }
    
   
    public void postorderTraversal() {
        postorderRecursive(root);
        System.out.println();
    }
    
   
    private void postorderRecursive(TreeNode node) {
        if (node != null) {
            postorderRecursive(node.left);
            postorderRecursive(node.right);
            System.out.print(node.value + " ");
        }
    }
    
    
    public int getHeight() {
        return getHeightRecursive(root);
    }
    
   
    private int getHeightRecursive(TreeNode node) {
       
        if (node == null) {
            return -1;
        }
        
     
        int leftHeight = getHeightRecursive(node.left);
        int rightHeight = getHeightRecursive(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
}


public class Main {
    public static void main(String[] args) {
        System.out.println("=== Binary Search Tree Demo ===\n");
        
        // Create a new SearchTree
        SearchTree bst = new SearchTree();
        
        // Insert sample values
        System.out.println("Step 1: Inserting values into the tree");
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
        
        System.out.print("Inserting: ");
        for (int value : values) {
            System.out.print(value + " ");
            bst.insert(value);
        }
        System.out.println("\n");
        
        // Print all traversals
        System.out.println("Step 2: Display all traversals");
        System.out.println("-----------------------------------");
        
        System.out.print("Inorder Traversal (Ascending):    ");
        bst.inorderTraversal();
        
        System.out.print("Reverse Order (Descending):       ");
        bst.reverseOrderTraversal();
        
        System.out.print("Preorder Traversal:               ");
        bst.preorderTraversal();
        
        System.out.print("Postorder Traversal:              ");
        bst.postorderTraversal();
        
        System.out.println();
        
        // Perform search tests
        System.out.println("Step 3: Search operations");
        System.out.println("-----------------------------------");
        
        int searchValue1 = 40;
        int searchValue2 = 100;
        
        System.out.println("Searching for " + searchValue1 + ": " + 
                         (bst.search(searchValue1) ? "FOUND ✓" : "NOT FOUND ✗"));
        
        System.out.println("Searching for " + searchValue2 + ": " + 
                         (bst.search(searchValue2) ? "FOUND ✓" : "NOT FOUND ✗"));
        
        System.out.println();
        
        // Get initial height
        System.out.println("Step 4: Tree height before deletion");
       
        System.out.println("Height of the tree: " + bst.getHeight());
        System.out.println();
        
        // Delete nodes
        System.out.println("Step 5: Delete operations");
        
        
        // Delete a leaf node
        int leafToDelete = 10;
        System.out.println("Deleting leaf node: " + leafToDelete);
        bst.delete(leafToDelete);
        
        // Delete a node with two children
        int nodeWithTwoChildren = 30;
        System.out.println("Deleting node with two children: " + nodeWithTwoChildren);
        bst.delete(nodeWithTwoChildren);
        
        System.out.println();
        
        // Print updated traversals
        System.out.println("Step 6: Display traversals after deletion");
    
        System.out.print("Inorder Traversal (Ascending):    ");
        bst.inorderTraversal();
        System.out.print("Reverse Order (Descending):       ");
        bst.reverseOrderTraversal();
        System.out.print("Preorder Traversal:               ");
        bst.preorderTraversal();
        System.out.print("Postorder Traversal:              ");
        bst.postorderTraversal();
        
        System.out.println();
        
        // Get final height
        System.out.println("Step 7: Tree height after deletion");
        System.out.println("Height of the tree: " + bst.getHeight());
        
    }
}