public class Node {
    private Node parent;
    private Node left;
    private Node right;
    private int ID;
    private int sizeChild=0;

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSizeChild() {
        return sizeChild;
    }

    public void setSizeChild(int sizeChild) {
        this.sizeChild = sizeChild;
    }
}