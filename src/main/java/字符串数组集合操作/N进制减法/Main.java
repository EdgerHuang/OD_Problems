package 字符串数组集合操作.N进制减法;


import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;

/**
 *
 * 题目描述：
 *     主管期望你实现一个基于字符串的N进制的减法。
 *     需要对输入的两个字符串按照给定的N进制进行减法操作，输出正负符号和表示结果的字符串。
 *
 * 输入描述:
 *     输入有三个参数：
 *         第一个参数是整数形式的进制N值，N值范围为大于等于2、小于等于35。
 *         第二个参数为被减数字符串；
 *         第三个参数为减数字符串。
 *     有效的字符包括0-9以及小写字母a-z，字符串有效字符个数最大为100个字符，另外还有结尾的\0。
 *     限制：
 *         输入的被减数和减数，除了单独的0以外，不能是以0开头的字符串。
 *         如果输入有异常或计算过程中有异常，此时应当输出-1表示错误。
 *
 * 输出描述：
 *     输出有2个。
 *     其一为减法计算的结果，-1表示出错，0表示结果为整数，1表示结果为负数。
 *     其二表示结果为字符串。
 *
 * 用例：
 *     输入：2 11 1
 *     输出：0 10
 *     说明：按二进制计算11 -1，计算正常，0表示符号为正数，结果为10
 *
 *     输入：8 07 1
 *     输出：-1
 *     说明：按8进制，检查到减数不符合非0前导的要求，返回结果为-1，没有其它内容。
 *
 *
 */

/**
 *  这道题应该是有问题的因为经过测试
 *      我们可以发现，最多为35进制时，所能包含的最大的字符是y, 如果包含z的话，最大的进制应该是36，应该是一点小bug
 *
 *
 */

// a - 11
// b - 12
// 我们可以发现 如果出现字符a，那么最小的进制是11，如果出现字符b，最小的进制是12，
// 因此，规律是最小的进制是其对应的ASCII码值减去固定值86
// [A-Z] = [65,90]  [a-z] = [97-122] [0-9] = [48,57]
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();

        String bj = sc.next();

        String js = sc.next();

        System.out.println(getResult(k,bj,js));


    }


    public static String getResult(int k, String bj, String js){

        if (!isValid(k,bj,js)){
            return "-1";
        }
        long decBj = Long.parseLong(bj,k);

        long decJs = Long.parseLong(js,k);

        long res = decBj - decJs;

        String resStr = Long.toString(res,k);


        if (resStr.charAt(0) == '-'){
            return "1" + resStr;
        }else {
            return "0 " + resStr;
        }
    }

    public static boolean isValid(int k, String bj, String js){


        if ( k < 2 || k > 35){
            return false;
        }

        if (bj.startsWith("0")){
            return "0".equals(bj);
        }
        if (js.startsWith("0")){
            return "0".equals(js);
        }
        Pattern pattern = Pattern.compile("[0-9a-z]");

        if (!pattern.matcher(bj).find() || !pattern.matcher(js).find())
            return false;

        if (bj.length() > 100 || js.length() > 100)
            return false;


        for (int i = 0;i < bj.length();i++){

            char ch = bj.charAt(i);
            if ( ch >= '0' && ch <= '9' && Integer.parseInt(ch + "") >= k )
                return false;
            if (ch >= 'a' && ch <= 'z' && k < ch - 86 )
                return false;
        }


        for (int i = 0;i < js.length();i++){

            char ch = bj.charAt(i);
            if ( ch >= '0' && ch <= '9' && Integer.parseInt(ch + "") >= k )
                return false;
            if (ch >= 'a' && ch <= 'z' && k < ch - 87 )
                return false;
        }

        return true;
    }
}
