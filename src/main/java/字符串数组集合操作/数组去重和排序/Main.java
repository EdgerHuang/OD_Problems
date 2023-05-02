package 字符串数组集合操作.数组去重和排序;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 题目描述：
 *     给定一个乱序的数组，删除所有的重复元素，使得每个元素只出现一次，并且按照出现的次数从高到低进行排序，
 * 相同出现次数按照第一次出现顺序进行先后排序。
 *
 * 输入描述：
 *     一个数组
 *
 * 输出描述：
 *     去重排序后的数组
 *
 * 用例：
 *     输入：1,3,3,3,2,4,4,4,5
 *     输出：3,4,1,2,5
 *     备注：数组大小不超过100数组元素值大小不超过100。
 *
 *
 */

public class Main{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String originStr = sc.next();

        System.out.println(getResult(originStr));

    }

    public static String getResult(String originStr) {
        Integer[] arr = Arrays.stream(originStr.split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        HashMap<Integer,Integer> map = new HashMap<>();

        for (Integer one : arr){
            map.put(one,map.getOrDefault(one,0)+1);
        }

        Integer[] newArr = map.keySet().stream().sorted((a,b)-> !map.get(a).equals(map.get(b)) ? map.get(b) - map.get(a) : originStr.indexOf(String.valueOf(a)) - originStr.indexOf(String.valueOf(b)))
                .toArray(Integer[]::new);

        StringJoiner joiner = new StringJoiner(",");

        for (Integer one:newArr){
            joiner.add(String.valueOf(one));
        }

        return joiner.toString();

    }
}
