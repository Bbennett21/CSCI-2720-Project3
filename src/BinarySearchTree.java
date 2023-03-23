public class BinarySearchTree<T extends Comparable<T>> {
    private NodeType<T> root;

    public BinarySearchTree() {
        root = null;
    } //BinarySearchTree

    public void insert(T key) {
        if (root == null) {
            root = new NodeType<T>(null, null);
            root.setKey(key);
        } else {
            insertNode(root, key);
        } // if
    } // insert

    private void insertNode(NodeType<T> node, T key) {
        if (key.compareTo(node.getKey()) < 0) {
            if (node.getLeftChild() == null) {
                node.setLeftChild(new NodeType<T>(null, null));
                node.getLeftChild().setKey(key);
            } else {
                insertNode(node.getLeftChild(), key);
            } // if
        } else if (key.compareTo(node.getKey()) > 0) {
            if (node.getRightChild() == null) {
                node.setRightChild(new NodeType<T>(null, null));
                node.getRightChild().setKey(key);
            } else {
                insertNode(node.getRightChild(), key);
            } // if
        } // if
    } // insertNode

    public void delete(T key) {
        root = deleteNode(root, key);
    } //delete

    private NodeType<T> deleteNode(NodeType<T> node, T key) {
        if (node == null) {
            return null;
        } // if

        if (key.compareTo(node.getKey()) < 0) {
            node.setLeftChild(deleteNode(node.getLeftChild(), key));
        } else if (key.compareTo(node.getKey()) > 0) {
            node.setRightChild(deleteNode(node.getRightChild(), key));
        } else {
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                node = null;
            } else if (node.getLeftChild() == null) {
                node = node.getRightChild();
            } else if (node.getRightChild() == null) {
                node = node.getLeftChild();
            } else {
                NodeType<T> temp = findMin(node.getRightChild());
                node.setKey(temp.getKey());
                node.setRightChild(deleteNode(node.getRightChild(), temp.getKey()));
            } // if
        } // if

        return node;
    } //deleteNode

    private NodeType<T> findMin(NodeType<T> node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        } // while
        return node;
    } //findMin

    public boolean search(T item) {
        return searchNode(root, item);
    } // search

    private boolean searchNode(NodeType<T> node, T item) {
        if (node == null) {
            return false;
        } // if

        int compareResult = item.compareTo(node.getKey());
        if (compareResult == 0) {
            return true;
        } else if (compareResult < 0) {
            return searchNode(node.getLeftChild(), item);
        } else {
            return searchNode(node.getRightChild(), item);
        } // if
    } // searchNode

    public void inOrder() {
        inOrderTraversal(root);
    } // inOrder

    private void inOrderTraversal(NodeType<T> node) {
        if (node != null) {
            inOrderTraversal(node.getLeftChild());
            System.out.print(node.getKey() + " ");
            inOrderTraversal(node.getRightChild());
        } // if
    } //inOrderTraversal

    public void getSingleParent() {
        singleParentTraversal(root);
    } // getSingleParent


    private void singleParentTraversal(NodeType<T> node) {
        if (node != null) {
            if ((node.getLeftChild() == null && node.getRightChild() != null) ||
            (node.getLeftChild() != null && node.getRightChild() == null)) {
                System.out.print(node.getKey() + " ");
            } // if
            singleParentTraversal(node.getLeftChild());
            singleParentTraversal(node.getRightChild());
        } // if
    } // singleParentTraversal

    public int getNumLeafNodes() {
        return countLeafNodes(root);
    } // getNumLeafNodes


    private int countLeafNodes(NodeType<T> node) {
        if (node == null) {
            return 0;
        } else if (node.getLeftChild() == null && node.getRightChild() == null) {
            return 1;
        } else {
            return countLeafNodes(node.getLeftChild()) + countLeafNodes(node.getRightChild());
        } // if
    } // countLeafNodes

    public void getCousins(NodeType<T> node) {
        if (node == null || node == root) {
            System.out.println("No cousins found.");
            return;
        } // if

        int level = getNodeLevel(root, node, 1);
        if (level == 1) {
            System.out.println("No cousins found.");
            return;
        } // if

        printCousins(root, node, level);
    } // getCousins

    private int getNodeLevel(NodeType<T> node, NodeType<T> target, int level) {
        if (node == null) {
            return 0;
        } // if

        if (node == target) {
            return level;
        } // if

        int leftLevel = getNodeLevel(node.getLeftChild(), target, level+1);
        if (leftLevel != 0) {
            return leftLevel;
        } // if

        return getNodeLevel(node.getRightChild(), target, level+1);
    } // getNodeLevel

    private void printCousins(NodeType<T> node, NodeType<T> target, int level) {
        if (node == null || level < 2) {
            return;
        } // if

        if (level == 2) {
            if (node.getLeftChild() != null && node.getLeftChild() != target && node.getRightChild() != null && node.getRightChild() != target) {
                System.out.print(node.getLeftChild().getKey() + " " + node.getRightChild().getKey() + " ");
            } else if (node.getLeftChild() != null && node.getLeftChild() != target) {
                System.out.print(node.getLeftChild().getKey() + " ");
            } else if (node.getRightChild() != null && node.getRightChild() != target) {
                System.out.print(node.getRightChild().getKey() + " ");
            } // if
        } else if (level > 2) {
            printCousins(node.getLeftChild(), target, level-1);
            printCousins(node.getRightChild(), target, level-1);
        } // if
    } // printCousins


} // BinarySearchTree
