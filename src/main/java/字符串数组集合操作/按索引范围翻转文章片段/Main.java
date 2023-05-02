package 字符串数组集合操作.按索引范围翻转文章片段;


import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 *
 * 题目描述：
 *     输入一个英文文章片段，翻转指定区间的单词顺序，标点符号和普通字母一样处理。
 * 例如 输入字符串"I am a developer."， 区间[0,3],则输出"developer. a am I"。
 *
 * 输入描述：
 *     使用换行隔开三个参数
 *     第一个参数为英文文章内容即英文字符串
 *     第二个参数为翻转起始单词下标(下标从0开始)
 *     第三个参数为结束单词下标
 *
 * 输出描述
 *     反转后的英文文章片段所有单词之间以一个半角空格分隔进行输出。
 *
 * 用例：
 *     输入： I am a developer
 *            1
 *            2
 *
 *     输出： I a am developer
 *
 *     说明： 无
 *
 *     输入：     hello world!
 *           0
 *           1
 *     输出：world! hello
 *     说明：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括
 *
 *     输入：I    am    a    developer.
 *           0
 *           3
 *     输出：developer. a am I
 *     说明：如果连个单词间有多余的空格，将反转后单词间的空格减少到只含有一个。
 *
 *
 *     输入：Hello!
 *           0
 *           3
 *     输出：EMPTY
 *     说明：指定反转区间只有一个单词，或无有效单词则统一输出EMPTY
 *
 *
 *
 */
public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String originStr = sc.nextLine();

        int startIndex = sc.nextInt();

        int endIndex = sc.nextInt();

        System.out.println(getResult(originStr,startIndex,endIndex));

    }

    
    public static String getResult(String originStr, int startIndex, int endIndex){

        // 正则表达式的reg = "\s+",\s表示可以匹配任意的空格，制表符，换行符
        String[] arr = originStr.split("\s+");


        // 对输入索引做范围判定
        // 首先startIndex <= endIndex,其次，startIndex[0,arr.length) endIndex < arr.length
        if (startIndex > endIndex || startIndex < 0 || startIndex >= arr.length  || endIndex >= arr.length){
            return "EMPTY";
        }

        arr = Arrays.stream(arr).filter(str->!"".equals(str)).toArray(String[]::new);

        StringJoiner joiner = new StringJoiner(" ","","");

        int i = startIndex;
        int j = endIndex;

        while (i <= j){
            String tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }

        for (String str:arr){
            joiner.add(str);
        }

        return joiner.toString();

    }

}
