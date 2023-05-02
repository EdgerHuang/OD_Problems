package 字符串数组集合操作.免单统计;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * 题目描述：
 *     华为商城举办了一个促销活动，如果某顾客是某一秒内最早时刻下单的顾客(可能是多个人)，则可以获取免单。
 * 请你编程计算有多少顾客可以获取免单。
 *
 * 输入描述：
 *     输入n行数据，每一行表示一位顾客的下单时间
 *     以（年-月-日时-分-秒.毫秒） yyyy-MM-ddHH:mm:ss.fff 形式给出。
 *     0<n<50000
 *     2000<yyyy<2020
 *     0<MM<=12
 *     0<dd<=28
 *     0<=HH<=23
 *     0<=mm<=59
 *     0<=ss<=59
 *     0<=fff<=999
 * 所有输入保证合法
 *
 * 输出描述：
 *     输出一个整数，表示有多少顾客可以获取免单。
 *
 * 用例：
 *     输入： 3
 *           2019-01-01 00:00:00.001
 *           2019-01-01 00:00:00.002
 *           2019-01-01 00:00:00.003
 *     输出：1
 *     说明：在样例1中，三个订单都是同一秒内下单，只有第一个订单最早下单，可以免单。
 *
 *     输入：3
 *           2019-01-01 08:59:00.123
 *           2019-01-01 08:59:00.123
 *           2018-12-28 10:08:00.999
 *     输出：3
 *     说明：样例2中，前两个订单都是同一秒内下单同一时刻(也是最早)下单，都可以免单，第三个订单是当前秒内唯一一个订单
 *         （也是最早），也可免单。
 *
 *     输入：5
 *          2019-01-01 00:00:00.004
 *          2019-01-01 00:00:00.004
 *          2019-01-01 00:00:01.006
 *          2019-01-01 00:00:01.006
 *          2019-01-01 00:00:01.005
 *     输出：3
 *     说明：样例 3 中，前两个订单是同一秒内同一时刻（也是最早）下单，第三第四个订单不是当前秒内最早下单，
 *           不可免单，第五个订单可以免单。
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);

        int num = Integer.parseInt(sc.nextLine());

        String[] originArr = new String[num];

        for (int i = 0; i < num;i++){

            // 因为日和时之间有空格
            originArr[i] = sc.nextLine();

        }

        System.out.println(getResult(originArr,num));

    }

    public static int getResult(String[] originArr,int num){

        HashMap<String, ArrayList<Integer>> map = new HashMap<>();

        for (String str : originArr){
            String tmpStr = str.substring(0,str.length()-4);
            Integer tmpInt = Integer.parseInt(str.substring(str.length()-3));
            map.putIfAbsent(tmpStr,new ArrayList<>());
            map.get(tmpStr).add(tmpInt);
        }

        int count = 0;

        for (String str : map.keySet()){
            map.get(str).sort(Comparator.comparingInt((Integer a)->a));
            if (map.get(str).size() == 1){
                count++;
            }else { // > 1
                Integer tmp = map.get(str).get(0);
                count++;
                for (int i = 1;i < map.get(str).size();i++){
                    if (map.get(str).get(i).equals(tmp)){
                        count++;
                    }
                }
            }
        }

        return count;

    }

}
