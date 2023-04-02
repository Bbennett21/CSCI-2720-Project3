import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BinarySearchTreeDriver {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: no input file provided.");
            return;
        }
        File inputFile = new File(args[0]);
        if (!inputFile.exists()) {
            System.out.println("Error: file not found.");
            return;
        }

        Scanner inputScanner = new Scanner(System.in); // declare inputScanner outside try block
        BinarySearchTree tree = null; // declare tree outside if/else statement
        String type;
        try {
            Scanner fileScanner = new Scanner(inputFile);
            System.out.print("Enter list type (i - int, d - double, s - string): ");
            type = inputScanner.nextLine();
            if (!(type.equals("i") || type.equals("d") || type.equals("s"))) {
                System.out.println("Error: invalid type.");
                return;
            }

            if (type.equals("i")) {
                tree = new BinarySearchTree<Integer>();
                while (fileScanner.hasNextInt()) {
                    try {
                        int value = fileScanner.nextInt();
                        tree.insert(value);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: data in file does not match selected type.");
                        return;
                    }
                }
            } else if (type.equals("d")) {
                tree = new BinarySearchTree<Double>();
                while (fileScanner.hasNextDouble()) {
                    try {
                        double value = fileScanner.nextDouble();
                        tree.insert(value);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: data in file does not match selected type.");
                        return;
                    }
                }
            } else if (type.equals("s")) {
                tree = new BinarySearchTree<String>();
                while (fileScanner.hasNext()) {
                    String line = fileScanner.next();
                    tree.insert(line);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: unable to open input file.");
            return;
        }


        System.out.println("Commands:");
        System.out.println("(i) - Insert Item");
        System.out.println("(d) - Delete Item");
        System.out.println("(p) - Print Tree");
        System.out.println("(s) - Search Item");
        System.out.println("(l) - Count Leaf Nodes");
        System.out.println("(sp) - Find Single Parents");
        System.out.println("(c) - Find Cousins");
        System.out.println("(q) - Quit program");

        String command = "";
        while (!command.equals("q")) {
            System.out.print("Enter a command: ");
            command = inputScanner.nextLine();
            switch (command) {
            case "i":
                System.out.print("In-order:");
                tree.inOrder();
                System.out.println();
                System.out.print("Enter value to insert: ");
                String insertValue = inputScanner.nextLine();
                if (type.equals("i")) {
                    int intValue = Integer.parseInt(insertValue);
                    tree.insert(intValue);
                    System.out.print("In-order:");
                    tree.inOrder();
                    System.out.println();
                } else if (type.equals("d")) {
                    double doubleValue = Double.parseDouble(insertValue);
                    tree.insert(doubleValue);
                    System.out.print("In-order:");
                    tree.inOrder();
                    System.out.println();
                } else if (type.equals("s")) {
                    tree.insert(insertValue);
                    System.out.print("In-order:");
                    tree.inOrder();
                    System.out.println();
                }
                break;
            case "d":
                System.out.print("In-order:");
                tree.inOrder();
                System.out.println();
                System.out.print("Enter value to delete: ");
                String deleteValue = inputScanner.nextLine();
                if (type.equals("i")) {
                    int intValue = Integer.parseInt(deleteValue);
                    tree.delete(intValue);
                } else if (type.equals("d")) {
                    double doubleValue = Double.parseDouble(deleteValue);
                    tree.delete(doubleValue);
                } else if (type.equals("s")) {
                    tree.delete(deleteValue);
                } // if
                System.out.print("In-order:");
                tree.inOrder();
                System.out.println();
                break;
            case "p":
                System.out.print("In-order:");
                tree.inOrder();
                System.out.println();
                break;
            case "s":
                System.out.print("In-order:");
                tree.inOrder();
                System.out.println();
                System.out.print("Enter value to search: ");
                String searchValue = inputScanner.nextLine();
                boolean found = false;
                if (type.equals("i")) {
                    found = tree.search(Integer.parseInt(searchValue));
                } else if (type.equals("d")) {
                    found = tree.search(Double.parseDouble(searchValue));
                } else if (type.equals("s")) {
                    found = tree.search(searchValue);
                }
                if (found) {
                    System.out.println(searchValue + " exists in the BST.");
                } else {
                    System.out.println(searchValue + " does not exist in the BST.");
                }
                break;
            case "l":
                System.out.println("Number of leaf nodes: " + tree.getNumLeafNodes());
                break;
            case "sp":
                tree.getSingleParent();
                break;
            case "c":
                System.out.print("In-order:");
                tree.inOrder();
                System.out.println();
                System.out.print("Enter a node to find its cousins: ");
                String nodeValue = inputScanner.nextLine();
                if (type.equals("i")) {
                    tree.getCousins(Integer.parseInt(nodeValue));
                } else if (type.equals("d")) {
                    tree.getCousins(Double.parseDouble(nodeValue));
                } else if (type.equals("s")) {
                    tree.getCousins(nodeValue);
                }
                break;
            case "q":
                System.out.println("Exiting program...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command.");
            }
        }
    }
}
