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
        // int closest = -1;
        // if (isEmpty()) {
        //     return closest;
        // }
        // for(int i = 0; i < numItems + 1; i++) {
        //     if (itemArray[i] != null) {
        //         if(itemArray[i].getKey() == key) {
        //             return i;
        //         } else {
        //             if (itemArray[i].getKey() > key) {
        //                 closest = i;
        //                 break;
        //             }
        //         }
        //     }
        // }
        // if (closest == -1) {
        //     closest = numItems + 1;
        // }
        // return closest;
        if (isEmpty()) {
            return -1;
        }
        int start = 0;
        int end = numItems -1;
        int middle = (start + end) / 2;
        System.out.println(numItems);

        while( start <= end ){
            middle = (start + end) / 2;
            if(itemArray[middle].getKey() == key)
                return middle;
            else if(key < itemArray[middle].getKey())
                end = middle - 1;
            else
                start = middle + 1;
        }
        return middle;
    }

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
        for (int i = 0; i < numItems + 1; i++) {
            nodeSplitReadyArray[i] = itemArray[i];
        }
        return nodeSplitReadyArray;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public void removeItem(int index) {
        for (int i = index; i < numItems + 1; i++) {
            itemArray[i] = itemArray[i + 1];
        }
        itemArray[numItems + 1] = null;
        decreaseItemNum();
    }

    public TreeNode getLeftChildFromItem(int index) {
        return childArray[index];
    }

    public TreeNode getRightChildFromItem(int index) {
        return childArray[index + 1];
    }

    public void insertOrderedChild(int index, TreeNode child) {
        for (int i = numItems - 1; i >= index; i--) {
            childArray[i + 1] = childArray[i];
        }
        childArray[index] = child;
    }

    public boolean isEmpty() {
        return itemArray[0] == null;
    }

    public void printItems() {
        for (int i = 0; i < numItems; i++) {
            System.out.print(itemArray[i].getKey());
            System.out.print(',');
        }
        System.out.println();
    }

}

