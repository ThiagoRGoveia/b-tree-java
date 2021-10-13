// package arvore_twofour;

public class TwoFourTree {
    private TreeNode rootNode;

    // Constructor
    public TwoFourTree()
    {
        rootNode = new TreeNode();
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
        // System.out.println("prospectKeyIndex " + prospectKeyIndex);
        if (prospectKeyIndex < 0) {
            return new SearchResult(rootNode);
        }
        // node.printItems();
        // System.out.println("key found " + node.getItem(prospectKeyIndex).getKey());
        TreeNodeData prospectItem = node.getItem(prospectKeyIndex);
        if(prospectItem.getKey() == key) {
            return new SearchResult(node, prospectItem);
        } else {
            TreeNode currentNode;
            if (prospectItem.getKey() > key) {
                currentNode = node.getChild(prospectKeyIndex);
            } else {
                currentNode = node.getChild(prospectKeyIndex + 1);
            }
            if (currentNode != null) {
                return keySearch(key, currentNode);
            }
            // node.printItems();
            return new SearchResult(node);
        }

    }


    // Methods to insert data
    public void insert(InsertedItem data)
    {
        System.out.println("inserting " + data.getInsertedItem().getKey());
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
            System.out.println("Item inserted");
            node.printItems();
        } else if (node.isRoot()) {
            handleRootNodeOverflow(data);
        } else {
            handleLeafNodeOverflow(data, node);
        }
    }

    public void insert(TreeNodeData newItem) {
        insert(new InsertedItem(newItem));
    }

    public void handleLeafNodeOverflow(InsertedItem data, TreeNode node) {
        TreeNode newNode = split(node, data);
        newNode.setParent(node.getParent());
        TreeNodeData promotedItem = promoteItem(newNode);
        System.out.println("Promoted item " + promotedItem.getKey());
        insert(
            new InsertedItem(promotedItem, node, newNode),
            node.getParent()
        );
    }

    public void handleRootNodeOverflow(InsertedItem data) {
        TreeNode newRootNode = new TreeNode();
        TreeNode node = rootNode;
        rootNode = newRootNode;
        node.setParent(newRootNode);
        TreeNode newNode = split(node, data);
        // System.out.println("Print node");
        // node.printItems();
        // System.out.println("Print newnode");
        // newNode.printItems();
        TreeNodeData promotedItem = promoteItem(newNode);
        System.out.println("Promoted item " + promotedItem.getKey());
        insert(
            new InsertedItem(promotedItem, node, newNode),
            newRootNode
        );
    }

    private TreeNode split(TreeNode node, InsertedItem data) {
        TreeNode newNode = new TreeNode();
        newNode.setParent(node.getParent());
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
        insertSortedItemToArray(splitArray, data);
        System.out.println("Split array");
        System.out.println("-----------");
        for (int i = 0; i < splitArray.length; i++) {
            System.out.print(splitArray[i].getKey());
            System.out.print(",");
        }
        System.out.println();
        System.out.println("-----------");

        for (int i = 0; i  < splitArray.length / 2; i++) {
            node.setItem(i, splitArray[i]);
        }
        for (int i = splitArray.length / 2; i < splitArray.length; i++) {
            newNode.setItem(i - splitArray.length / 2, splitArray[i]);
        }
    }

    private void insertSortedItemToArray (TreeNodeData[] array, TreeNodeData item) {
        // printTreeNodeDataArray(array);
        int i = 0;
        while (i < array.length &&  array[i] != null && array[i].getKey() < item.getKey()) {
            i++;
        }
        for (int j = array.length - 1; j > i; j--) {
            array[j] = array[j - 1];
        }
        array[i] = item;
    }

    // print TreeNodeDataArray

    public void printTreeNodeDataArray(TreeNodeData[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                System.out.print(array[i].getKey());
                System.out.print(",");
            }
        }
        System.out.println();
    }


    public void traverseInOrder() {
        traverseInOrder(rootNode);
    }

    public void traverseInOrder(TreeNode node) {
        if (node != null) {
            for (int i = 0; i < node.getNumChildren(); i++) {
                traverseInOrder(node.getChild(i));
            }
            node.printItems();
        }
    }

}
/*
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