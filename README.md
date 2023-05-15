# CSCI-2720-Project3: Binary Search Tree

This project is a Java implementation of a binary search tree, created by Bryson Bennett and Michael Li for the CSCI 2720 Data Structures course at the University of Georgia.

## Overview

A binary search tree is a type of data structure where each node in the tree has at most two children, and the left child is always less than the parent node, while the right child is greater than the parent node. This makes binary search trees useful for searching and sorting data efficiently.

The implementation of the binary search tree in this project includes three main classes:

- `NodeType`: a class defining the basic structure of a node in the binary search tree, including its value and references to its left and right children.

- `BinarySearchTree`: a class that implements the binary search tree, including methods to insert, search for, and delete nodes.

- `BinarySearchTreeDriver`: a class that tests the implementation of the binary search tree.

## Contributors

- Bryson Bennett (blb64218@uga.edu)
- Michael Li (ml10674@uga.edu)

## Usage

To compile the project, follow these steps:

1. Compile `NodeType.java`: `javac -d bin NodeType.java`
2. Compile `BinarySearchTree.java`: `javac -d bin -cp bin BinarySearchTree.java`
3. Compile `BinarySearchTreeDriver.java`: `javac -d bin -cp bin BinarySearchTreeDriver.java`

To run the project, enter the command `java -cp bin BinarySearchTreeDriver` in the terminal.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more information.
