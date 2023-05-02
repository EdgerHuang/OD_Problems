package 字符串数组集合操作.计算最大乘积;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * 题目描述：
 *     给定一个元素类型为小写字符串的数组，请计算两个没V有相同字符的元素长度乘积的最大值，如果没有符合条件的两个元素，返回0
 *
 * 输入描述：
 *     输入为一个半角逗号分隔的小写字符串的数组，2 <= 数组长度 <= 100, 0 < 字符串长度 <= 50。
 *
 * 输出描述：
 *     两个没有相同字符的元素长度乘积的最大值
 *
 *
 * 用例：
 *     输入：iwdvpbn,hk,iuop,iikd,kadgpf
 *     输出：14
 *
 *     说明：数组中有5个元素，iwdvpbn与hk无相同的字符，满足条件，iwdvpbn的长度为7，hk的长度为2，乘积为14(7*2)
 *          iwdvpbn与iuop， iikd，kadgpf均有相同的字符，不满足条件。
 *          iuop与iikd，kadgpf均有相同的字符，不满足条件。
 *          iikd与kadgpf有相同的字符，不满足条件。因此，输出为14
 */

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String originStr = sc.next();

        System.out.println(getResult(originStr));
    }

    public static int getResult(String originStr){

        String[] arr = originStr.split(",");

        int max = 0;

        for (int i = 0;i < arr.length-1;i++){
            for (int j = i+1;j < arr.length;j++){
                if (!isHaveInCommon(arr[i], arr[j])){
                    max = Math.max(max,arr[i].length() * arr[j].length());
                }
            }
        }
        return max;
    }

    public static boolean isHaveInCommon(String str1,String str2){

        HashSet<Character> set = new HashSet<>();

        for (char ch:str2.toCharArray()){
            set.add(ch);
        }

        for (char ch:str1.toCharArray()){
            if (set.contains(ch)){
                return true;
            }
        }

        return false;
    }
}
