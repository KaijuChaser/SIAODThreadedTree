public class BinaryTree<V> {

   private Node<V> root = null;

   BinaryTree() {
   }

   public void add(int key, V value) {
      root = add(root, key, value);
   }

   private Node<V> add(Node current, int key, V value) {
      if (current == null) {
         return new Node<V>(key, value);
      }
      if (key > current.key) current.right = add(current.right, key, value);
      else if (key < current.key) current.left = add(current.left, key, value);
      return current;
   }

   public void preorder() {
      preorder(root);
      System.out.println();
   }

   private void preorder(Node root) {
      if (root != null) {
         System.out.print(root.value + " ");
         preorder(root.left);
         preorder(root.right);
      }
   }

   public void symm() {
      symm(root);
      System.out.println();
   }

   private void symm(Node root) {
      if (root != null) {
         symm(root.left);
         System.out.print(root.value + " ");
         symm(root.right);
      }
   }

   public void postorder() {
      postorder(root);
      System.out.println();
   }

   private void postorder(Node root) {
      if (root != null) {
         postorder(root.left);
         postorder(root.right);
         System.out.print(root.value + " ");
      }
   }

   public Node search(int key) {
      return search(root, key);
   }

   private Node search(Node current, int key) {
      if (current == null || current.key == key) return current;

      if (key > current.key)
         return search(current.right, key);
      else
         return search(current.left, key);
   }

   public void delete(int key) {

      //поиск
      Node found = root;
      Node parent = new Node<V>(Integer.MAX_VALUE,null);
      parent.left = root;
      boolean isNotFound = true;
      while (isNotFound) {
         if (found == null) return;
         if (found.key == key) isNotFound = false;
         else {
            parent = found;
            if (key > found.key)
               found = found.right;
            else
               found = found.left;
         }
      }

      //нет левого и правого
      if (found.right == null && found.left == null) {
         if (found == root) root = null;
         else {
            if (parent.key > found.key) parent.left = null;
            else parent.right = null;
         }
      // нет правого
      } else if (found.right == null) {
         if (found == root) {
            root = root.left;
            return;
         }
         if (parent.key > found.key) {
            parent.left = found.left;
         }
         else {
            parent.right = found.left;
         }
      //есть правый
      } else {
         Node current = found.right;
         while (current.left != null) current = current.left;
         if (current == found.right) {
            current.left = found.left;
            if (parent.key > found.key) parent.left = current;
            else parent.right = current;
         } else {
            delete(current.key);
            found.key = current.key;
            found.value = current.value;
         }
      }
   }

   public void log() {
      log(root, 0);
   }

   private void log(Node root, int n) {
      if (root != null) {
         log(root.right, n + 3);
         for (int i = 0; i < n; i++)
            System.out.print(" ");
         System.out.println(root.value);
         log(root.left, n + 3);
      }
   }
}

class Node<V> {

   V value;
   int key;

   Node<V> left;
   Node<V> right;

   Node(int key, V value) {
      this.key = key;
      this.value = value;
      this.left = null;
      this.right = null;
   }

   Node() {
   }
}
