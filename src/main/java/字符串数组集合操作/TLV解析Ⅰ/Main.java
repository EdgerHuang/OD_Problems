package 字符串数组集合操作.TLV解析Ⅰ;

/**
 *
 * 题目描述:
 *    TLV编码是按[Tag Length Value]格式进行编码的，一段码流中的信元用Tag标识，Tag在码流中唯一不重复，Length表示信元Value的长度，Value表示信元的值。
 *    码流以某信元的Tag开头，Tag固定占一个字节，Length固定占两个字节，字节序为小端序
 *    现给定TLV格式编码的码流，以及需要解码的信元Ta\g，请输出该信元的Value。
 *    输入码流的16进制字符中，不包括小写字母，且要求输出的16进制字符串中也不要包含小写字母；码流字符串的最大长度不超过50000个字节。
 *
 * 输入描述:
 *     输入的第一行为一个字符串，表示待解码信元的Tag；输入的第二行为一个字符串，表示待解码的16进制码流，字节之间用空格分隔。
 *
 * 输出描述:
 *    输出一个字符串，表示待解码信元以16进制表示的Value。
 *
 * 用例
 *      输入  31
 *           32 01 00 AE 90 02 00 01 02 30 03 00 AB 32 31 31 02 00 32 33 33 01 00 CC
 *      输出  32 33
 *
 * 说明 需要解析的信元的Tag是31，从码流的起始处开始匹配，
 *      第一个信元的Tag是32，信元长度为1（01 00，小端序表示为1）；
 *      第二个信元的Tag是90，其长度为2；
 *      第三个信元的Tag是30，其长度为3；
 *      第四个信元的Tag是31，其长度为2（02 00），
 *      所以返回长度后面的两个字节即可，即32 33。
 *
 */

import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 解题思路：
 *    可以使用递归来进行题解
 *
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String targetTag = sc.nextLine();

        String originStr = sc.nextLine();

        System.out.println(getResult(targetTag,originStr));


    }

    public static String getResult(String targetTag,String originStr){

        List<String> list = Arrays.asList(originStr.split(" "));


       ArrayList<String> listw = new ArrayList<>(list);
        String res = "";

        res = recursion(listw,0,targetTag,res);

        return res;
    }


    /**
     *
     * @param list 用来保存原始的码流数组，不会随递归的次数而改变
     * @param res  用来保存最后的结果字符串，初始为""
     * @param tagIndex 用来记录下一次递归时候的tag在list中的索引位置
     * @param targetTag 目标tag值，每次用来比较和遍历得到的tag值是否一致
     * @return 返回目标tag所对应的value字符串
     */

    // 一定要注意小端的存储
    public static String recursion(ArrayList<String> list, int tagIndex, String targetTag, String res){

        String currentTag = list.get(tagIndex);
        if (currentTag.equals(targetTag)){
            String lengthStr = list.get(tagIndex+2) + list.get(tagIndex+1);

            int length = Integer.parseInt(lengthStr,16);


            StringJoiner joiner = new StringJoiner(" ","","");

            for (int i = 1;i <= length;i++){
                joiner.add(list.get(i+tagIndex+2));
            }
            res = joiner.toString();
            return res;
        }else {
            String lengthStr = list.get(tagIndex+2) + list.get(tagIndex+1);

            int length = Integer.parseInt(lengthStr,16);

            int newTagIndex = tagIndex + 2 + length + 1;

            res = recursion(list,newTagIndex,targetTag,res);
        }

        return res;
    }

}
