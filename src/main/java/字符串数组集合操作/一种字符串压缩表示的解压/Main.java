package 字符串数组集合操作.一种字符串压缩表示的解压;

import java.util.Scanner;

/**
 * 题目描述
 *     有一种简易压缩算法：针对全部由小写英文字母组成的字符串，
 *     将其中连续超过两个相同字母的部分压缩为连续个数加该字母，其他部分保持原样不变。
 *     请您编写解压函数，根据输入的字符串，判断其是否为合法压缩过的字符串，
 *     若输入合法则输出解压缩后的字符串，否则输出字符串“!error”来报告错误。
 *
 * 输入描述：
 *     输入一行，为一个ASCII字符串，长度不会超过100个字符，用例保证输出的字符串长度也不会超过100字符
 *
 * 输出描述：
 *     若判读输入为合法的经过压缩后的字符串，则输出压缩前的字符串；若输入不合法，则输出字符串“!error”
 *
 * 用例：
 *
 *     输入：4dff
 *     输出：ddddff
 *     说明：4d扩展为dddd,故解压后的字符串为ddddff
 *
 *     输入：2dff
 *     输出：！error
 *     说明：两个d不需要压缩，故输入不合法
 *
 *     输入：4d@A
 *     输出：！error
 *     说明：全部由小写英文字母组成的字符串压缩后不会出现特俗字符@和大写字母A，故输入不合法
 *
 *
 */

/**
 *  解题思路：
 *      说一下解这道题的新路历程，首先是对于字符串合法性的判断，一开始认为是只要不是符合小写字母或者数字，都将其计入非法字符
 *      但是只有数字和小写字母就一定是合法字符了吗，并不是，如果是0,1，2这样的数字呢，仍旧不合法，那如果我得到的是数字字符，
 *      我判断其大小不就可以了，但是需要注意的是，这里可能会有多个连续的数字，所以需要将数字本身的大小意义表现出来，
 *
 *      这道题，我给了更好的扩展性，就是对数字的位数的采集，可以无限采集(当然字符串比较长时，加入不暴内存),
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        System.out.println(getResult(str));
    }  

    public static String getResult(String str) {

        if (!isValid(str))
            return "error";

        int i = 0;

        StringBuilder sb = new StringBuilder();

        while (i < str.length()){

            char ch = str.charAt(i);

            if (Character.isLowerCase(ch)){
                sb.append(ch);
                i++;
            }else {
                StringBuilder tmpSb = new StringBuilder();
                tmpSb.append(ch);
                Character one;
                while (Character.isDigit(one = str.charAt(++i))){
                    tmpSb.append(one);
                }
                int tmpSum = Integer.parseInt(tmpSb.toString());
                for (int j = 0;j < tmpSum;j++){
                    sb.append(one);
                }
                i++;
            }
        }
        return sb.toString();
    }

    public static boolean isValid(String str){

        int count = 0;

        int i = 0;

        while (i < str.length()){

            char ch = str.charAt(i);

            if (Character.isLowerCase(ch)){
                i++;
                continue;
            }else if (Character.isDigit(ch)){
                StringBuilder sb = new StringBuilder();
                sb.append(ch);
                Character one;
                while (Character.isDigit(one = str.charAt(++i))){
                    sb.append(one);
                }
                int tmpI = Integer.parseInt(sb.toString());
                if (tmpI <= 2)
                    count++;

                // 后退一步,为了和其它情况保持一致
                i--;

            }else{
                count++;
            }
            i++;
        }
        return count == 0;
    }

}
