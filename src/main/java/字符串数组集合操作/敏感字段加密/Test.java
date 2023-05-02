package 字符串数组集合操作.敏感字段加密;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = Integer.parseInt(sc.nextLine());
        String s = sc.nextLine();

        System.out.println(getResult(k, s));
    }

    public static String getResult(int k, String s) {

        StringBuilder stack = new StringBuilder();

        LinkedList<String> result = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            // 两种情况,一种是stack中没有元素,另一种是stack中不存在"字符,因为如果存在"字符,一定是在栈底
            if (c == '_' && (stack.length() == 0 || stack.charAt(0) != '"')) {
                result.add(stack.toString());
                // 弹栈之后清空栈中的元素
                stack = new StringBuilder();
            } else if (c == '"' && stack.length() != 0) { // 因为遇到的第一次遇到"元素时,一定是在栈为空的时候,可以直接压入栈中
                stack.append('"');
                result.add(stack.toString());

                // 弹栈之后清空栈中的元素
                stack = new StringBuilder();
            } else {
                stack.append(c);
            }
        }

        // 为了防止如果字符串不是以_结尾时，此时栈中还有元素，需要进行弹栈操作
        if (stack.length() > 0)
            result.add(stack.toString());

        String[] arr = result.toArray(String[]::new);

        System.out.println(Arrays.toString(arr));
        //
        List<String> ans = result.stream().filter(str -> !"".equals(str)).collect(Collectors.toList());

        if (k > ans.size() - 1) return "ERROR";
        ans.set(k, "******");

        StringJoiner sj = new StringJoiner("_");

        for (String an : ans) sj.add(an);
        return sj.toString();
    }
}