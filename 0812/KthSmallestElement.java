import java.util.PriorityQueue;

public class KthSmallestElement {
    
    public static int kthSmallest_MaxHeap(int[] arr, int k) {
        if (k < 1 || k > arr.length) throw new IllegalArgumentException();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b - a);
        for (int v : arr) {
            maxHeap.offer(v);
            if (maxHeap.size() > k) maxHeap.poll();
        }
        return maxHeap.peek();
    }

    public static int kthSmallest_MinHeap(int[] arr, int k) {
        if (k < 1 || k > arr.length) throw new IllegalArgumentException();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int v : arr) minHeap.offer(v);
        int res = -1;
        for (int i = 0; i < k; i++) res = minHeap.poll();
        return res;
    }

   
    public static void main(String[] args) {
        int[] a1 = {7,10,4,3,20,15};
        System.out.println(kthSmallest_MaxHeap(a1, 3)); 
        System.out.println(kthSmallest_MinHeap(a1, 3)); 

        int[] a2 = {1};
        System.out.println(kthSmallest_MaxHeap(a2, 1)); 

        int[] a3 = {3,1,4,1,5,9,2,6};
        System.out.println(kthSmallest_MaxHeap(a3, 4)); 
    }
}
