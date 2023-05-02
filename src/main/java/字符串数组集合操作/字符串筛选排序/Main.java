package 字符串数组集合操作.字符串筛选排序;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述：
 *     输入一个由N个大小写字母组成的字符串
 *     按照ASCII码值从小到大进行排序
 *     查找字符串中第K个最小ASCII码值的字母(K >= 1)
 *     输出该字母所在字符串中的位置索引(字符串的第一个位置索引为0)
 *     K如果大于字符串长度则输出最大ASCII码值的字母所在字符串的位置索引
 *     如果有重复字母则输出子母的最小位置索引
 *
 * 输入描述：
 *     第一行输入一个由大小写字母组成的字符串
 *     第二行输入k，k必须大于0，k可以大于输入字符串的长度
 *
 * 输出描述：
 *     输出字符串中第K个最小ASCII码值的字母所在字符串的位置索引
 *     K如果大于字符串长度则输出最大ASCII码值的字母所在字符串的位置索引
 *     如果第K个最小ASCII码值的字母存在重复 则输出该字母的最小位置索引
 *
 * 用例：
 *     输入：AbCdeFG
 *          3
 *
 *     输出：5
 *
 *     说明：根据ASCII码值排序，第三个ASCII码值的字母为F
 *           F在字符串中位置索引为5（0为字符串的第一个字母位置索引）
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        int k = sc.nextInt();
        System.out.println(getResult(str,k));
    }

    public static int getResult(String str,int k){

        char[] chars = str.toCharArray();

        Arrays.sort(chars);

        String newStr = new String(chars);

        char ch = newStr.charAt(k-1);

        return str.indexOf(ch);
    }

}
