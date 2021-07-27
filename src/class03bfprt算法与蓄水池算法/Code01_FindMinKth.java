package class03bfprt算法与蓄水池算法;
import java.util.Comparator;
import java.util.PriorityQueue;

//第k小的数
//对数器测过 已经正确
public class Code01_FindMinKth {

    //利用大根堆去解决此问题
    public static class MaxHeapComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    }
    public static int minKth1(int[] array,int k){
        int[] arr = copyArray(array);
        PriorityQueue<Integer> pq = new PriorityQueue<>(new MaxHeapComparator());
        for(int i = 0;i < k;i ++){
            pq.add(arr[i]);
        }
        for(int i = k;i < arr.length;i++){
            if(arr[i] < pq.peek()){ //如果相等,弹出并且加入,多此一举.因为两个数字相等
                pq.poll();
                pq.add(arr[i]);
            }
        }
        return pq.peek();
    }

    public static int[] copyArray(int[] arr){
        int[] ans = new int[arr.length];
        for(int i = 0;i < arr.length;i++){
            ans[i] = arr[i];
        }
        return ans;
    }

    //改写快排
    public static int minKth2(int[] array,int k){
        int[] arr = copyArray(array);
        return process2(arr,0,arr.length-1,k-1);
    }
    public static int process2(int[] arr,int L, int R, int index){
        if(L == R){
            return arr[L];
        }
        int pivot = arr[L + (int)(Math.random()*(R-L+1))];
        int[] range = partition(arr,L,R,pivot);
        if(index >= range[0] && index <= range[1]){
            return arr[index];
        }else if(index < range[0]){
            return process2(arr,L,range[0]-1,index);
        }else {
            return process2(arr,range[1]+1,R,index);
        }
    }
    public static int[] partition(int[] arr,int L,int R,int pivot){
        if(L == R){
            return new int[]{L,L};
        }
        int less = L-1;
        int more = R+1;
        int index = L;
        while(index < more){
            if(arr[index] == pivot){
                index++;
            }else if(arr[index] > pivot){
                swap(arr,--more,index);
            }else {
                swap(arr,++less,index++);
            }
        }
        return new int[]{less+1,more-1};
    }
    public static void swap(int[] arr,int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    //bfprt算法
    public static int minKth3(int[] array,int k){
        int[] arr = copyArray(array);
        return bfprt(arr,0,arr.length-1,k-1);//k-1代表了下标
    }
    public static int bfprt(int[] arr,int L,int R,int index){
        if(L == R){
            return arr[L];
        }
        int pivot = medianOfMedians(arr,L,R);
        int[] range = partition(arr,L,R,pivot);
        if(index >= range[0] && index <= range[1]){
            return arr[index];
        }else if(index < range[0]){
            return bfprt(arr,L,range[0]-1,index);
        }else {
            return bfprt(arr,range[1]+1,R,index);
        }
    }
    public static int medianOfMedians(int[] arr, int L, int R){
        int size = R-L+1;
        int offset = size % 5==0? 0:1;
        int[] mArr = new int[size/5+offset];
        for(int team =0;team < mArr.length;team++){
            int teamFirst = L + 5*team;
            mArr[team] = getMedian(arr,teamFirst,Math.min(R,teamFirst+4));
        }
        return bfprt(mArr,0,mArr.length-1,mArr.length/2);//mArr.length/2就是中位数的下标
    }
    public static int getMedian(int[] arr,int L, int R){
        insertionSort(arr,L,R);
        return arr[(L+R)/2];
    }
    public static void insertionSort(int[] arr,int L,int R){
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }
    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = minKth1(arr, k);
            int ans2 = minKth2(arr, k);
            int ans3 = minKth3(arr, k);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
