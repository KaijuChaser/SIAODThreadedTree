class ThreadedTree<V> {

   TNode<V> root;

   ThreadedTree() {
   }

   public void add(int key, V value) {
      root = add(root, key, value);
   }

   private TNode<V> add(TNode<V> curr, int key, V value) {
      if (curr == null) return new TNode<V>(key, value, null, null, false, false);
      if (key > curr.key) {
         if (curr.ltag)
            curr.right = add(curr.right, key, value);
         else {
            curr = new TNode<V>(key, value, curr.left, new TNode<V>(key, value, curr, curr.right, false, false), curr.ltag, true);
         }
      } else if (key < curr.key) {
         if (curr.rtag)
            curr.left = add(curr.left, key, value);
         else {
            curr = new TNode<V>(key, value, new TNode<V>(key, value, curr.left, curr, false, false), curr.right, true, false);
         }
      }
      return curr;
   }

   public void delete(int key) {

      //поиск
      TNode<V> found = root;

      TNode<V> parent = new TNode<V>(Integer.MAX_VALUE,null);
      parent.left = root;

      boolean isNotFound = true;
      while (isNotFound) {
         if (found == null) return;
         if (found.key == key) isNotFound = false;
         else {
            parent = found;
            if (key > found.key)
               found = found.getRight();
            else
               found = found.getLeft();
         }
      }

      //нет левого и правого
      if (found.right == null && found.left == null) {
         if (found == root) root = null;
         else {
            if (parent.key > found.key) {
               parent.left = parent.left.left;
               parent.ltag = false;
            } else {
               parent.right = parent.right.right;
               parent.rtag = false;
            }
         }
         // нет правого
      } else if (found.right == null) {
         TNode<V> current = found;
         while (current.getLeft()!= null) current = current.left;
         while (current.right!=found) current = current.right;
         current.right = found.right;
         if (parent.key > found.key) parent.left = found.left;
         else parent.right = found.left;
         //есть правый
      } else {
         TNode<V> current = found.right;
         while (current.getLeft() != null) current = current.left;
         if (current == found.right) {
            current.left = found.left;
            current.ltag = true;
            if (current.left!=null && !current.left.rtag) current.left = current;
            if (parent.key > found.key) parent.left = current;
            else parent.right = current;
         } else {
            delete(current.key);
            found.key = current.key;
            found.value = current.value;
         }
      }
   }

   public void symm() {
      symm(root);
      System.out.println();
   }

   private void symm(TNode<V> root) {
      TNode<V> current = root;
      while (current.getLeft()!=null) current = current.left;
      while (current!=null) {
         System.out.print(current.value);
         if (!current.rtag) current = current.right;
         else {
            current = current.right;
            while (current.getLeft()!=null) current = current.left;
         }
      }
   }

   public void log() {
      log(root, 0);
   }

   private void log(TNode<V> root, int n) {
      if (root != null) {
         log(root.getRight(), n + 3);
         for (int i = 0; i < n; i++)
            System.out.print(" ");
         System.out.println(root.value);
         log(root.getLeft(), n + 3);
      }
   }
}

class TNode<V> {
   int key;
   V value;

   boolean ltag;
   boolean rtag;

   TNode<V> left;
   TNode<V> right;

   TNode(int key, V value) {
      this.key = key;
      this.value = value;
   }

   TNode(int key, V value, TNode<V> left, TNode<V> right, boolean ltag, boolean rtag) {
      this.key = key;
      this.value = value;
      this.left = left;
      this.right = right;
      this.ltag = ltag;
      this.rtag = rtag;
   }

   public TNode<V> getLeft() {
      return ltag ? left : null;
   }

   public TNode<V> getRight() {
      return rtag ? right : null;
   }
}