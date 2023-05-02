package 字符串数组集合操作.整型数组按个位值排序;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 *
 * 题目描述：
 *     给定一个非空数组(列表)，其元素数据类型为整型，请按照数组元素十进制最低位从小到大进行排序，
 * 十进制最低位相同的元素，相对位置保持不变。
 *
 *     当数组元素为负值时，十进制最低位等同于去除符号位后对应十进制最低位。
 *
 * 输入描述：
 *     给定一个非空数组，其元素数据类型为32位有符号整数，数组长度[1,1000]
 *
 * 输出描述：
 *     输出排序后的数组
 *
 *
 * 用例:1,2,5,-21,22,11,55,-101,42,8,7,32
 *     输入：
 *     输出：1,-21,11,-101,2,22,42,32,5,55,7,8
 *     说明：无
 *
 */

// 可以自己定制化一个比较器
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String originStr = sc.next();

        System.out.println(getResult(originStr));

    }

    public static String getResult(String originStr){

        Integer[] arr = Arrays.stream(originStr.split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        arr = Arrays.stream(arr).sorted(Comparator.comparing(a->-Math.abs(a % 10))).toArray(Integer[]::new);
//        arr = Arrays.stream(arr).sorted((a,b)->{
//            int one = Math.abs(a % 10);
//            int two = Math.abs(b % 10);
//            return one -two;
//        }).toArray(Integer[]::new);
//
        StringJoiner joiner = new StringJoiner(",","","");

        for (Integer one:arr){
            joiner.add(String.valueOf(one));
        }

        return joiner.toString();
    }
}
