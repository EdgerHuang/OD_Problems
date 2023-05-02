package 字符串数组集合操作.运维日志排序;


import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 题目描述：
 *     运维工程师采集到某产品线网运行一天产生的日志n条，现需要根据日志时间先后顺序对日志进行排序，
 *     日志时间格式为H:M:S:N。
 *     H表示小时(0~23)
 *     M表示分钟(0~59)
 *     S表示秒(0~59)
 *     N表示毫秒(0~999)
 *     时间可能没有补全，也就是说，01：01：01：001也可能表示为1:1:1.1。
 *
 * 输入描述：
 *     第一行输入一个整数n表示日志条数，1 <= n <= 100000，接下来n行输入n个时间。
 *
 * 输出描述：
 *     按时间升序排序之后的时间，如果有两个时间表示的时间相同，则保持输入顺序。
 *
 * 用例：
 *     输入：2
 *          01:41:8.9
 *          1:1:09.211
 *
 *     输出：1:1:09.211
 *           01:41:8.9
 *
 *     说明：无
 *
 *     输入：3
 *          23:41:08.023
 *          1:1:09.211
 *          08:01:22.0
 *     输出：1:1:09.211
 *          08:01:22.0
 *          23:41:08.023
 *     说明：无
 *
 *     输入：2
 *          22:41:08.023
 *          22:41:08.23
 *
 *     输出：22:41:08.023
 *          22:41:08.23
 *
 *     说明:无
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();

        String[] arr = new String[num];

        for (int i = 0;i < num;i++){
            arr[i] = sc.next();
        }

        getResult(arr);
    }


    public static void getResult(String[] arr){

        ArrayList<String[]> list = new ArrayList<>();

        for (String str : arr){
            String[] newArr = str.split("[:.]");
            list.add(newArr);
        }

       ArrayList<String> resList = list.stream().sorted(
                Comparator.comparing((String[] a)->Integer.parseInt(a[0]))
                        .thenComparing(b->Integer.parseInt(b[1]))
                        .thenComparing(c->Integer.parseInt(c[2]))
                        .thenComparing(d->Integer.parseInt(d[3]))

        )
                .map(one->{
                    StringJoiner joiner = new StringJoiner(":","","");
                    for (int i = 0;i < 3;i++){
                        joiner.add(String.valueOf(one[i]));
                    }
                    String interStr = joiner.toString();
                    return interStr + "." + one[3];
                }).collect(Collectors.toCollection(ArrayList<String>::new));

        for (String str : resList){
            System.out.println(str);
        }

    }

    public static String[] getIntArr(String originStr){

        String[] arr = (originStr.split("[:.]"));

        return arr;
    }
}
