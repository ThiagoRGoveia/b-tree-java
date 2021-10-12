package arvore_twofour;

import com.sun.source.tree.Tree;

public class TwoFourTree {
    private TreeNode root;
 
    // Constructor 
    public TwoFourTree() 
    {
        root = null;
    }

    public TreeNodeData search(int key) {
        SearchResult searchResult = keySearch(key);
        if (searchResult.hasData()) {
            return  searchResult.getTreeNodeData();
        } else {
            return  null;
        }
    }
 
    public SearchResult keySearch(int key) {
        return keySearch(key, root);
    }

    public SearchResult keySearch(int key, TreeNode node){
        int prospectKeyIndex = node.keySearch(key);
        TreeNodeData prospectItem = node.getItem(prospectKeyIndex);
        if(prospectItem.getKey() == key) {
            return new SearchResult(node, prospectItem);
        } else {
            TreeNode currentNode = node.getChild(prospectKeyIndex);
            if (currentNode != null) {
                return keySearch(key, currentNode);
            }
            return new SearchResult(node);
        }
    }


    // Methods to insert data
    public void insert(TreeNodeData data)
    {
        SearchResult searchResult = keySearch(data.getKey());
        if (searchResult.hasData()) {
            System.out.println("Key already exists");
        } else {
            insert(data, searchResult.getFinalNode());
        }
    }

    public void insert(TreeNodeData data, TreeNode node) {
        if (!node.isFull()) {
            node.insertEntryToNode(data);
        } else if (node.isLeaf()) {
            handleLeafNodeOverflow(data, node);
        } else {
            handleRootNodeOverflow(data);
        }
    }
    
    public void handleLeafNodeOverflow(TreeNodeData data, TreeNode node) {

    }
    
    public void handleRootNodeOverflow(TreeNodeData data) {
        
    }



    private void split(TreeNode node) {
        TreeNode newNode = new TreeNode();

    }

    private void rearrangeNodeItems(TreeNode node, TreeNodeData newEntry) {
        TreeNodeData[] splitArray =  node.getNodeSplitReadyArray();
        for( int i = 0; i < TreeNode.maxItems; i++) {
            if (splitArray[i].getKey() > newEntry.getKey()) {
                for (int j = TreeNode.maxItems - 1; j == i; j--) {
                    splitArray[j + 1] = splitArray[j];
                }
                splitArray[i] = newEntry;
                break;
            }
        }

        for (int i = 0; i  < splitArray.length / 2; i++) {
            node.a
        }
    }

    private void insertKeyToSortedArray(TreeNodeData node, int key){
        }

}
/*
    // Method to insert data recursively
//    private TreeNode insert(TreeNode node, int data)
//    {
//        return node;
//    }

    // Method to check if tree is empty
    public boolean isEmpty()
    {
        return root == null;
    }


    // Methods to delete data
    public void delete(int k)
    {
//        if (isEmpty())
//            System.out.println("Tree Empty");
//        else if (search(k) == false)
//            System.out.println("Sorry " + k + " is not present");
//        else
//        {
//            root = delete(root, k);
//            System.out.println(k + " deleted from the tree");
//        }
    }

    private TreeNode delete(TreeNode root, int key)
    {
//        return root;
    }

 
    // Methods to search for an element
//    public boolean search(int val) {
//        return search(root, val);
//    }
//
//    // Method to search for an element recursively
//    private boolean search(TreeNode r, int val) {
//        return true;
//    }
 
    // Method for inorder traversal
//    public void inorder()
//    {
//        inorder(root);
//    }
//
//    private void inorder(TreeNode r)
//    {
//        if (r != null)
//        {
//            inorder(r.getLeft());
//            System.out.print(r.getData() + " ");
//            inorder(r.getRight());
//        }
//    }
*/