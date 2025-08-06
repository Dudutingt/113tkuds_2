import java.util.Scanner;

public class GradeStatisticsSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        System.out.print("請輸入學生人數：");
        int n = sc.nextInt();

        int[] scores = new int[n];
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        
        for (int i = 0; i < n; i++) {
            System.out.print("請輸入第 " + (i + 1) + " 位學生的分數：");
            scores[i] = sc.nextInt();

            

            sum += scores[i];

           
            if (scores[i] > max) {
                max = scores[i];
            }
            if (scores[i] < min) {
                min = scores[i];
            }
        }

        
        double average = (double) sum / n;

        
        System.out.println("總分：" + sum);
        System.out.println("平均：" + average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);

        sc.close();
    }
}
