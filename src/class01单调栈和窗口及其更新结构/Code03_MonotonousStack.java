package class01单调栈和窗口及其更新结构;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//对数器测过，正确
public class Code03_MonotonousStack {

    //无重复值
    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i < arr.length;i++){
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                Integer popInteger = stack.pop();
                int leftLessIndex = stack.isEmpty()? -1:stack.peek();
                res[popInteger][0] = leftLessIndex;
                res[popInteger][1] = i;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            Integer popInteger = stack.pop();
            int leftLessIndex= stack.isEmpty()? -1:stack.peek();
            res[popInteger][0] = leftLessIndex;
            res[popInteger][1] = -1;
        }

        return res;
    }

    //有重复值，用list保存
    public static int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];

        Stack<List<Integer>> stack = new Stack<>();
        for(int i = 0;i < arr.length;i++){
            while(!stack.isEmpty()&&arr[stack.peek().get(0)] > arr[i]){
                List<Integer> popIs = stack.pop();
                int leftLessIndex = stack.isEmpty()? -1 : stack.peek().get(stack.peek().size()-1);

                for(Integer index:popIs){
                    res[index][0] = leftLessIndex;
                    res[index][1] = i;
                }
            }

            if(!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
                stack.peek().add(Integer.valueOf(i));
            }else {
                List<Integer> ls = new ArrayList();
                ls.add(i);
                stack.push(ls);
            }
        }
        while(!stack.isEmpty()){
            List<Integer> popIs = stack.pop();
            int leftLessIndex = stack.isEmpty()? -1 : stack.peek().get(stack.peek().size()-1);

            for(Integer index:popIs){
                res[index][0] = leftLessIndex;
                res[index][1] = -1;
            }
        }

        return res;
    }
}
