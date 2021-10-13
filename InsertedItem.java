
public class InsertedItem {
    private TreeNodeData insertedItem;
    private TreeNode leftChild;
    private TreeNode rightChild;

    //  Constructor for holding children info of
    //  inserted items (keys)
    public InsertedItem(TreeNodeData insertedItem, TreeNode leftChild, TreeNode rightChild) {
        this.insertedItem = insertedItem;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    //  Constructor for inserted item (key)
    public InsertedItem(TreeNodeData insertedItem) {
        this.insertedItem = insertedItem;
    }

    //  Getter method for inserted item (key)
    public TreeNodeData getInsertedItem() {
        return insertedItem;
    }

    //  Getter method for left child of said item (key)
    public TreeNode getLeftChild() {
        return leftChild;
    }

    //  Getter method for right child of said item (key)
    public TreeNode getRightChild() {
        return rightChild;
    }

}
