package 字符串数组集合操作.单词接龙;


import java.io.StringReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * 题目描述：
 *
 *   单词接龙的规则是：
 *     ·可用于接龙的单词首字母必须要前一个单词的尾字母相同；
 *     ·当存在多个首字母相同的单词时，取长度最长的单词，如果长度也相等，则取字典序最小的单词；已经参与接龙的单词不能重复使用。
 *     ·现给定一组全部由小写字母组成单词数组，并指定其中的一个单词作为起始单词，进行单词接龙。
 *     ·请输出最长的单词串，单词串是单词拼接而成，中间没有空格。
 *
 * 输入描述：
 *
 *     输入的第一行为一个非负整数，表示起始单词在数组中的索引K，0 <= K < N ；
 *     输入的第二行为一个非负整数，表示单词的个数N；
 *     接下来的N行，分别表示单词数组中的单词。
 *
 * 备注：
 *     单词个数N的取值范围是[1,20]；
 *     每个单词的长度的取值范围是[1,30];
 *
 * 输出描述：
 *     输出一个字符串，表示最终拼接的单词串。
 *
 *
 * 用例：
 *
 *     输入：0
 *          6
 *          word
 *          dd
 *          da
 *          dc
 *          dword
 *          d
 *     输出：worddwordda
 *
 *     说明：先确定起始单词word，再接以d开头的且长度最长的单词dword，剩余以d开头且长度最长的有dd、da、dc，则取字典序最小的da，所以最后输出worddwordda。
 *
 *
 *     输入：4
 *          6
 *          word
 *          dd
 *          da
 *          dc
 *          dword
 *          d
 *     输出：dwordda
 *     说明：先确定起始单词dword，剩余以d开头且长度最长的有dd、da、dc，则取字典序最小的da，所以最后输出dwordda。
 *
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();

        int n = sc.nextInt();

        String[] arr = new String[n];

        for(int i = 0;i < n;i++){
            arr[i] = sc.next();
        }

        System.out.println(getResult(k,n,arr));

    }

    public static String getResult(int k, int n, String[] arr){

        // 这里不需要考虑k不合法的情况

        String firstStr = arr[k];

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0;i < n;i++){
            if (i != k){
                list.add(arr[i]);
            }
        }
        // 第一个字符串的最后一个字符
        Character ch = firstStr.charAt(firstStr.length() - 1);

        String resStr = arr[k];

        resStr = recursion(ch,list,resStr);
        return resStr;
    }

    /**
     *
     * @param ch  每次需要再list中找的字符
     * @param list list用于每次对剩余的字符进行排序，然后删除最开始的那个字符后继续供查找
     * @param resStr 结果字符串，用于拼接
     * @return       返回最深处的递归得到的字符串
     */
    public static String recursion(Character ch,ArrayList<String> list,String resStr){

        // 对list进行排序
        list = list.stream()
                .sorted(Comparator.comparing((String s) -> s.charAt(0) != ch)
                        .thenComparing((a,b)->b.length() - a.length())
                        .thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toCollection(ArrayList<String>::new));

        // 这里还是有点复杂
        // 首先我们要考虑list.size() > 0 (因为会删持续除元素，导致list减小)
        // 其次，有可能排序后，虽然list有值，但是呢，没有以特定字母开始的元素，
        if (list.size() > 0){
            String one = "";
            one = list.get(0);
            if (one.charAt(0) != ch){ // 有值但是接不上了
                return resStr;
            }

            // 续上
            resStr += one;

            // 移除元素
            list.remove(0);

            Character newCh = one.charAt(one.length() - 1);

           resStr = recursion(newCh,list,resStr);

        }

        return resStr;
    }

}
