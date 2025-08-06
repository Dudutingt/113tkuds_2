import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val; TreeNode left, right;
        TreeNode(int v) { val = v; }
    }
    public static TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inorderIndex.put(inorder[i], i);
        return helperPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inorderIndex);
    }

    private static TreeNode helperPreIn(int[] pre, int preStart, int preEnd,
                                       int[] in, int inStart, int inEnd,
                                       Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) return null;
        int rootVal = pre[preStart];
        TreeNode root = new TreeNode(rootVal);
        int inRootIdx = inMap.get(rootVal);
        int leftSize = inRootIdx - inStart;
        root.left = helperPreIn(pre, preStart + 1, preStart + leftSize, in, inStart, inRootIdx - 1, inMap);
        root.right = helperPreIn(pre, preStart + leftSize + 1, preEnd, in, inRootIdx + 1, inEnd, inMap);
        return root;
    }

    // 根據後序與中序重建二元樹
    public static TreeNode buildTreePostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inorderIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inorderIndex.put(inorder[i], i);
        return helperPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inorderIndex);
    }

    private static TreeNode helperPostIn(int[] post, int postStart, int postEnd,
                                        int[] in, int inStart, int inEnd,
                                        Map<Integer, Integer> inMap) {
        if (postStart > postEnd || inStart > inEnd) return null;
        int rootVal = post[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int inRootIdx = inMap.get(rootVal);
        int leftSize = inRootIdx - inStart;
        root.left = helperPostIn(post, postStart, postStart + leftSize - 1, in, inStart, inRootIdx - 1, inMap);
        root.right = helperPostIn(post, postStart + leftSize, postEnd - 1, in, inRootIdx + 1, inEnd, inMap);
        return root;
    }

    // 根據層序重建完全二元樹（使用層序陣列）
    public static TreeNode buildCompleteTreeLevelOrder(int[] levelOrder) {
        if (levelOrder.length == 0) return null;
        List<TreeNode> nodes = new ArrayList<>();
        for (int v : levelOrder) {
            nodes.add(new TreeNode(v));
        }
        for (int i = 0; i < levelOrder.length; i++) {
            int leftIdx = 2 * i + 1;
            int rightIdx = 2 * i + 2;
            if (leftIdx < levelOrder.length) nodes.get(i).left = nodes.get(leftIdx);
            if (rightIdx < levelOrder.length) nodes.get(i).right = nodes.get(rightIdx);
        }
        return nodes.get(0);
    }

    public static boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    public static void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入前序節點數量: ");
        int n = sc.nextInt();
        System.out.println("請輸入前序陣列:");
        int[] preorder = new int[n];
        for (int i = 0; i < n; i++) preorder[i] = sc.nextInt();

        System.out.println("請輸入中序陣列:");
        int[] inorder = new int[n];
        for (int i = 0; i < n; i++) inorder[i] = sc.nextInt();

        TreeNode rootPreIn = buildTreePreIn(preorder, inorder);
        System.out.print("重建樹(前序+中序)中序列印: ");
        inorderPrint(rootPreIn);
        System.out.println();

        System.out.println("請輸入後序陣列:");
        int[] postorder = new int[n];
        for (int i = 0; i < n; i++) postorder[i] = sc.nextInt();

        TreeNode rootPostIn = buildTreePostIn(postorder, inorder);
        System.out.print("重建樹(後序+中序)中序列印: ");
        inorderPrint(rootPostIn);
        System.out.println();

        System.out.print("請輸入完全二元樹層序節點數量: ");
        int m = sc.nextInt();
        System.out.println("請輸入層序陣列:");
        int[] levelOrder = new int[m];
        for (int i = 0; i < m; i++) levelOrder[i] = sc.nextInt();

        TreeNode rootLevel = buildCompleteTreeLevelOrder(levelOrder);
        System.out.print("重建完全二元樹中序列印: ");
        inorderPrint(rootLevel);
        System.out.println();

        boolean sameTree = isSameTree(rootPreIn, rootPostIn);
        System.out.println("前序+中序與後序+中序重建的樹是否相同? " + (sameTree ? "是" : "否"));

        sc.close();
    }
}
