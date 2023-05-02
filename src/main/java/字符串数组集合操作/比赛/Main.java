package 字符串数组集合操作.比赛;

/**
 *
 * 题目描述：
 *     一个有N个选手参加比赛，选手编号为1~N(3 <= N <= 100)，有M(3 <= M <= 10)个评委对选手进行打分。
 *     打分规则为每个评委对选手打分，最高分10分，最低分1分。
 *     请计算得分最多的3位选手的编号。
 *     如果得分相同，则得分高分值最多的选手排名靠前
 *     (10分数量相同 ，则比较9分的数量，以此类推，用例中不会出现多个选手得分完全相同的情况)。
 *
 * 输入描述：
 *     第一行为半角逗号分隔的两个正整数，第一个数字表示M(3 <= M <= 10)个评委，第二个数字表示N(3 <= N <=100)个评委对选手进行打分。
 *     第2到M+1行是半角逗号分割的整数序列，表示评委为每个选手的打分，0号下标数字表示1号选手分数，1号下标数字表示2号选手分数，依次类推
 *
 * 输出描述：
 *     选手前3名的编号。
 *     注：若输入异常，输出-1，如M，N 打分不在范围内。
 *
 *
 * 用例：
 *     输入： 4,5
 *           10,6,9,7,6
 *           9,10,6,7,5
 *           8,10,6,5,10
 *           9,10,8,4,9
 *
 *     输出： 2,1,5
 *
 *     说明：第一行代表有4个评委，5个选手参加比赛
 *           矩阵代表是4*5，每个数字是选手的编号，每一行代表一个评委对选手的打分排序，
 *           2号选手得分36分排第1，1号选手36分排第2，5号选手30分(2号10分值有3个，1号10分值只有1个，所以2号排第一)
 *
 *     输入：2,5
 *          7,3,5,4,2
 *          8,5,4,4,3
 *
 *     输出：-1
 *
 *     说明：只有2个评委，要求最少为3个评委
 *
 *     输入：4,2
 *          8,5
 *          5,6
 *          10,4
 *          8,9
 *     输出：-1
 *     说明：只有2名选手参加，要求最少为3名
 *
 *
 *     输入：4,5
 *          11,6,9,7,8
 *          9,10,6,7,8
 *          8,10,6,9,7
 *          9,10,8,6,7
 *     输出：-1
 *     说明：第一个评委给第一个选手打分11，无效分数
 *
 *
 */

import java.util.*;
import java.util.jar.JarEntry;

/**
 *
 * 解题思路：
 *     这道题首先是对评委数量和选手数量的判断，然后是对评委打分的判断
 *     其次是对于是
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int judgeNum = sc.nextInt();

        int playerNum = sc.nextInt();

        Integer[][] arr = new Integer[judgeNum][];

        for (int i = 0;i < judgeNum;i++){
            arr[i] = Arrays.stream(sc.next().split(",")).map(Integer::parseInt).toArray(Integer[]::new);
        }
        System.out.println(getResult(judgeNum,playerNum,arr));
    }


    public static String getResult(int judgeNum,int playerNum, Integer[][] arr){

        // 合法性的判断
        if (!isValid(judgeNum,playerNum,arr)){
            return "-1";
        }

        // 将选手的编号，和选手的总分，以及选手的各个评委的打分进行map
        HashMap<Integer,Integer[]> map = new HashMap<>();

        // 列优先进行遍历
        for (int i = 0; i < playerNum; i++){
            // 因为还要保存一个总数 key->i+1
            Integer[] tmpArr = new Integer[judgeNum + 1];
            int total = 0;
            for (int j = 0; j < judgeNum; j++){
                tmpArr[j+1] = arr[j][i];
                total += arr[j][i];
            }
            tmpArr[0] = total;
            map.put(i+1,tmpArr);
        }

        Integer[] res = map.keySet().stream().sorted(Comparator.comparing((Integer s)->-map.get(s)[0])
                .thenComparing((a,b)->{
                    for (int i = 10;i >= 1;i--){
                        if (countNum(map.get(a),i) == countNum(map.get(b),i))
                            continue;
                         return countNum(map.get(b),i) - countNum(map.get(a),i);
                    }
                    return 0;
                })
        ).limit(3).toArray(Integer[]::new);

        StringJoiner joiner = new StringJoiner(",","","");

        for (Integer one : res){
            joiner.add(String.valueOf(one));
        }

        return joiner.toString();

    }


    // 对数据合法性的判定
    public static boolean isValid(int judgeNum,int playerNum, Integer[][] arr){

        // 对评委数量和选手数量进行判断
        if (judgeNum < 3 || playerNum < 3)
            return false;

        // 对评委的评分进行判断

        for (int i = 0;i < judgeNum;i++){
            for (int j = 0;j < playerNum;j++){
                if (arr[i][j] > 10 || arr[i][j] < 1){
                    return false;
                }
            }
        }

        return true;
    }


    // 对于特定数字[1-10]数量的判断
    public static int countNum(Integer[] oneScore,int num) {

        int count = 0;
        for (Integer one : oneScore){
            if (one.equals(num)){
                count++;
            }
        }
        return count;
    }
}
