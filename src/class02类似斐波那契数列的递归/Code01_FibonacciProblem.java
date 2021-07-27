package class02类似斐波那契数列的递归;

//已经测试，正确

//f为斐波那契数列问题
//s为迈楼梯问题
//c为奶牛问题
public class Code01_FibonacciProblem {

    public static int f1(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1|| n == 2){
            return 1;
        }
        return f1(n-1)+f1(n-2);
    }

    public static int f2(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1|| n == 2){
            return 1;
        }
        int res = 1;
        int pre = 1;
        int tmp = 0;
        for(int i = 3;i <= n;i++){
            tmp = res;
            res += pre;
            pre = tmp;
        }
        return res;
    }

    //矩阵快速幂
    public static int f3(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1|| n == 2){
            return 1;
        }
        int[][] base = {
                {1,1},
                {1,0}
                };
        int[][] res = matrixPower(base,n-2);
        return res[0][0]+res[1][0];
    }

    public static int[][] matrixPower(int[][] m,int p){
        int[][] res = new int[m.length][m[0].length];
        for(int i = 0;i < m.length;i++){
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for(; p != 0 ; p >>= 1){
            if( (p&1) != 0){
                res = muliMatrix(res,tmp);
            }
            tmp = muliMatrix(tmp,tmp);
        }
        return res;
    }

    public static int[][] muliMatrix(int[][] m1, int[][] m2){
        int[][] res = new int[m1.length][m2[0].length];
        for(int i = 0;i < m1.length;i++){
            for(int j = 0;j < m2[0].length;j++){
                for(int k = 0;k < m2.length;k++){
                    res[i][j] += m1[i][k]*m2[k][j];
                }
            }
        }
        return res;
    }

    //矩阵快速幂
    public static int s3(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1|| n == 2){
            return n;
        }
        int[][] base = {
                {1,1},
                {1,0}
        };
        int[][] res = matrixPower(base,n-2);
        return 2*res[0][0]+res[1][0];
    }

    //矩阵快速幂
    public static int c3(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2|| n == 3){
            return n;
        }
        int[][] base = {
                {1,1,0},
                {0,0,1},
                {1,0,0}
        };
        int[][] res = matrixPower(base,n-3);
        return 3*res[0][0] + 2*res[1][0] + res[2][0];
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(f3(n));
        System.out.println("===");

//        System.out.println(s1(n));
//        System.out.println(s2(n));
        System.out.println(s3(n));
//        System.out.println("===");
//
//        System.out.println(c1(n));
//        System.out.println(c2(n));
//        System.out.println(c3(n));
//        System.out.println("===");

    }

}
