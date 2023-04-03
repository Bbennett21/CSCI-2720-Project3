public class BinarySearchTree<T extends Comparable<T>> {
    private NodeType<T> root;

    public BinarySearchTree() {
        root = null;
    } //BinarySearchTree

    //inserts value
    public void insert(T key) {
        if (root == null) {
            root = new NodeType<T>(key);
        } else {
            insertNode(root, key);
        } // if
    } // insert

    //uses value to insert node
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

    // delete value
    public void delete(T key) {
        root = deleteNode(root, key);
    } //delete

    // uses value to delete node
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


    // finds the min value
    private NodeType<T> findMin(NodeType<T> node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        } // while
        return node;
    } //findMin

    //searches for value
    public boolean search(T item) {
        return searchNode(root, item);
    } // search


    //uses value to search for node
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

    // gives list in order
    public void inOrder() {
        inOrderTraversal(root);
    } // inOrder

    //travers and prints list
    private void inOrderTraversal(NodeType<T> node) {
        if (node != null) {
            inOrderTraversal(node.getLeftChild());
            System.out.print(node.getInfo() + " ");
            inOrderTraversal(node.getRightChild());
        } // if
    } //inOrderTraversal

    //gets single parent
    public void getSingleParent() {
        singleParentTraversal(root);
    } // getSingleParent


    //traverses to find single parent
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

    //get number of leaf nodes
    public int getNumLeafNodes() {
        return countLeafNodes(root);
    } // getNumLeafNodes


    //recursivly counts leaf nodes
    private int countLeafNodes(NodeType<T> node) {
        if (node == null) {
            return 0;
        } else if (node.getLeftChild() == null && node.getRightChild() == null) {
            return 1;
        } else {
            return countLeafNodes(node.getLeftChild()) + countLeafNodes(node.getRightChild());
        } // if
    } // countLeafNodes

    //gets cousins
    public void getCousins(T value) {
        if (root == null || value == null) {
            return;
        }
        if (root.getInfo().equals(value)) {
            return;
        }
        int level = getLevel(root,value,1);
        if (level == -1) {
            return;
        }
        NodeType<T> parent = getParent(root, value);
        if (parent == null) {
            return;
        }
        System.out.println(value + " cousins: ");
        printCousins(root, level, parent);
    }

    //finds the level of a node
    private int getLevel(NodeType<T> node, T value, int level) {
        if (node == null) {
            return -1;
        }
        if (node.getInfo().equals(value)) {
            return level;
        }
        int leftLevel = getLevel(node.getLeftChild(), value, level + 1);
        if (leftLevel == -1) {
            return getLevel(node.getRightChild(), value, level + 1);
        }
        return leftLevel;
    }

    //finds a nodes parent
    private NodeType<T> getParent(NodeType<T> node, T value) {
    if (node == null || node.getInfo().equals(value)) {
        return null;
    }
    if ((node.getLeftChild() != null && node.getLeftChild().getInfo().equals(value)) ||
        (node.getRightChild() != null && node.getRightChild().getInfo().equals(value))) {
        return node;
    }
    NodeType<T> leftParent = getParent(node.getLeftChild(), value);
    if (leftParent != null) {
        return leftParent;
    }
    return getParent(node.getRightChild(), value);
}

    //prints the nodes cousins
    private void printCousins(NodeType<T> node, int level, NodeType<T> parent) {
    if (node == null || level <= 2) {
        return;
    }

    if ( getParent(root, node.getInfo()) != null && getParent(root, node.getInfo()) != parent) {
        if(getLevel(root, node.getInfo(),1) == level) {
            System.out.print(node.getInfo() + " ");
        }
    }

    printCousins(node.getLeftChild(), level, parent);
    printCousins(node.getRightChild(), level, parent);

}


} // BinarySearchTree
