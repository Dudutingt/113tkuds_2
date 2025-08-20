import  java.util.*;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  
        sc.nextLine(); 

        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            String t = sc.nextLine().trim();
            times[i] = toMinutes(t);
        }

        String query = sc.nextLine().trim();
        int q = toMinutes(query);

        
        int idx = binarySearch(times, q);

        if (idx == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHMM(times[idx]));
        }
    }

    static int toMinutes(String s) {
        String[] parts = s.split(":");
        int hh = Integer.parseInt(parts[0]);
        int mm = Integer.parseInt(parts[1]);
        return hh * 60 + mm;
    }

    static String toHHMM(int m) {
        int hh = m / 60;
        int mm = m % 60;
        return String.format("%02d:%02d", hh, mm);
    }

    static int binarySearch(int[] arr, int q) {
        int l = 0, r = arr.length - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] > q) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}


