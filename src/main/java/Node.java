public class Node {
    private Node parent;
    private Node left;
    private Node right;
    private float e;

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

    public float getE() {
        return e;
    }

    public void setE(float e) {
        this.e = e;
    }
}