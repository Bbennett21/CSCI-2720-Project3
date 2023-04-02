public class NodeType<T extends Comparable<T>> {
    private T info;
    private NodeType<T> leftChild;
    private NodeType<T> rightChild;

    public NodeType(T info) {
        this.info = info;
        leftChild = null;
        rightChild = null;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NodeType<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(NodeType<T> leftChild) {
        this.leftChild = leftChild;
    }

    public NodeType<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(NodeType<T> rightChild) {
        this.rightChild = rightChild;
    }
}
