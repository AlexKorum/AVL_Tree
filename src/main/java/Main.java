public class Main {
    public static void main(String[] args) {
        AVL avl = new AVL();
        int[] mas = {55, 14, 0, 16, 60, 57, 70, 15, 58, 100, -9, -10, 101, 102, 103};
        for (int i : mas) {
            avl.add(i);
        }
        avl.visual(avl.getFerst());
        System.out.println();
        avl.remove(70);
        avl.visual(avl.getFerst());

    }
}
