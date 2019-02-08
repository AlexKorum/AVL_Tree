
public class Main {
    public static void main(String[] args) {
        AVL avl = new AVL();
        float[] mas = {55, 14, 0, 16, 60, 57, 70, 15, 58,100,-9,-10};
        for (float i : mas) {
            avl.add(i);
        }
        System.out.println(avl.remove(55));
        try {
            System.out.println(avl.getFerst().getE());
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        avl.visual(avl.getFerst());
    }
}
