package 字符串数组集合操作.字符串分割;

/**
 * 题目描述：
 *    给定一个非空字符串S，其被N个‘-’分隔成N+1个子串，给定正整数K，要求除第一个子串外，其余的子串每K个字符组成新的子串，并用‘-’分隔。
 *    对于新组成的每一个子串，如果它含有的小写字母比大写字母多，则将这个子串的所有大写字母转换为小写字母；
 *    反之，如果它含有的大写字母比小写字母多，则将这个子串的所有小写字母转换为大写字母；大小写字母的数量相等时，不做转换。
 *
 * 输入描述：
 *     输入为两行，第一行为参数K, 第二行为字符串S
 *
 * 输出描述：
 *     输出转换后的字符串
 *
 *     输入:  3
 *           12abc-abCABc-4aB@
 *
 *     输出: 12abc-abc-ABC-4aB-@
 *
 *     说明子串为12abc、abCABc、4aB@，第一个子串保留，后面的子串每3个字符一组为abC、ABc、4aB、@，abC中小写字母较多，转换为abc，
 *     ABc中大写字母较多，转换为ABC，4aB中大小写字母都为1个，不做转换，@中没有字母，连起来即12abc-abc-ABC-4aB-@
 *
 *     输入: 12
 *          12abc-abCABc-4aB@
 *     输出  12abc-abCABc4aB@
 *
 *     说明 子串为12abc、abCABc、4aB@，第一个子串保留，后面的子串每12个字符一组为abCABc4aB@，这个子串中大小写字母都为4个，不做转换，连起来即12abc-abCABc4aB@
 *
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 *  解题思路：
 *      首先，我们看到字符串S被N个"-"分隔成N+1个子字符串，说明字符"-"不是在字符串的首尾存在，这是个细节
 *      可以使用ArrayList<String> 来存储结果，然后使用三目运算符来map比较
 *
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        String str = sc.next();

        System.out.println(getResult(str,k));

    }

    public static String getResult(String str,int k){

        String[] arr = str.split("-");

        if (arr.length == 1)
            return arr[0];

        StringBuilder sb = new StringBuilder();

        for (int i = 1;i < arr.length;i++){
            sb.append(arr[i]);
        }
        String oneStr = sb.toString();
        ArrayList<String> res = new ArrayList<>();
        res.add(arr[0]);
        recursion(res,oneStr,k);


       List<String> res2 =   res.stream().map(one->{
            int lowCun = getLowerCaseLetterNum(one);
            int upCun = getUpperCaseLetterNum(one);
            return lowCun != upCun ? lowCun > upCun ? one.toLowerCase() : one.toUpperCase() : one;

        }).toList();

        StringJoiner joiner = new StringJoiner("-","","");

        for (String one:res2){
            joiner.add(one);
        }

        String s = "";

        return joiner.toString();
    }

    // 使用递归函数来将字符串进行划分

    /**
     *
     * @param list 将按照k长度划分的字符串装进去
     * @param str  待划分字符串
     * @param k    截取字符串的长度
     */
    public static void recursion(ArrayList<String> list,String str,int k){

        if (str.length() <= k){
            list.add(str);
            return;
        }

        String tmp = str.substring(0,k);
        list.add(tmp);
        str = str.substring(k);

        recursion(list,str,k);
    }


    public static int getLowerCaseLetterNum(String str){

        int count= 0;
        for (char ch:str.toCharArray()){
            if (Character.isLowerCase(ch))
                count++;
        }
        return count;
    }

    public static int getUpperCaseLetterNum(String str){

        int count= 0;
        for (char ch:str.toCharArray()){
            if (Character.isUpperCase(ch))
                count++;
        }
        return count;
    }
}
