

import java.io.*;
import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt(), m = fs.nextInt();
        double[] A = new double[n];
        double[] B = new double[m];
        for (int i = 0; i < n; i++) A[i] = fs.nextDouble();
        for (int i = 0; i < m; i++) B[i] = fs.nextDouble();

        System.out.printf(Locale.US, "%.1f%n", findMedianSortedArrays(A, B));
    }

    // 二分切割法
    static double findMedianSortedArrays(double[] nums1, double[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);

        int n = nums1.length, m = nums2.length;
        int totalLeft = (n + m + 1) / 2;
        int lo = 0, hi = n;

        while (lo <= hi) {
            int i = (lo + hi) >>> 1; // A 切點
            int j = totalLeft - i;   // B 切點

            double Aleft = (i == 0) ? -1e18 : nums1[i - 1];
            double Aright = (i == n) ? 1e18 : nums1[i];
            double Bleft = (j == 0) ? -1e18 : nums2[j - 1];
            double Bright = (j == m) ? 1e18 : nums2[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                if (((n + m) & 1) == 1) return Math.max(Aleft, Bleft);
                return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
            } else if (Aleft > Bright) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        return 0.0; 
    }

    
    static class FastScanner {
        InputStream in; byte[] buf = new byte[1 << 16]; int p = 0, l = 0;
        FastScanner(InputStream is) { in = is; }
        int read() {
            if (p >= l) {
                try { l = in.read(buf); p = 0; } catch (Exception e) {}
                if (l <= 0) return -1;
            }
            return buf[p++];
        }
        String next() {
            StringBuilder sb = new StringBuilder();
            int c; while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do { sb.append((char)c); c = read(); } while (c > ' ');
            return sb.toString();
        }
        int nextInt() { return Integer.parseInt(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
    }
}

