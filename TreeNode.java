// package arvore_twofour;

// Class that defines a tree node
class TreeNode {
    private int numItems;
    public static final int maxChildren = 4;
    public static final int maxItems = maxChildren - 1;
    private TreeNode parent;
    private final TreeNode[] childArray = new TreeNode[maxChildren];
    //ArrayList<TreeNodeData> childArray = new ArrayList<TreeNodeData>();
    private final TreeNodeData[] itemArray = new TreeNodeData[maxItems];
    //ArrayList<TreeNodeData> itemArray = new ArrayList<TreeNodeData>();

    // Returns the entry that is equal or the closest to the key being searched
    public int keySearch(int key) {
        int closest = -1;
        if (isEmpty()) {
            return closest;
        }
        for(int i = 0; i < numItems + 1; i++) {
            if (itemArray[i] != null) {
                if(itemArray[i].getKey() == key) {
                    return i;
                } else {
                    if (itemArray[i].getKey() > key) {
                        closest = i;
                        break;
                    }
                }
            }
        }
        if (closest == -1) {
            closest = numItems - 1;
        }
        return closest;
    }

    //  Method for insertion of key entries to said node
    public void insertEntryToNode(InsertedItem newItem) {
        for (int i = 0; i < numItems + 1; i++) {
            if (itemArray[i] == null) {
                itemArray[i] = newItem.getInsertedItem();
                increaseItemNum();
                insertOrderedChild(i, newItem.getLeftChild());
                insertOrderedChild(i + 1, newItem.getRightChild());
                break;
            } else if(itemArray[i].getKey() > newItem.getInsertedItem().getKey()) {
                for(int j = numItems - 1; j==i; j--) {
                    itemArray[j+1] = itemArray[j];
                }
                itemArray[i] = newItem.getInsertedItem();
                insertOrderedChild(i, newItem.getLeftChild());
                insertOrderedChild(i + 1, newItem.getRightChild());
                increaseItemNum();
                break;
            }
        }
        printItems();
    }

    //  Getter method for numItems
    public int getNumItems() {
        return numItems;
    }

    //  Setter method for numItems
    public void increaseItemNum() {
        numItems++;
    }

    //  Method to decrease numItems
    public void decreaseItemNum() {
        numItems--;
    }

    //  Getter method for childNum
    public TreeNode getChild(int childNum) {
        return childArray[childNum];
    }

    //  Setter method for childNum (as a child)
    public void setChild(int childNum, TreeNode child) {
        childArray[childNum] = child;
    }

    //  Getter method for the index of an item (key)
    //  in the itemArray
    public TreeNodeData getItem(int index) {
        return itemArray[index];
    }

    //  Getter method for the itemArray itself
    public TreeNodeData[] getItems() {
        return itemArray;
    }

    //  Setter method for the index as the position of
    //  said item and increases the number of items in
    //  a node
    public void setItem(int index, TreeNodeData item) {
        itemArray[index] = item;
        increaseItemNum();
    }

    //  Checks if item (key) is null
    public boolean isItemNull(int index) {
        return (itemArray[index] == null);
    }

    //  Getter method for parent node
    public TreeNode getParent() {
        return parent;
    }

    //  Method to check is said node is a leaf node
    public boolean isLeaf() {
        return (getChild(0) == null);
    }

    // Method to check if said node is full (max capacity)
    public boolean isFull() {
        return (getNumItems() == maxItems);
    }

    //  Method to up the number of said array to be split by one
    public TreeNodeData[] getNodeSplitReadyArray() {
        TreeNodeData[] nodeSplitReadyArray = new TreeNodeData[maxItems + 1];
        for (int i = 0; i < numItems + 1; i++) {
            nodeSplitReadyArray[i] = itemArray[i];
        }
        return nodeSplitReadyArray;
    }

    //  Setter method for parent node
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    //  Method for removing item (key) in said index and
    //  decreases numItems in array by one
    public void removeItem(int index) {
        for (int i = index; i < numItems + 1; i++) {
            itemArray[i] = itemArray[i + 1];
        }
        itemArray[numItems + 1] = null;
        decreaseItemNum();
    }

    //  Getter method for left child array from item (key)
    public TreeNode getLeftChildFromItem(int index) {
        return childArray[index];
    }

    //  Getter method for right child array from item (key)
    public TreeNode getRightChildFromItem(int index) {
        return childArray[index + 1];
    }

    //  Method for positioning child to left of
    //  promoted node, rightfully guaranteeing it's
    //  node position in respect to parent
    public void insertOrderedChild(int index, TreeNode child) {
        for (int i = numItems; i >= index; i--) {
            childArray[i + 1] = childArray[i];
        }
        childArray[index] = child;
    }

    // Checks if first position in node is null
    public boolean isEmpty() {
        return itemArray[0] == null;
    }

    public void printItems() {
        for (int i = 0; i < numItems; i++) {
            System.out.print(itemArray[i].getKey());
            System.out.print(',');
        }
    }

}

