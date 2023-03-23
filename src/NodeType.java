public class NodeType{

    private NodeType leftChild;
    private NodeType rightChild;

    public NodeType(NodeType leftChild, NodeType rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    } // NodeType

    public NodeType getLeftChild() {
        return leftChild;
    } // getLeftChild

    public void setLeftChild(NodeType leftChild) {
        this.leftChild = leftChild;
    } // setLeftChild

    public NodeType getRightChild() {
        return rightChild;
    } // getRightChild

    public void setRightChild(NodeType rightChild) {
        this.rightChild = rightChild;
    } // setRightChild
} // NodeType
