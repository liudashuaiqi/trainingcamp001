package class04KMP算法及其扩展;

//已测试，代码正确
public class Code03_IsRotation {
    public static boolean isRotation(String a, String b){
        if(a == null || b == null || a.length()!=b.length()){
            return false;
        }
        String s = b+b;
        return getIndexOf(s,a) != -1;
    }
    //就是kmp算法 代码一模一样
    public static int getIndexOf(String s, String m) {
        if(s == null || m == null || m.length() < 1 || s.length() < m.length()){
            return -1;
        }
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int x = 0;
        int y = 0;

        int[] next = getNextArray(match);
        while(x < s.length() && y < m.length()){
            if(str[x] == match[y]){
                x++;
                y++;
            }else if(next[y]==-1){
                x++;
            }else{
                y = next[y];
            }
        }
        return y == m.length()? x-y:-1;
    }
    public static int[] getNextArray(char[] match){
        if(match.length == 1){
            return new int[] {-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while(i < match.length){
            if(match[i-1] == match[cn]){
                next[i++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else {
                next[i++] = 0;
            }
        }
        return next;
    }
    public static void main(String[] args) {
        String str1 = "yunzuocheng";
        String str2 = "zuochengyun";
        System.out.println(isRotation(str1, str2));

    }
}
