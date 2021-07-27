package class01单调栈和窗口及其更新结构;

import java.util.Stack;

//对数器测试过，正确
public class Code04_AllTimesMinToMax {
    public static int max2(int[] arr) {
        int size = arr.length;

        int[] sum = new int[size];
        sum[0] = arr[0];
        for(int i = 1;i < size;i++){
            sum[i] = sum[i-1] + arr[i];
        }
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for(int i = 0;i < size;i++){
            while(!stack.isEmpty()&&arr[stack.peek()] > arr[i]){
                int j = stack.pop();
                max = Math.max(max,(stack.isEmpty()?sum[i-1]:sum[i-1]-sum[stack.peek()])*arr[j]);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int j = stack.pop();
            max = Math.max(max,(stack.isEmpty()?sum[size-1]:sum[size-1]-sum[stack.peek()])*arr[j]);
        }

        return max;
    }
}
