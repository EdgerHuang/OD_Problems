package 测试;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String b_sub = sc.next();
        String sub = sc.next();
        System.out.println(getResult(n, b_sub, sub));
    }

    /**
     * @param n 表示被减数和减数是n进制数
     * @param b_sub 被减数
     * @param sub 减数
     * @return 输出正负符号和表示结果的字符串
     */
    public static String getResult(int n, String b_sub, String sub) {
        if (n < 2 || n > 35) return "-1";

        if (!isValid(b_sub, n) || !isValid(sub, n)) return "-1";

        long b_sub_val = Long.parseLong(b_sub, n);
        long sub_val = Long.parseLong(sub, n);

        String diff = Long.toString(Math.abs(b_sub_val - sub_val), n);
        String sign = b_sub_val >= sub_val ? "0" : "1";

        return sign + " " + diff;
    }

    public static boolean isValid(String str, int n) {
        // 含前导的0只有0值本身合法
        if (str.startsWith("0")) return "0".equals(str);

        // 被减数，减数只能包含字符0-9，a-z
        Pattern reg = Pattern.compile("[^a-z0-9]");
        if (reg.matcher(str).find()) return false;

        // 被减数，减数长度最多100
        if (str.length() > 100) return false;

        // 被减数，减数的每位不能超过n
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9' && Integer.parseInt(c + "") >= n) return false; // 比如2进制数的每一位不能超过2
            if (c >= 'a' && c <= 'z' && c - 87 >= n) return false; // 比如16进制数每一位不能超过f
        }

        return true;
    }
}