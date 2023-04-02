public class BinarySearchTree<T extends Comparable<T>> {
    private NodeType<T> root;

    public BinarySearchTree() {
        root = null;
    } //BinarySearchTree

    public void insert(T key) {
        if (root == null) {
            root = new NodeType<T>(key);
        } else {
            insertNode(root, key);
        } // if
    } // insert

    private void insertNode(NodeType<T> node, T key) {
        int compareResult = key.compareTo(node.getInfo());

        if (compareResult == 0) {
            System.out.println("The item already exists in the tree.");
            return;
        } // if
        if (compareResult < 0) {
            if (node.getLeftChild() == null) {
                node.setLeftChild(new NodeType<T>(key));
            } else {
                insertNode(node.getLeftChild(), key);
            } // if
        } else if (compareResult > 0) {
            if (node.getRightChild() == null) {
                node.setRightChild(new NodeType<T>(key));
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
            System.out.println("The item is not present in the tree");
            return null;
        } // if

        if (key.compareTo(node.getInfo()) < 0) {
            node.setLeftChild(deleteNode(node.getLeftChild(), key));
        } else if (key.compareTo(node.getInfo()) > 0) {
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
                node.setInfo(temp.getInfo());
                node.setRightChild(deleteNode(node.getRightChild(), temp.getInfo()));
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

        int compareResult = item.compareTo(node.getInfo());
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
            System.out.print(node.getInfo() + " ");
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
                System.out.println("Single Parents: " + node.getInfo() + " ");
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

    public void getCousins(T value) {
        NodeType<T> node = getNodeWithValue(root, value);
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

    private NodeType<T> getNodeWithValue(NodeType<T> node, T value) {
        if (node == null) {
            return null;
        } // if

        if (node.getInfo().equals(value)) {
            return node;
        } // if

        NodeType<T> leftNode = getNodeWithValue(node.getLeftChild(), value);
        if (leftNode != null) {
            return leftNode;
        } // if

        return getNodeWithValue(node.getRightChild(), value);
    } // getNodeWithValue



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
                System.out.print(node.getLeftChild().getInfo() + " " + node.getRightChild().getInfo() + " ");
            } else if (node.getLeftChild() != null && node.getLeftChild() != target) {
                System.out.print(node.getLeftChild().getInfo() + " ");
            } else if (node.getRightChild() != null && node.getRightChild() != target) {
                System.out.print(node.getRightChild().getInfo() + " ");
            } // if
        } else if (level > 2) {
            printCousins(node.getLeftChild(), target, level-1);
            printCousins(node.getRightChild(), target, level-1);
        } // if
    } // printCousins

} // BinarySearchTree
