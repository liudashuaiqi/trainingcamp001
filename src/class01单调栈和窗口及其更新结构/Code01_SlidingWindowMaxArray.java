package class01单调栈和窗口及其更新结构;

import java.util.LinkedList;

//已测试 success
//leetcode 239
public class Code01_SlidingWindowMaxArray {
    public static int[] getMaxWindow(int[] arr, int w) {
        if(arr == null|| w < 1 || arr.length < w){
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] rest = new int[arr.length-w+1];
        int index = 0;
        for(int R = 0;R < arr.length; R++){
            while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]){
                qmax.pollLast();
            }
            qmax.addLast(R);

            if(qmax.peekFirst() == R-w){
                qmax.pollFirst();
            }
            if(R >= w-1){
                rest[index++] = arr[qmax.peekFirst()];
            }
        }
        return rest;
    }

}
