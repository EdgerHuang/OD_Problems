package 字符串数组集合操作.字符统计与重排;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * 题目描述：
 *     给出一个仅包含字母的字符串，不包含空格，统计字符串中各个字母(区分大小写)出现的次数，
 * 并按照字母出现次数从大到小的顺序。输出各个字母及其出现次数。
 * 如果次数相同，按照自然顺序进行排序，且小写字母在大写字母之前。
 *
 * 输入描述：
 *     输入一行，为一个仅包含字母的字符串。
 *
 * 输出描述：
 *     按照字母出现次数从大到小的顺序输出各个字母和字母次数，用英文分号分隔，注意末尾的分号；
 *     字母和次数间用英文冒号分隔。
 *
 * 用例：
 *     输入：xyxyXX
 *     输出：x:2;y:2;X:2;
 *     说明：无
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

        HashMap<Character,Integer> map = new HashMap<>();

        for (char ch : originStr.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

       String[] resArr = map.keySet().stream().sorted(Comparator.comparing((Character ch)->-map.get(ch))
                       .thenComparing(a->!Character.isLowerCase(a)))
                .map(one->one + ":" + map.get(one) + ";")
                .toArray(String[]::new);

        StringBuilder sb = new StringBuilder();

        for (String str : resArr){
            sb.append(str);
        }
        return sb.toString();

    }
}
