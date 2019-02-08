
public class Main {
    public static void main(String[] args) {
        AVL avl = new AVL();
        int[] mas = {55, 14, 0, 16, 60, 57, 70, 15, 58};
        for (int i : mas) {
            avl.add(i);
        }
        try {
            System.out.println(avl.remove(14));
            System.out.println(avl.getFerst().getE());
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        avl.visual(avl.getFerst());
    }
}
