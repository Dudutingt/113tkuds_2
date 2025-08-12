import java.util.*;

class Task {
    String name;
    int priority;
    long timestamp;

    Task(String name, int priority, long timestamp) {
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }
}

public class PriorityQueueWithHeap {
    private PriorityQueue<Task> pq;
    private long time;

    public PriorityQueueWithHeap() {
        time = 0;
        pq = new PriorityQueue<>((a, b) -> {
            if (b.priority != a.priority) return b.priority - a.priority;
            return Long.compare(a.timestamp, b.timestamp);
        });
    }

    public void addTask(String name, int priority) {
        pq.offer(new Task(name, priority, time++));
    }

    public String executeNext() {
        if (pq.isEmpty()) return null;
        return pq.poll().name;
    }

    public String peek() {
        if (pq.isEmpty()) return null;
        return pq.peek().name;
    }

    public void changePriority(String name, int newPriority) {
        List<Task> temp = new ArrayList<>();
        while (!pq.isEmpty()) {
            Task t = pq.poll();
            if (t.name.equals(name)) {
                t.priority = newPriority;
            }
            temp.add(t);
        }
        pq.addAll(temp);
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap queue = new PriorityQueueWithHeap();
        queue.addTask("備份", 1);
        queue.addTask("緊急修復", 5);
        queue.addTask("更新", 3);

        System.out.println(queue.executeNext()); 
        System.out.println(queue.executeNext()); 
        System.out.println(queue.executeNext()); 
    }
}
