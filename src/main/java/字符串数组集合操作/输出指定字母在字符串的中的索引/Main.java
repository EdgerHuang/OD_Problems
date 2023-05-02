package 字符串数组集合操作.输出指定字母在字符串的中的索引;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * 题目描述：
 *     给定一个字符串，把字符串按照大写在前小写在后排序，输出排好后的第K个字母在原来字符串的索引。
 *  相同字母输出第一个出现的位置。
 *
 * 输入描述：
 *     无
 * 输出描述：
 *     无
 *
 * 用例：
 *     输入：hAkDAjByBq 4
 *     输出：6
 *     说明：排好序后 AABBDhjkqy，第 4 个是 B，第一个出现的在原字符串 6 这个位置。（注：索引是从 0 开始）
 *
 *
 *
 *
 */

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String originStr = sc.next();

        int k = sc.nextInt();

        System.out.println(getResult(originStr,k));

    }

    public static int getResult(String originStr, int k){

        char[] chars = originStr.toCharArray();

        Arrays.sort(chars);

        String newStr = new String(chars);

        char ch = newStr.charAt(k-1);

        int resIndex = originStr.indexOf(ch);
        return resIndex;
    }

}
