package 字符串数组集合操作.英文输入法;


/**
 *
 * 题目描述：
 *     主管期望你来实现英文输入法单词联想功能
 *
 * 需求如下：
 *     依据用户输入的单词前缀，从已输入的英文语句中联想出用户想输入的单词，按字典序输出联想到的单词序列
 *     如果联想不到，请输出用户输入的单词前缀。
 *
 * 注意：
 *     1. 英文单词联想时，区分大小写
 *     2. 缩略形式如"don't", 判定为两个单词，“don”和“t”
 *     3. 输出的单词序列，不能有重复的单词，且只能是英文单词，不能有标点符号
 *
 * 输入描述：
 *     输入为两行
 *     首行输入一段由英文单词word和标点符合组成的语句str；
 *     接下来一行为一个英文单词前缀pre
 *     0 < word.length() <= 20
 *     0 < str.length() <= 10000
 *     0 < pre <= 20
 *
 * 输出描述：
 *     输出符合要求的单词序列或者单词前缀，存在多个时，单词之间以单个空格分割
 *
 * 用例：
 *     输入：I love you
 *           He
 *     输出：
 *           He
 *     说明： 从用户已输入英文语句"I love you"中提炼出"I","love","you"三个单词，接下来用户输入"He"
 *            从已输入信息中无法联想到任何符合要求的单词，因此输出用户输入的单词前缀
 *
 *
 *     输入：The furthest distance in the world, Is not between life and death, But when I stand in front
 *           you,Yet you don't know that I love you.
 *           f
 *     输出：front furthest
 *
 *     说明：从用户已输入英文语句”The furthest distance in the world, Is not between life and death,
 *     But when I stand in front of you, Yet you dont know that I love you.”
 *     中提炼出的单词，符合“f”作为前缀的，有“furthest”和“front”，
 *     按字典序排序并在单词间添加空格后输出，结果为“front furthest”。
 *
 *
 */


import javax.swing.text.TabExpander;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 解题思路：
 *     这题目不是很难，首先我们可以使用split来将用户输入的语句进行分割为String[]，这里的正则匹配的是单个字符
 *     单个字符包括有','   '.'   ' '   ''' 这四种字符
 *
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String originStr = sc.nextLine();

        String pre = sc.next();

        System.out.println(getResult(originStr,pre));

    }

    public static String getResult(String originStr, String pre){

        // 匹配单个字符
        String[] arr = originStr.split("[,. ']");


        String[] temArr = Arrays.stream(arr).filter(str->!"".equals(str) && str.startsWith(pre)).toArray(String[]::new);

        HashSet<String> set = new HashSet<>(Arrays.asList(temArr));

       ArrayList<String> list = set.stream().sorted(String::compareTo).collect(Collectors.toCollection(ArrayList<String>::new));

       if (list.size() == 0)
           return pre;


        StringJoiner joiner = new StringJoiner(" ","","");

        for (String str:list){
            joiner.add(str);
        }
        return joiner.toString();
    }


}
