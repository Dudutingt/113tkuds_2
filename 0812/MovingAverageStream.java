import java.util.*;

public class MovingAverageStream {
    private final int size;
    private Deque<Integer> window;
    private long sum;
    
    private TreeMap<Integer, Integer> counts;
    private int countTotal;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new ArrayDeque<>();
        this.sum = 0;
        this.counts = new TreeMap<>();
        this.countTotal = 0;
    }

    public double next(int val) {
        window.offerLast(val);
        sum += val;
        counts.put(val, counts.getOrDefault(val, 0) + 1);
        countTotal++;

        if (window.size() > size) {
            int out = window.pollFirst();
            sum -= out;
            counts.put(out, counts.get(out) - 1);
            if (counts.get(out) == 0) counts.remove(out);
            countTotal--;
        }
        return (double) sum / window.size();
    }

    public double getMedian() {
        if (countTotal == 0) return 0.0;
        int mid1 = (countTotal + 1) / 2;
        int mid2 = (countTotal % 2 == 1) ? mid1 : mid1 + 1;
        int c = 0;
        Integer a = null, b = null;
        for (Map.Entry<Integer,Integer> e : counts.entrySet()) {
            c += e.getValue();
            if (a == null && c >= mid1) a = e.getKey();
            if (b == null && c >= mid2) { b = e.getKey(); break; }
        }
        return ((double)a + (double)b) / 2.0;
    }

    public int getMin() {
        if (counts.isEmpty()) throw new NoSuchElementException();
        return counts.firstKey();
    }

    public int getMax() {
        if (counts.isEmpty()) throw new NoSuchElementException();
        return counts.lastKey();
    }

   
    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1)); 
        System.out.println(ma.next(10)); 
        System.out.printf("%.2f\n", ma.next(3)); 
        System.out.println(ma.next(5)); // 6.0
        System.out.println("median=" + ma.getMedian()); 
        System.out.println("min=" + ma.getMin()); 
        System.out.println("max=" + ma.getMax()); 
    }
}
