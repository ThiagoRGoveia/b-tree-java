// package arvore_twofour;

public class TwoFourTree {
    private TreeNode rootNode;

    // Constructor
    public TwoFourTree()
    {
        rootNode = null;
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
        return keySearch(key, rootNode);
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
    public void insert(InsertedItem data)
    {
        SearchResult searchResult = keySearch(data.getInsertedItem().getKey());
        if (searchResult.hasData()) {
            System.out.println("Key already exists");
        } else {
            insert(data, searchResult.getFinalNode());
        }
    }

    public void insert(InsertedItem data, TreeNode node) {
        if (!node.isFull()) {
            node.insertEntryToNode(data);
        } else if (node.isLeaf()) {
            handleLeafNodeOverflow(data, node);
        } else {
            handleRootNodeOverflow(data);
        }
    }

    public void handleLeafNodeOverflow(InsertedItem data, TreeNode node) {
        TreeNode newNode = split(node, data);
        TreeNodeData promotedItem = promoteItem(newNode);
        insert(
            new InsertedItem(promotedItem, node, newNode),
            node.getParent()
        );
    }

    public void handleRootNodeOverflow(InsertedItem data) {
        TreeNode newRootNode = new TreeNode();
        TreeNode node = rootNode;
        rootNode.setParent(newRootNode);
        TreeNode newNode = split(node, data);
        TreeNodeData promotedItem = promoteItem(newNode);
        insert(
            new InsertedItem(promotedItem, node, newNode),
            newRootNode
        );
    }

    private TreeNode split(TreeNode node, InsertedItem data) {
        TreeNode newNode = new TreeNode();
        newNode.setParent(newNode.getParent());
        rearrangeNodeItems(node, newNode, data.getInsertedItem());
        return newNode;
    }

    private TreeNodeData promoteItem(TreeNode node) {
        TreeNodeData promotedItem = node.getItem(0);
        node.removeItem(0);
        return promotedItem;
    }

    private void rearrangeNodeItems(TreeNode node, TreeNode newNode, TreeNodeData data) {
        TreeNodeData[] splitArray =  node.getNodeSplitReadyArray();
        for( int i = 0; i < TreeNode.maxItems; i++) {
            if (splitArray[i].getKey() > data.getKey()) {
                for (int j = TreeNode.maxItems - 1; j == i; j--) {
                    splitArray[j + 1] = splitArray[j];
                }
                splitArray[i] = data;
                break;
            }
        }
        for (int i = 0; i  < splitArray.length / 2; i++) {
            node.setItem(i, splitArray[i]);
        }
        for (int i = splitArray.length / 2; i < splitArray.length; i++) {
            newNode.setItem(i - splitArray.length / 2, splitArray[i]);
        }
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