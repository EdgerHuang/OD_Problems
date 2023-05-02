package 字符串数组集合操作.寻找相同子串;


/**
 *
 * 题目描述：
 *     给你两个字符串t和p，要求从t中找到一个和p相同的连续子串，并输出该子串第一个字符的下标。
 *
 * 输入描述：
 *     输入文件包括两行 分别表示字符串t和p
 *     保证t的长度不小于p
 *     且t的长度不超过1000000
 *     p的长度不超过10000
 *
 * 输出描述：
 *     如果能从t中找到一个和p相等的连续子串，则输出该子串第一个字符在t中的下标，下标从左到右依次为1,2,3,...;
 *     如果不能，则输出“NO”
 *     如果含有多个这样的子串，则输出第一个字符下标最小的
 *
 * 用例：
 *     输入：AVERDXIVYERDIAN
 *          RDXI
 *     输出：
 *          4
 *
 *
 *
 */

import java.util.Scanner;

/**
 *  解题思路：
 *      首先是可以对t字符串进行暴力遍历
 *
 *      其次另一种比较巧妙的方式是可以对t字符串进行split(),然后对其进行size()进行判断，如果大于1说明拆分成功，然后返回下标
 *
 *
 *
 *
 */

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String strT = sc.next();
        String strP = sc.next();

        int resIndex = getResult(strT,strP);

        if (resIndex >= 1){
            System.out.println(resIndex);
        }else {
            System.out.println("NO");
        }


    }



    public static int getResult(String strT,String strP){

        for (int i = 0;i < strT.length();i++){

            char tmpCh = strT.charAt(i);

            char firstCh = strP.charAt(0);

            if (tmpCh == firstCh){
                String resStr = strT.substring(i,i+strP.length());
                if (resStr.equals(strP)){
                    return i+1;
                }
            }
        }

        return -1;
    }

}
