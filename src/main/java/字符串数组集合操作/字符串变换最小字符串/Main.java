package 字符串数组集合操作.字符串变换最小字符串;


/**
 *
 * 题目描述：
 *     给定一个字符串s,最多只能进行一次变换，返回变换后能得到的最小字符串（按照字典序进行比较）。
 *     变换规则：交换字符串中任意俩个不同位置的字符。
 *
 * 输入描述：
 *     一串小写字母组成的字符串s
 *
 * 输出描述：
 *     按照要求进行变换得到的最小字符串。
 *
 * 用例：
 *     输入：abcdef
 *     输出：abcdef
 *     说明：abcdef已经是最小字符串，不需要交换
 *
 *     输入：bcdefa
 *     输出：acdefb
 *     说明：a和b进行位置交换，可以得到最小字符串。
 *
 *     (自测)输入：
 *          acdebtybulbx    abbbcdeltuxy
 *
 *          abdectybulbx
 *
 */


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 解题思路：
 *     首先想到的是暴力，将所有可能的情况都收集起来，然后进行字典排序,特点是效率比较低
 *
 *      另一种方式：
 *          我们首先对字符串进行字典排序,然后比较是否和原始字符串一致，如果一致直接返回
 *          否则，我们对排好序的字符串进行遍历，逐个字符比较，看相应位置的字符是否在原始字符串的相应位置
 *          如果在，则继续遍历，如果不再则调整位置进行返回
 *          这样做效率会高一些
 *          思考： 如果有重复字符串，会产生bug ，见自测用例，所以我们需要将重复的最后一个字符进行交换
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String originStr = sc.next();

        System.out.println(getResult(originStr));
    }

    public static String getResult(String originStr){

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0;i < originStr.length()-1;i++){
            for (int j = i+1;j < originStr.length();j++){

                char[] chars = originStr.toCharArray();

                char tmp = chars[i];
                chars[i] = chars[j];
                chars[j] = tmp;

                String tmpStr = new String(chars);

                list.add(tmpStr);
            }
        }

        list.sort(Comparator.naturalOrder());

        return list.get(0);

    }



}
