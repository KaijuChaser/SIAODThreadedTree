public class Main {

    public static void main(String[] args) {

        /*BinaryTree<Character> tree = new BinaryTree<Character>();

        tree.add(50, 'a');
        tree.add(30, 'b');
        tree.add(80, 'c');
        tree.add(20, 'd');
        tree.add(70, 'e');
        tree.add(90, 'f');
        tree.add(40, 'g');

        tree.preorder();
        tree.symm();
        tree.postorder();

        tree.log();
        tree.delete(50);
        tree.log();*/

       BinaryTree<Character> t = new BinaryTree<Character>();

       t.add(50, 'a');
       t.add(30, 'b');
       t.add(80, 'c');
       t.add(20, 'd');
       t.add(70, 'e');
       t.add(90, 'f');
       t.add(40, 'g');

       t.symm();
       t.log();

       t.delete(50);

       t.symm();
       t.log();
    }
}
