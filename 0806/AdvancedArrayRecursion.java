public class AdvancedArrayRecursion {

   
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivotIndex = partition(arr, left, right);
        quickSort(arr, left, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        int temp = arr[i + 1]; arr[i + 1] = arr[right]; arr[right] = temp;
        return i + 1;
    }

    public static int[] mergeSortedArrays(int[] a, int[] b) {
        return mergeHelper(a, 0, b, 0);
    }

    private static int[] mergeHelper(int[] a, int i, int[] b, int j) {
        if (i == a.length) {
            int[] rest = new int[b.length - j];
            System.arraycopy(b, j, rest, 0, b.length - j);
            return rest;
        }
        if (j == b.length) {
            int[] rest = new int[a.length - i];
            System.arraycopy(a, i, rest, 0, a.length - i);
            return rest;
        }
        if (a[i] <= b[j]) {
            int[] merged = mergeHelper(a, i + 1, b, j);
            int[] result = new int[merged.length + 1];
            result[0] = a[i];
            System.arraycopy(merged, 0, result, 1, merged.length);
            return result;
        } else {
            int[] merged = mergeHelper(a, i, b, j + 1);
            int[] result = new int[merged.length + 1];
            result[0] = b[j];
            System.arraycopy(merged, 0, result, 1, merged.length);
            return result;
        }
    }

    public static int kthSmallest(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1);
        return arr[k - 1];
    }

   
    public static boolean subsetSum(int[] arr, int target) {
        return subsetSumHelper(arr, 0, target);
    }

    private static boolean subsetSumHelper(int[] arr, int index, int target) {
        if (target == 0) return true;
        if (index == arr.length || target < 0) return false;
        
        return subsetSumHelper(arr, index + 1, target - arr[index]) || subsetSumHelper(arr, index + 1, target);
    }

    
    public static void main(String[] args) {
        int[] arr1 = {5, 3, 8, 4, 2};
        quickSort(arr1, 0, arr1.length - 1);
        System.out.print("QuickSort: ");
        for (int n : arr1) System.out.print(n + " ");
        System.out.println();

        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6};
        int[] merged = mergeSortedArrays(a, b);
        System.out.print("Merged: ");
        for (int n : merged) System.out.print(n + " ");
        System.out.println();

        int[] arr2 = {7, 10, 4, 3, 20, 15};
        System.out.println("3rd smallest: " + kthSmallest(arr2, 3)); // 7

        int[] arr3 = {3, 34, 4, 12, 5, 2};
        System.out.println("Subset sum 9? " + subsetSum(arr3, 9)); // true (4+5)
        System.out.println("Subset sum 30? " + subsetSum(arr3, 30)); // false
    }
}
