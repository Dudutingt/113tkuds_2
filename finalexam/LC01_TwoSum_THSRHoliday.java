import java.io.*;
import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt(), target = fs.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        Map<Integer, Integer> needIdx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (needIdx.containsKey(a[i])) {
                System.out.println(needIdx.get(a[i]) + " " + i);
                return;
            }
            long need = (long)target - a[i]; 
            if (need >= Integer.MIN_VALUE && need <= Integer.MAX_VALUE)
                needIdx.put((int)need, i);
        }
        System.out.println("-1 -1");
    }

    static class FastScanner {
        InputStream in; byte[] buffer = new byte[1 << 16]; int ptr = 0, len = 0;
        FastScanner(InputStream is){in=is;}
        int read(){ if(ptr>=len){ try{ len=in.read(buffer); ptr=0;}catch(Exception e){} if(len<=0) return -1;} return buffer[ptr++];}
        String next(){ StringBuilder sb=new StringBuilder(); int c; while((c=read())!=-1 && c<=' '){} if(c==-1) return null; do{ sb.append((char)c); c=read(); }while(c> ' '); return sb.toString();}
        int nextInt(){ return Integer.parseInt(next());}
        long nextLong(){ return Long.parseLong(next());}
    }
}
