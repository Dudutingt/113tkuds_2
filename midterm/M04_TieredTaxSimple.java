import java.io.*;

public class M04_TieredTaxSimple {
   
    static final long[] UPPER = {120_000L, 500_000L, 1_000_000L, Long.MAX_VALUE};
    
    static final double[] RATE = {0.05, 0.12, 0.20, 0.30};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      
        String line;
        while ((line = br.readLine()) != null && line.trim().isEmpty()) {}
        if (line == null) return;
        int n = Integer.parseInt(line.trim());

        long[] taxes = new long[n];
        long sum = 0;

        for (int i = 0; i < n; i++) {
            String s;
            while ((s = br.readLine()) != null && s.trim().isEmpty()) {}
            if (s == null) break;
            long income = Long.parseLong(s.trim());

            long tax = calcTax(income);
            taxes[i] = tax;
            sum += tax;
        }

       
        StringBuilder out = new StringBuilder();
        for (long t : taxes) {
            out.append("Tax: ").append(t).append('\n');
        }

        long avg = Math.round((double) sum / n);
        out.append("Average: ").append(avg);
        System.out.print(out.toString());
    }

    
    private static long calcTax(long x) {
        long prev = 0;
        double total = 0.0;

        for (int i = 0; i < UPPER.length; i++) {
            long hi = UPPER[i];
            if (x > prev) {
                long taxable = Math.min(x, hi) - prev;
                total += taxable * RATE[i];
                if (x <= hi) break;
                prev = hi;
            } else break;
        }
        
        return Math.round(total);
    }
}
/*
 * Time Complexity: O(n)
 * 說明：每筆收入以固定 4 個稅率區間逐段累加，單筆計算為 O(1)；
 *       共處理 n 筆輸入，總時間為 O(n)。額外空間為 O(1)。
 */