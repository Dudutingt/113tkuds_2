import java.util.*;

public class M01_BuildHeap {
    static String type; 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        type = sc.next();   
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        buildHeap(arr, n);

       
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1) System.out.print(" ");
        }
        System.out.println();
    }

    
    static void buildHeap(int[] arr, int n) {
       
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, n, i);
        }
    }

   
    static void heapifyDown(int[] arr, int n, int i) {
        int extreme = i; 
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (type.equals("max")) {
            if (left < n && arr[left] > arr[extreme]) {
                extreme = left;
            }
            if (right < n && arr[right] > arr[extreme]) {
                extreme = right;
            }
        } else { 
            if (left < n && arr[left] < arr[extreme]) {
                extreme = left;
            }
            if (right < n && arr[right] < arr[extreme]) {
                extreme = right;
            }
        }

        if (extreme != i) {
            int tmp = arr[i];
            arr[i] = arr[extreme];
            arr[extreme] = tmp;
            heapifyDown(arr, n, extreme);
        }
    }
}

/*
【時間複雜度分析】
- buildHeap：從 n/2 個非葉節點開始，每個節點 heapifyDown 的成本與其高度成正比。
  總成本 = O(n) （比單純 insert n 次的 O(n log n) 更快）
- heapifyDown：最壞 O(log n)，但總和起來仍是 O(n)。
=> 整體時間複雜度：O(n)
【空間複雜度分析】
- 僅使用 O(1) 額外空間（遞迴深度 O(log n)，可視為忽略）
=> 整體空間複雜度：O(1)
*/
