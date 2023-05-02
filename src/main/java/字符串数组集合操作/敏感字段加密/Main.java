package 字符串数组集合操作.敏感字段加密;

/**
 *  给定一个由多个命令字组成的命令字符串：
 *     1、字符串长度小于等于127字节，只包含大小写字母，数字，下划线和偶数个双引号；
 *
 *     2、命令字之间以一个或多个下划线_进行分割；
 *
 *     3、可以通过两个双引号””来标识包含下划线_的命令字或空命令字（仅包含两个双引号的命令字），双引号不会在命令字内部出现；
 *
 * 请对指定索引的敏感字段进行加密，替换为******（6个*），并删除命令字前后多余的下划线_。
 *
 * 如果无法找到指定索引的命令字，输出字符串ERROR。
 *
 * 输入描述:
 *     输入为两行，第一行为命令字索引K（从0开始），第二行为命令字符串S。
 *
 * 输出描述：
 *     输出处理后的命令字符串，如果无法找到指定索引的命令字，输出字符串ERROR
 *
 * 用例
 *
 *     输入     1
 *             password__a12345678_timeout_100
 *
 *     输出    password_******_timeout_100
 *
 *     说明    无
 *
 *
 *     输入     2
 *             aaa_password_"a12_45678"_timeout__100_""_
 *
 *     输出    aaa_password_******_timeout_100_""
 *
 *     说明    无
 *
 *
 */


import java.util.*;

/**
 * 解题思路描述：
 *      最容易想到的是根据正则对字符串进行拆分，从而形成字符串数组String[] ,但是因为在“”中也包含有_，所以很难根据_或者“对字符串进行拆分
 *
 *      所以要借用其它的数据结构来进行处理
 *      这里我们可以使用栈来进行处理，遇见普通的字符(大小写字母，数字)直接进行压栈，遇见_则将栈中的元素全部弹栈，并汇集成一个字符串，遇见“则将
 *      其进行压栈，其后续的所有字符也都进行压栈处理，直到遇见下一个"字符，则依次进行弹栈，直到遇到栈中的那一个",又可以汇总成一个字符串，依次类推
 *
 *
 *
 * 总结：
 *      其实对于字符串的处理，尤其是需要进行拆分处理的时候，首先可能会想到split()使用正则进行拆分，但是如果正则难以构造，可能就要考虑使用其它数据结构
 *      比如经常使用的栈
 *
 */

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int index = sc.nextInt();

        String originStr = sc.next();

        System.out.println(getResult(originStr,index));
    }

    public static String getResult(String originStr,int index){

        LinkedList<String> stack = new LinkedList<>();

        ArrayList<String> list = new ArrayList<>();

        for (char ch:originStr.toCharArray()){

            String oneCh = String.valueOf(ch);

            if (oneCh.equals("_") && !stack.contains("\"")){

                 // 清空栈中的元素
                StringBuilder s = new StringBuilder();

                // 为了保证是在栈中有元素的时候进行追加
                if (stack.size() > 0){
                    while (stack.size() > 0){
                        String temp = stack.pop();
                        s.append(temp);
                    }
                    list.add(s.reverse().toString());
                }

            }else if (oneCh.equals("\"") && !stack.contains("\"")){
                    stack.push(oneCh);
            }else if (oneCh.equals("\"") && stack.contains("\"")){ //

                  StringBuilder sb = new StringBuilder();

                  String r;

                  // 因为是要把"也要加上
                  sb.append(oneCh);
                  while (!(r = stack.pop()).equals("\"")){
                      sb.append(r);
                  }
                  sb.append(r);
                  list.add(sb.reverse().toString());
            }else { // 大小写字母, 数字这种普通字符 以及是_字符，但是栈中包含有"元素的
                 stack.push(oneCh);
            }
        }

        StringBuilder sb = new StringBuilder();

        if (stack.size() > 0){
            while (stack.size() > 0){
                String s = stack.pop();
                sb.append(s);
            }
        }
        if (sb.length() > 0){
            list.add(sb.reverse().toString());
        }

        String[] arr = list.toArray(String[]::new);

        System.out.println(Arrays.toString(arr));

        if (index < 0 || index >= list.size())
            return "ERROR";

        list.set(index,"******");

        StringJoiner joiner = new StringJoiner("_","","");

        for (String str:list){
            joiner.add(str);
        }
       return joiner.toString();
    }

}
