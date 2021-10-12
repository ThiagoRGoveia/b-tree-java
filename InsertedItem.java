
public class InsertedItem {
    private TreeNodeData insertedItem;
    private TreeNode leftChild;
    private TreeNode rightChild;

    public InsertedItem(TreeNodeData insertedItem, TreeNode leftChild, TreeNode rightChild) {
        this.insertedItem = insertedItem;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public TreeNodeData getInsertedItem() {
        return insertedItem;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

}
