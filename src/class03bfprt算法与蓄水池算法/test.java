package class03bfprt算法与蓄水池算法;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String s = null;
        for(int i = 0; i< n;i++){
            s = sc.nextLine();
            String[] str = s.split("\\\\s+");
            System.out.println(s);
            for(int j = 0;j < str.length;j++){
                System.out.println(str[i]);
            }
        }
    }
}
