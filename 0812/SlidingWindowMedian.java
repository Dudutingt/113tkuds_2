import java.util.*;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> small; 
    private PriorityQueue<Integer> large; 
    private Map<Integer, Integer> delayed; 
    private int smallSize, largeSize;

    public SlidingWindowMedian() {
        small = new PriorityQueue<>((a,b) -> b - a);
        large = new PriorityQueue<>();
        delayed = new HashMap<>();
        smallSize = largeSize = 0;
    }

    private void balance() {
        if (smallSize > largeSize + 1) {
            int v = small.poll(); smallSize--;
            large.offer(v); largeSize++;
        } else if (smallSize < largeSize) {
            int v = large.poll(); largeSize--;
            small.offer(v); smallSize++;
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int top = heap.peek();
            if (delayed.getOrDefault(top, 0) > 0) {
                delayed.put(top, delayed.get(top) - 1);
                if (delayed.get(top) == 0) delayed.remove(top);
                heap.poll();
            } else break;
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (k == 0) return new double[0];
        List<Double> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (small.isEmpty() || num <= small.peek()) { small.offer(num); smallSize++; }
            else { large.offer(num); largeSize++; }

            balance();

            if (i >= k) {
                int out = nums[i - k];
                
                delayed.put(out, delayed.getOrDefault(out, 0) + 1);
               
                if (!small.isEmpty() && out <= small.peek()) smallSize--;
                else largeSize--;
                
                prune(small); prune(large);
                balance();
            }

            if (i >= k - 1) {
                prune(small); prune(large);
                if ((k & 1) == 1) ans.add((double) small.peek());
                else ans.add(((double) small.peek() + (double) large.peek()) / 2.0);
            }
        }
        double[] res = new double[ans.size()];
        for (int i = 0; i < ans.size(); i++) res[i] = ans.get(i);
        return res;
    }

    
    public static void main(String[] args) {
        SlidingWindowMedian s = new SlidingWindowMedian();
        int[] a1 = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(s.medianSlidingWindow(a1, 3))); 

        SlidingWindowMedian s2 = new SlidingWindowMedian();
        int[] a2 = {1,2,3,4};
        System.out.println(Arrays.toString(s2.medianSlidingWindow(a2, 2))); 
    }
}
