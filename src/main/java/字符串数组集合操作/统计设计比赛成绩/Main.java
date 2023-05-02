package 字符串数组集合操作.统计设计比赛成绩;

import java.util.*;

/**
 *
 * 题目描述：
 *     给定一个射击比赛成绩单，包含多个选手若干次射击的成绩分数，请对每个选手按其最高3个分数之和进行降序排名，
 *     输出降序排名后的选手ID序列。
 *
 * 条件如下：
 *     1. 一个选手可以有多个射击成绩的分数，且次序不固定。
 *     2. 如果一个选手成绩少于3个，则认为选手的所有成绩无效，排名忽略该选手。
 *     3. 如果选手的成绩之和相等，则成绩之和相等的选手按照其ID序列降序排列
 *
 * 输入描述：
 *     输入第一行，一个整数N，表示该场比赛总共进行了N次射击，产生N个成绩分数(2 <= N <= 100)
 *     输入第二行，一个长度为N整数序列，表示参与每次射击的选手ID(0 <= ID <=99)
 *     输入第三行，一个长度为N整数序列，表示参与每次射击的选手对应的成绩(0 <= 成绩 <= 100)
 *
 * 输出描述：
 *     符合题设条件的降序排名后的选手ID序列
 *
 * 用例：
 *     输入：13
 *          3,3,7,4,4,4,4,7,7,3,5,5,5
 *          53,80,68,24,39,76,66,16,100,55,53,80,55
 *     输出：5,3,7,4
 *     说明：该场射击比赛进行了13次
 *          参赛的选手为3,4,5,7
 *          3号选手成绩：53,80,55，最高3个成绩的和为：80+55+53=188。
 *          4号选手成绩：24,39,76,66，最高3个成绩的和为：76+66+39=181。
 *          5号选手成绩：53,80,55，最高3个成绩的和为：80+55+53=188。
 *          7号选手成绩：68,16,100，最高3个成绩的和为：100+68+16=184。
 *          比较各个选手最高3个成绩的和，有3号=5号>7号>4号，由于3号和5号成绩相等且ID号5>3，
 *          所以输出为：5,3,7,4
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();

        String idStr = sc.next();

        String scoreStr = sc.next();

        System.out.println(getResult(num,idStr,scoreStr));
    }



    public static String getResult(int num,String idStr,String scoreStr){

        Integer[] idArr = Arrays.stream(idStr.split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        Integer[] scoreArr = Arrays.stream(scoreStr.split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < num; i++){

            Integer tmpId = idArr[i];

            Integer tmpScore = scoreArr[i];

            map.putIfAbsent(tmpId,new ArrayList<Integer>());
            map.get(tmpId).add(tmpScore);
        }

        //
        for (Integer one : map.keySet()){

            map.get(one).sort(Comparator.comparingInt((Integer a) -> a).reversed());

        }

        Integer[] arr = map.keySet().stream().filter(one->map.get(one).size() >= 3).sorted(Comparator.comparingInt((Integer a) -> map.get(a).get(0)+map.get(a).get(1)+map.get(a).get(2)).reversed()
                .thenComparingInt(b->-b))
                .toArray(Integer[]::new);
        StringJoiner joiner = new StringJoiner(",","","");
        for (Integer one : arr){
            joiner.add(String.valueOf(one));
        }
        return joiner.toString();
    }

}
