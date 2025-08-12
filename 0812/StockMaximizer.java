import java.util.*;

public class StockMaximizer {
    
    public static int maxProfitWithKTransactions(int[] prices, int K) {
        if (prices == null || prices.length < 2 || K <= 0) return 0;
        PriorityQueue<Integer> profits = new PriorityQueue<>((a,b) -> b - a);
        int n = prices.length;
        int i = 0;
        while (i < n - 1) {
            while (i < n - 1 && prices[i+1] <= prices[i]) i++;
            int buy = i;
            while (i < n - 1 && prices[i+1] > prices[i]) i++;
            int sell = i;
            if (sell > buy) {
                profits.offer(prices[sell] - prices[buy]);
            }
        }
        int ans = 0;
        for (int t = 0; t < K && !profits.isEmpty(); t++) ans += profits.poll();
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxProfitWithKTransactions(new int[]{2,4,1}, 2)); 
        System.out.println(maxProfitWithKTransactions(new int[]{3,2,6,5,0,3}, 2)); 
        System.out.println(maxProfitWithKTransactions(new int[]{1,2,3,4,5}, 2)); 
    }
}
