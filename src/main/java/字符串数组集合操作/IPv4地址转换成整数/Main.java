package 字符串数组集合操作.IPv4地址转换成整数;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 题目描述：
 *  存在一种虚拟IPv4地址，由4小节组成，每节的范围为0~255，以#号间隔，虚拟IPv4地址可以转换为一个32位的整数，
 * 例如：
 *     128#0#255#255，转换为32位整数的结果为2147549183（0x8000FFFF）
 *     1#0#0#0，转换为32位整数的结果为16777216（0x01000000）
 *     现以字符串形式给出一个虚拟IPv4地址，限制第1小节的范围为1~128，即每一节范围分别为(1~128)#(0~255)#(0~255)#(0~255)，
 *     要求每个IPv4地址只能对应到唯一的整数上。如果是非法IPv4，返回invalid IP
 *
 * 输入描述：
 *     输入一行，虚拟IPv4地址格式字符串
 *
 * 输出描述：
 *     输出一行，按照要求输出整型或者特定字符
 *
 * 用例：
 *
 *     输入：100#101#1#5
 *     输出：1684340997
 *     说明：无
 *
 *     输入：1#2#3
 *     输出：invalid IP
 *     说明：无
 *
 *
 *
 */

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String ipAddr = sc.next();

        System.out.println(getResult(ipAddr));
    }

    public static String getResult(String ipAddr){
       Integer[] arr = Arrays.stream(ipAddr.split("#")).map(Integer::parseInt).toArray(Integer[]::new);

       if (!isValid(arr))
           return "invalid IP";

        StringBuilder sb = new StringBuilder();

       for (Integer one:arr){

           String tmpStr = Integer.toBinaryString(one);

           // 补足长度
           int conlelen = 8 - tmpStr.length();

           for (int i = 0;i < conlelen;i++){
               sb.append(0);
           }
           sb.append(tmpStr);
       }

       long res = Integer.parseInt(sb.toString(),2);
       return String.valueOf(res);
    }


    public static boolean isValid(Integer[] arr){

        if (arr.length != 4){
            return false;
        }else {
            for (int i = 0;i < 4;i++){
                if (i == 0){
                    if (arr[0] < 1 || arr[0] > 128){
                        return false;
                    }
                }
                if (arr[i] < 0 || arr[i] > 255){
                    return false;
                }
            }
        }
        return true;
    }
}
