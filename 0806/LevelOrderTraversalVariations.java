import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {
        int val; TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static TreeNode buildTreeFromInput(Scanner sc) {
        System.out.print("請輸入節點數量(含空節點 -1): ");
        int n = sc.nextInt();
        if (n == 0) return null;
        List<TreeNode> nodes = new ArrayList<>();
        System.out.println("請依層序輸入節點值，空節點請輸入 -1:");
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            nodes.add(val == -1 ? null : new TreeNode(val));
        }
        int childIndex = 1;
        for (int i = 0; i < n && childIndex < n; i++) {
            TreeNode curr = nodes.get(i);
            if (curr != null) {
                if (childIndex < n) curr.left = nodes.get(childIndex++);
                if (childIndex < n) curr.right = nodes.get(childIndex++);
            }
        }
        return nodes.get(0);
    }

    
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                level.add(curr.val);
                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
            }
            res.add(level);
        }
        return res;
    }

   
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;
        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (leftToRight) level.addLast(curr.val);
                else level.addFirst(curr.val);
                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
            }
            res.add(level);
            leftToRight = !leftToRight;
        }
        return res;
    }

    public static List<Integer> lastNodeEachLevel(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            int lastVal = 0;
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                lastVal = curr.val;
                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
            }
            res.add(lastVal);
        }
        return res;
    }

    
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Map<Integer, List<Integer>> columnTable = new TreeMap<>();
        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
        q.offer(new Pair<>(root, 0));

        while (!q.isEmpty()) {
            Pair<TreeNode, Integer> p = q.poll();
            TreeNode node = p.getKey();
            int col = p.getValue();
            columnTable.putIfAbsent(col, new ArrayList<>());
            columnTable.get(col).add(node.val);

            if (node.left != null) q.offer(new Pair<>(node.left, col - 1));
            if (node.right != null) q.offer(new Pair<>(node.right, col + 1));
        }

        for (List<Integer> colList : columnTable.values()) {
            res.add(colList);
        }
        return res;
    }

    
    static class Pair<K,V> {
        private K key;
        private V value;
        public Pair(K k, V v) { key = k; value = v; }
        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TreeNode root = buildTreeFromInput(sc);

        System.out.println("每層節點分別儲存:");
        List<List<Integer>> levels = levelOrder(root);
        for (int i = 0; i < levels.size(); i++) {
            System.out.println("第 " + (i+1) + " 層: " + levels.get(i));
        }

        System.out.println("\n之字形層序走訪:");
        List<List<Integer>> zigzag = zigzagLevelOrder(root);
        for (int i = 0; i < zigzag.size(); i++) {
            System.out.println("第 " + (i+1) + " 層: " + zigzag.get(i));
        }

        System.out.println("\n每層最後一個節點:");
        List<Integer> lastNodes = lastNodeEachLevel(root);
        System.out.println(lastNodes);

        System.out.println("\n垂直層序走訪:");
        List<List<Integer>> vertical = verticalOrder(root);
        for (int i = 0; i < vertical.size(); i++) {
            System.out.println("列 " + i + ": " + vertical.get(i));
        }

        sc.close();
    }
}
