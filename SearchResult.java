// package arvore_twofour;

public class SearchResult {
    private TreeNode finalNode;
    private TreeNodeData treeNodeData;

    public SearchResult(TreeNode finalNode, TreeNodeData searchResult) {
        this.finalNode = finalNode;
        this.treeNodeData = searchResult;
    }

    public SearchResult(TreeNode finalNode) {
        this.finalNode = finalNode;
        this.treeNodeData = null;
    }

    public TreeNode getFinalNode() {
        return finalNode;
    }

    public TreeNodeData getTreeNodeData() {
        return treeNodeData;
    }

    public boolean hasData() {
        return treeNodeData != null;
    }
}
