import java.util.*;

public class BSTConversionAndBalance {

    static class TreeNode {
        int val; TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static TreeNode head = null, prev = null;
    public static TreeNode bstToDoublyLinkedList(TreeNode root) {
        head = null; prev = null;
        convert(root);
        return head;
    }

    private static void convert(TreeNode node) {
        if (node == null) return;
        convert(node.left);
        if (prev == null) head = node;
        else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;
        convert(node.right);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBSTHelper(nums, left, mid - 1);
        root.right = sortedArrayToBSTHelper(nums, mid + 1, right);
        return root;
    }

    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1;
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1;
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void convertBST(TreeNode root) {
        int[] sum = new int[1];
        convertHelper(root, sum);
    }

    private static void convertHelper(TreeNode node, int[] sum) {
        if (node == null) return;
        convertHelper(node.right, sum);
        sum[0] += node.val;
        node.val = sum[0];
        convertHelper(node.left, sum);
    }

    
    public static void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    public static TreeNode buildBSTFromInput(Scanner sc) {
        System.out.print("請輸入BST節點數量: ");
        int n = sc.nextInt();
        TreeNode root = null;
        System.out.println("請輸入節點值（將依序插入BST）:");
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            root = insertBST(root, val);
        }
        return root;
    }

    public static TreeNode insertBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insertBST(root.left, val);
        else root.right = insertBST(root.right, val);
        return root;
    }

    public static void printDoublyLinkedList(TreeNode head) {
        TreeNode curr = head;
        System.out.print("雙向鏈結串列節點: ");
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TreeNode root = buildBSTFromInput(sc);

        System.out.print("原BST中序列印: ");
        inorderPrint(root);
        System.out.println();

        TreeNode listHead = bstToDoublyLinkedList(root);
        printDoublyLinkedList(listHead);

        System.out.print("請輸入排序陣列長度: ");
        int m = sc.nextInt();
        System.out.println("請輸入排序陣列元素:");
        int[] sortedArray = new int[m];
        for (int i = 0; i < m; i++) sortedArray[i] = sc.nextInt();

        TreeNode balancedRoot = sortedArrayToBST(sortedArray);
        System.out.print("排序陣列轉平衡BST中序列印: ");
        inorderPrint(balancedRoot);
        System.out.println();

        System.out.println("BST是否平衡: " + (isBalanced(root) ? "是" : "否"));

        convertBST(root);
        System.out.print("BST每節點改為大於等於該節點值總和後中序列印: ");
        inorderPrint(root);
        System.out.println();

        sc.close();
    }
}
