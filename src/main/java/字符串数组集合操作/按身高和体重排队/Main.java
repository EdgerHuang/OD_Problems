package 字符串数组集合操作.按身高和体重排队;


import java.util.*;

/**
 *
 * 题目描述：
 *     某学校举行运动会，学生们按编号(1,2,3...n)进行标识，现需要按照身高由低到高排列，对身高相同的人，按照体重
 * 由轻到重排列；对于身高和体重都相同的人，维持原有的编号顺序关系，请输出排列后的学生编号。
 *
 * 输入描述：
 *     两个序列，每个序列由n个正整数组成(0 < n <= 100)。第一个序列中的数值代表身高，第二个序列中的数值代表体重。
 *
 * 输出描述：
 *     排列结果，每个数值都是原始序列中的学生编号，编号从1开始
 *
 * 用例：
 *     输入：4
 *           100 100 120 130
 *           40 30 60 50
 *     输出: 2 1 3 4
 *     说明：输出的第一个数字2表示此人原始编号为2，即身高为100，体重为30的这个人。
 *           由于他和编号为1的人身高一样，但是体重更轻，因此要排在1前面。
 *
 *     输入：3
 *          90 110 90
 *          45 60 45
 *     输出：1 3 2
 *     说明：1 和 3 的身高体重都相同，需要按照原有位置关系让1排在3前面，而不是3 1 2。
 *
 *
 */

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int num = Integer.parseInt(sc.nextLine());

        Integer[] heightArr = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        Integer[] weightArr = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);


        System.out.println(getResult(num,heightArr,weightArr));
    }


    public static String getResult(int num,Integer[] heightArr,Integer[] weightArr){

        HashMap<Integer,Integer[]> map = new HashMap<>();

        for (int i = 0; i < num; i++){
            Integer[] arr = new Integer[2];
            arr[0] = heightArr[i];
            arr[1] = weightArr[i];
            map.put(i+1,arr);
        }


       Integer[] newArr = map.keySet().stream().sorted(Comparator.comparing((Integer s)->map.get(s)[0])
                .thenComparing(a->map.get(a)[1])
        ).toArray(Integer[]::new);

        StringJoiner joiner = new StringJoiner(" ","","");

        for (Integer one : newArr){
            joiner.add(String.valueOf(one));
        }

        return joiner.toString();
    }
}
