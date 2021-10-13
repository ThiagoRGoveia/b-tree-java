// package arvore_twofour;

public class TwoFourTree {
    private TreeNode rootNode;

    // Constructor
    public TwoFourTree()
    {
        rootNode = new TreeNode();
    }

    //  Method to traverse the two-four tree
    public TreeNodeData search(int key) {
        SearchResult searchResult = keySearch(key);
        if (searchResult.hasData()) {
            return  searchResult.getTreeNodeData();
        } else {
            return  null;
        }
    }

    //  Recursive method to search keys inside a node
    public SearchResult keySearch(int key) {
        return keySearch(key, rootNode);
    }

    // Method that returns the search result in a node
    public SearchResult keySearch(int key, TreeNode node){
        int prospectKeyIndex = node.keySearch(key);
        System.out.println("prospectKeyIndex: " + prospectKeyIndex);
        if (prospectKeyIndex < 0) {
            return new SearchResult(rootNode);
        }
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


    //  Recursive method to insert data
    public void insert(InsertedItem data)
    {
        SearchResult searchResult = keySearch(data.getInsertedItem().getKey());
        if (searchResult.hasData()) {
            System.out.println("Key already exists");
        } else {
            insert(data, searchResult.getFinalNode());
        }
    }

    //  Method for insertion which checks cases and
    //  calls handling methods when the node overflows
    public void insert(InsertedItem data, TreeNode node) {
        if (!node.isFull()) {
            node.insertEntryToNode(data);
            System.out.println("Item inserted");
        } else if (node.isLeaf()) {
            handleLeafNodeOverflow(data, node);
        } else {
            handleRootNodeOverflow(data);
        }
    }

    //
    public void insert(TreeNodeData newItem) {
        insert(new InsertedItem(newItem));
    }

    //  Method that handles the leaf node overflow
    //  by calling split method and backtracks needed
    //  insertions
    public void handleLeafNodeOverflow(InsertedItem data, TreeNode node) {
        TreeNode newNode = split(node, data);
        TreeNodeData promotedItem = promoteItem(newNode);
        insert(
            new InsertedItem(promotedItem, node, newNode),
            node.getParent()
        );
    }

    //  Method that handles the tree root node
    //  overflow by splitting, setting new parent
    //  and promoting said item to parent node
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

    //  Method splits, sets new node as parent if needed
    //  and rearranges the child nodes
    private TreeNode split(TreeNode node, InsertedItem data) {
        TreeNode newNode = new TreeNode();
        newNode.setParent(newNode.getParent());
        rearrangeNodeItems(node, newNode, data.getInsertedItem());
        return newNode;
    }

    //  Method that promotes the middle key into the parent
    //  node and removes it from the previous child node from
    //  which it came
    private TreeNodeData promoteItem(TreeNode node) {
        TreeNodeData promotedItem = node.getItem(0);
        node.removeItem(0);
        return promotedItem;
    }

    //  Method for rearranging items through splitting
    //  the node in two, creating a new node for the
    //  rightmost key
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
