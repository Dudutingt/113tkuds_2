import java.io.*;
import java.util.*;

public class M09_AVLValidate {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { this.val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      
        String line;
        while ((line = br.readLine()) != null && line.trim().isEmpty()) {}
        if (line == null) return;
        int n = Integer.parseInt(line.trim());

        int[] arr = new int[n];
        int filled = 0;
        while (filled < n && (line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens() && filled < n) {
                arr[filled++] = Integer.parseInt(st.nextToken());
            }
        }

        Node root = buildTree(arr);

        boolean isBST = isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        if (!isBST) {
            System.out.println("Invalid BST");
            return;
        }

        boolean isAVL = isAVL(root);
        if (!isAVL) {
            System.out.println("Invalid AVL");
            return;
        }

        System.out.println("Valid");
    }

    private static Node buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < arr.length && !q.isEmpty()) {
            Node cur = q.poll();
          
            if (i < arr.length && arr[i] != -1) {
                cur.left = new Node(arr[i]);
                q.offer(cur.left);
            }
            i++;
            
            if (i < arr.length && arr[i] != -1) {
                cur.right = new Node(arr[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    private static boolean isBST(Node node, long min, long max) {
        if (node == null) return true;
        if (!(min < node.val && node.val < max)) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    
    }

    private static boolean isAVL(Node root) {
        return heightOrNegOne(root) != -1;
    }

    private static int heightOrNegOne(Node node) {
        if (node == null) return 0;
        int lh = heightOrNegOne(node.left);
        if (lh == -1) return -1;
        int rh = heightOrNegOne(node.right);
        if (rh == -1) return -1;
        if (Math.abs(lh - rh) > 1) return -1;
        return Math.max(lh, rh) + 1;
    }
}
/*
 * Time Complexity: O(n)
 * 說明：建樹走訪 n 個位置；檢查 BST 以上下界遞迴，每節點訪問一次；
 *       檢查 AVL 以後序計算高度與平衡因子，每節點訪問一次。總計 O(n)，空間 O(n)（佇列與遞迴堆疊）。
 */