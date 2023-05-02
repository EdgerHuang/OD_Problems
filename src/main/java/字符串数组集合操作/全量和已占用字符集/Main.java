package 字符串数组集合操作.全量和已占用字符集;

/**
 * 题目描述：
 *     给定两个字符集合，一个是全量字符集，一个是已占用字符集，已占用字符集中的字符不能再使用。
 *     要求shuchu可用字符集
 *
 * 输入描述：
 *     1. 输入一个字符串 一定包含@，@前为全量字符集，@后的为已占用字符集
 *     2. 已占用字符集中的字符一定是全量字符集中的字符
 *     3. 字符集中的字符跟字符之间使用英文逗号隔开
 *     4. 每个字符都表示为字符+数字的形式用英文冒号分隔，比如a:1标识一个a字符
 *     5. 字符只考虑英文字母，区分大小写
 *     6. 数字只考虑正整型 不超过100
 *     7. 如果一个字符都没被占用 @标识扔存在，例如a:3,b:5,c:2@
 *
 * 输出描述：
 *     输出可用的字符集
 *     不同的输出字符集之间用回车换行
 *     注意 输出的字符顺序要跟输入的一致，如下面用例不能输出b:3,a:2,c:2
 *     如果某个字符已全部占用 则不需要再输出
 *
 * 用例：
 *     输入：a:3,b:5,c:2@a:1,b:2
 *     输出：a:2,b:3,c:2
 *     说明： 全量字符集为3个a,5个b,2个c
 *            已占用字符集为1个a,2个b
 *            由于已占用字符不能再使用
 *            因此剩余可用字符为2个a,3个b,2个c
 *            因此输出a:2,b:3,c:2
 */

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 解题思路：
 *     可以使用HashMap来对字符和字符的数量进行存储，并随着占用字符集中的对应字符的数量，对相应的字符进行数量的相减
 *
 *
 *
 */


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String originStr = sc.next();

        System.out.println(getResult(originStr));
    }


    public static String getResult(String originStr){

        String[] arr = originStr.split("@");

        if (arr.length == 1){
            return arr[0];
        }
        String front = arr[0];

        // 有可能为""
        String end = arr[1];


        String[] frontArr = front.split(",");

        String[] endArr = end.split(",");

        HashMap<Character,Integer> frontMap = new HashMap<>();

        HashMap<Character,Integer> endMap = new HashMap<>();

        // 因为没有重复的字符，所以不需要是put默认的0，然后再加一的操作
        for (String str:frontArr){ // 存放全量字符
            frontMap.put(str.charAt(0),Integer.parseInt(String.valueOf(str.charAt(2))));
        }

        for (String str:endArr){ // 存放占用字符
            endMap.put(str.charAt(0),Integer.parseInt(String.valueOf(str.charAt(2))));
        }

        StringJoiner joiner = new StringJoiner(",","","");

        for (int i = 0; i < frontArr.length; i++){
            char ch = frontArr[i].charAt(0);
            // 对全量frontMap中的字符有对应的相减
            frontMap.put(ch,frontMap.get(ch) - endMap.getOrDefault(ch,0));

            String tmp = ch + ":" + frontMap.get(ch);
            joiner.add(tmp);
        }

        return joiner.toString();

    }
}
