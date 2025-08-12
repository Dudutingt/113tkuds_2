import java.util.*;

public class SlidingWindowMedian {
    private PriorityQueue<Integer> maxHeap; // 小的一半（最大堆）
    private PriorityQueue<Integer> minHeap; // 大的一半（最小堆）
    private Map<Integer, Integer> delayed;  // 延遲刪除的元素
    private int leftSize;  // maxHeap 的有效元素數量
    private int rightSize; // minHeap 的有效元素數量

    public SlidingWindowMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        delayed = new HashMap<>();
        leftSize = 0;
        rightSize = 0;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
            if (i >= k) {
                remove(nums[i - k]);
            }
            if (i >= k - 1) {
                result[i - k + 1] = getMedian(k);
            }
        }

        return result;
    }

    private void add(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
            leftSize++;
        } else {
            minHeap.offer(num);
            rightSize++;
        }
        balance();
    }

    private void remove(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= maxHeap.peek()) {
            leftSize--;
            if (num == maxHeap.peek()) {
                prune(maxHeap);
            }
        } else {
            rightSize--;
            if (num == minHeap.peek()) {
                prune(minHeap);
            }
        }
        balance();
    }

    private void balance() {
        if (leftSize > rightSize + 1) {
            minHeap.offer(maxHeap.poll());
            leftSize--;
            rightSize++;
            prune(maxHeap);
        } else if (leftSize < rightSize) {
            maxHeap.offer(minHeap.poll());
            rightSize--;
            leftSize++;
            prune(minHeap);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
            int num = heap.peek();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) {
                delayed.remove(num);
            }
            heap.poll();
        }
    }

    private double getMedian(int k) {
        if (k % 2 == 1) {
            return maxHeap.peek();
        } else {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    // 測試
    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();

        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(nums1, 3)));
        // 預期: [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

        int[] nums2 = {1, 2, 3, 4};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(nums2, 2)));
        // 預期: [1.5, 2.5, 3.5]
    }
}
