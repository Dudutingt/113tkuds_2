public class ValidMaxHeapChecker {
    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < n && arr[i] < arr[left]) {
                System.out.println("違反節點索引: " + left);
                return false;
            }
            if (right < n && arr[i] < arr[right]) {
                System.out.println("違反節點索引: " + right);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] tests = {
            {100, 90, 80, 70, 60, 75, 65},
            {100, 90, 80, 95, 60, 75, 65},
            {50},
            {}
        };
        for (int[] arr : tests) {
            System.out.println(isValidMaxHeap(arr));
        }
    }
}
