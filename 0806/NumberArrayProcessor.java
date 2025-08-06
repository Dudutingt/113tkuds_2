import java.util.Scanner;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入整數個數: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        System.out.println("請輸入 " + n + " 個整數：");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }

        double avg = (double) sum / n;

        System.out.println("最大值: " + max);
        System.out.println("最小值: " + min);
        System.out.println("總和: " + sum);
        System.out.printf("平均值: %.2f\n", avg);
    }
}
