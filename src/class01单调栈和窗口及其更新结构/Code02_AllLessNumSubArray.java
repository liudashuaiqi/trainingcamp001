package class01单调栈和窗口及其更新结构;

import java.util.LinkedList;

//达标子数组数量
//一行一行对过，正确
public class Code02_AllLessNumSubArray {
    public static int getNum(int[] arr, int num) {
        if(arr == null || arr.length == 0){
            return 0;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();

        int L = 0;
        int R = 0;
        int res = 0;
        while(L < arr.length){
            while(R < arr.length){
                //一定要加等号，但是滑动窗口最大值加不加等号都可以
                //因为把一个不合法的东西加入到了滑动窗口当中，等下一次L左移的时候，L+1....R可能还是不合法的
                //所以需要重新对R位置进行判断，应该把当前窗口中的R位置吐出了，重新放入R位置，否则就会加入重复的R位置
                while(!qmax.isEmpty() && arr[qmax.peekLast()]<=arr[R]){
                    qmax.pollLast();
                }
                qmax.addLast(R);

                while(!qmin.isEmpty() && arr[qmin.peekLast()]>=arr[R]){
                    qmin.pollLast();
                }
                qmin.addLast(R);

                //getFirst不存在第一个元素抛异常 || peekFirst不存在第一个元素返回null
                if(arr[qmax.getFirst()]-arr[qmin.getFirst()] > num){
                    break;
                }
                R++;
            }
            res += R-L;

            if(qmax.peekFirst() == L){
                qmax.pollFirst();
            }
            if(qmin.peekFirst() == L){
                qmin.pollFirst();
            }

            L++;
        }
        return res;
    }
}
