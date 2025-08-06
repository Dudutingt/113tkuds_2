public class SelectionSortImplementation {

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

           
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }

           
            System.out.print("第 " + (i + 1) + " 輪: ");
            printArray(arr);
        }

        System.out.println("總比較次數: " + comparisons);
        System.out.println("總交換次數: " + swaps);
    }

    
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] data = {29, 10, 14, 37, 13};
        System.out.print("原始陣列: ");
        printArray(data);

        selectionSort(data);

        System.out.print("排序後陣列: ");
        printArray(data);

      
    }
}
