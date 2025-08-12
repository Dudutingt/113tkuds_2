import java.util.*;

public class MeetingRoomScheduler {

    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>(); 
        for (int[] it : intervals) {
            if (!pq.isEmpty() && pq.peek() <= it[0]) {
                pq.poll(); 
            }
            pq.offer(it[1]);
        }
        return pq.size();
    }

    public static List<int[]> maximizeTotalTimeWithNRooms(int[][] intervals, int N) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1])); 
        
        PriorityQueue<Integer> activeEnds = new PriorityQueue<>();
        List<int[]> chosen = new ArrayList<>();
        for (int[] it : intervals) {
            
            while (!activeEnds.isEmpty() && activeEnds.peek() <= it[0]) activeEnds.poll();
            if (activeEnds.size() < N) {
                // schedule it
                activeEnds.offer(it[1]);
                chosen.add(it);
            } else {
                
                PriorityQueue<Integer> maxEnds = new PriorityQueue<>((a,b)->b-a);
                maxEnds.addAll(activeEnds);
                int latest = maxEnds.peek();
                if (latest > it[1]) {
                    
                    boolean removed = false;
                    List<Integer> tmp = new ArrayList<>();
                    while (!activeEnds.isEmpty()) {
                        int e = activeEnds.poll();
                        if (!removed && e == latest) { removed = true; continue; }
                        tmp.add(e);
                    }
                    activeEnds.addAll(tmp);
                    activeEnds.offer(it[1]);
                    
                    for (int j = 0; j < chosen.size(); j++) {
                        if (chosen.get(j)[1] == latest) { chosen.remove(j); break; }
                    }
                    chosen.add(it);
                }
            }
        }
        
        return chosen;
    }

    public static void main(String[] args) {
        int[][] a1 = {{0,30},{5,10},{15,20}};
        System.out.println(minMeetingRooms(a1)); 

        int[][] a2 = {{9,10},{4,9},{4,17}};
        System.out.println(minMeetingRooms(a2));

        int[][] a3 = {{1,5},{8,9},{8,9}};
        System.out.println(minMeetingRooms(a3)); 

       
        int[][] ex = {{1,4},{2,3},{4,6}};
        List<int[]> schedule = maximizeTotalTimeWithNRooms(ex, 1);
        int total = 0;
        for (int[] it : schedule) total += it[1] - it[0];
        System.out.println("Total scheduled time with 1 room = " + total);
        
    }
}
