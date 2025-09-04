import java.util.*;

public class LC39_CombinationSum_PPE {
    static List<List<Integer>> ans = new ArrayList<>();
    static int[] candidates;

    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        candidates = nums;
        backtrack(new ArrayList<>(), target, 0);
        return ans;
    }

    private static void backtrack(List<Integer> path, int remain, int start) {
        if (remain == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (remain < 0) return;
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            backtrack(path, remain - candidates[i], i);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        Arrays.sort(nums);
        List<List<Integer>> res = combinationSum(nums, target);
        for (List<Integer> list : res) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + (i < list.size() - 1 ? " " : ""));
            }
            System.out.println();
        }
    }
}
