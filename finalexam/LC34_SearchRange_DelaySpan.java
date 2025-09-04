import java.util.*;

public class LC34_SearchRange_DelaySpan {
    public static int[] searchRange(int[] nums, int target) {
        return new int[]{first(nums, target), last(nums, target)};
    }

    private static int first(int[] nums, int target) {
        int l = 0, r = nums.length - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] >= target) {
                if (nums[mid] == target) ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    private static int last(int[] nums, int target) {
        int l = 0, r = nums.length - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] <= target) {
                if (nums[mid] == target) ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        int[] res = searchRange(nums, target);
        System.out.println(res[0] + " " + res[1]);
    }
}
