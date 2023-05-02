package 字符串数组集合操作.密钥格式化;


import java.util.Scanner;

/**
 *
 * 题目描述：
 *     给定一个非空字符串S，其被N个'-'分隔成N+1个子串，给定正整数K，
 *     要求除第一个子串外，其余的串每K个用'-'分隔，并将小写字母转换为大写。
 *
 * 输入描述：
 *     正整数K和'-'分隔的字符串，如：
 *     2
 *     25G3C-abc-d
 *
 * 输出描述：
 *     转换后的字符串
 *
 * 用例：
 *     输入：4
 *          5F3Z-2e-9-w
 *     输出：5F3Z-2E9W
 *     说明：字符串S被分成了两个部分，每部分4个字符；注意，两个额外的破折号需要删掉。
 *
 *     输入：2
 *          2-5g-3-J
 *     输出：2-5G-3J
 *     说明：字符串S被分成了3个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，其余部分皆为2个字符。
 *
 */

/**
 * 解题思路：
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

        return "";
    }
}
