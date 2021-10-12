// package arvore_twofour;

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
        for(int i = 0; i <= maxItems; i++) {
            if(itemArray[i].getKey() == key) {
                return i;
            } else {
                if (itemArray[i].getKey() > key) {
                    closest = i;
                    break;
                }
            }
        }
        if (closest == -1) {
            closest = maxItems - 1;
        }
        return closest;
    }

    public void insertEntryToNode(InsertedItem newItem) {
        for (int i=0; i < maxItems; i++) {
            if(itemArray[i].getKey() > newItem.getInsertedItem().getKey()) {
                for(int j=numItems-1; j==i; j--) {
                    itemArray[j+1] = itemArray[j];
                }
                itemArray[i] = newItem.getInsertedItem();
                insertOrderedChild(i, newItem.getLeftChild());
                insertOrderedChild(i + 1, newItem.getRightChild());
                break;
            }
        }
    }

    public int getNumItems() {
        return numItems;
    }

    public void increaseItemNum() {
        numItems++;
    }

    public void decreaseItemNum() {
        numItems--;
    }

    public TreeNode getChild(int childNum) {
        return childArray[childNum];
    }

    public void setChild(int childNum, TreeNode child) {
        childArray[childNum] = child;
    }

    public TreeNodeData getItem(int index) {
        return itemArray[index];
    }

    public TreeNodeData[] getItems() {
        return itemArray;
    }

    public void setItem(int index, TreeNodeData item) {
        itemArray[index] = item;
        increaseItemNum();
    }

    public boolean isItemNull(int index) {
        return (itemArray[index] == null);
    }

    public TreeNode getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return (getChild(0) == null);
    }

    public boolean isFull() {
        return (getNumItems() == maxItems);
    }

    public TreeNodeData[] getNodeSplitReadyArray() {
        TreeNodeData[] nodeSplitReadyArray = new TreeNodeData[maxItems + 1];
        for (int i = 0; i < maxItems; i++) {
            nodeSplitReadyArray[i] = itemArray[i];
        }
        return nodeSplitReadyArray;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public void removeItem(int index) {
        for (int i = index; i < maxItems; i++) {
            itemArray[i] = itemArray[i + 1];
        }
        itemArray[maxItems] = null;
        decreaseItemNum();
    }

    public TreeNode getLeftChildFromItem(int index) {
        return childArray[index];
    }

    public TreeNode getRightChildFromItem(int index) {
        return childArray[index + 1];
    }

    public void insertOrderedChild(int index, TreeNode child) {
        for (int i = numItems; i >= index; i--) {
            childArray[i + 1] = childArray[i];
        }
        childArray[index] = child;
    }
}

