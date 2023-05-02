package 字符串数组集合操作.字符串排序;


import java.util.*;

/**
 *
 * 题目描述：
 *     排序规则：
 *         1.单词中字母比较不区分大小写，两个单词先以第一个字母作为排序的基准，如果第一个字母相同，就用第二个字母。
 *         为基准，如果第二个字母相同就以第三个字母为基准，以此类推，如果遇到某个字母不相同，字母顺序在前的那个单词
 *         顺序在前。
 *         2. 当一个短单词和一个长单词的开头部分相同(即短单词是长单词从首字母开始的一部分)，短单词顺序在前。
 *         3. 字母大小写不同的相同单词，只输出一次。
 *
 * 输入描述：
 *     无
 *
 * 输出描述：
 *     无
 *
 * 用例：
 *     输入：Hello hello word
 *     输出：Hello world
 *     说明：无
 *
 *     输入：i LOVE Cc I love CC Hello Hel Hellow
 *     输出：Cc Hel Hello Hellow i LOVE
 *     说明：无
 *
 *
 */

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String originStr = sc.nextLine();

        System.out.println(getResult(originStr));

    }


    // 说一下Comparator.comparing()方法，是为了将这个该方法接受一个函数进行比较
    // 如果我们使用lambda表达式进行书写时,给定一个参数->{}，其实本质上是自定义了一个函数

    // 而如果我们使用的是String::method，这种形式
    public static String getResult(String originStr){

       String[] newArr = Arrays.stream(originStr.split(" ")).sorted(Comparator.comparing(String::toString,String::compareToIgnoreCase)).toArray(String[]::new);

       ArrayList<String> list = new ArrayList<>();

       for (String str : newArr){

           boolean isContains = false;
           for (String one : list){
                if (str.compareToIgnoreCase(one) == 0){
                    isContains = true;
                }
           }
           if (!isContains){
               list.add(str);
           }

       }

       StringJoiner joiner = new StringJoiner(" ","","");

       for (String str : list){
           joiner.add(str);
       }
       return joiner.toString();

    }

}
