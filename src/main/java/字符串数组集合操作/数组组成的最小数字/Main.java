package 字符串数组集合操作.数组组成的最小数字;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * 题目描述：
 *     给定一个整型数组，请从该数组中选择3个元素组成最小数字并输出
 *     (如果数组长度小于3，则选择数组中所有元素来组成最小数字)。
 *
 * 输入描述：
 *     一行用半角逗号分隔的字符串记录的整型数组，0 < 数组长度 <= 100， 0 < 整数的取值范围 <=10000
 *
 * 输出描述：
 *     由三个元素组成的最小数字，如果数组长度小于3，则选择数组中所有元素来组成最小数字。
 *
 * 用例：
 *     输入：21,30,62,5,31
 *     输出：21305
 *     说明：数组长度超过3，需要选择3个元素组成最小数字，21305由21，30，5三个元素组成的数字，为所有组合中最小的数字
 *
 *     输入：5,21
 *     输出：215
 *     说明：数组长度小于3，选择所有元素来组成最小值，215为最小值。
 *
 *
 */

/**
 *
 * 解题思路：
 *     1.三重for循环暴力
 *     首先可以对数组中的元素按照大小进行升序排序，
 *
 */

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String originStr = sc.next();

        System.out.println(getResult(originStr));

    }


    public static String getResult(String originStr){

        Integer[] arr = Arrays.stream(originStr.split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        if (arr.length < 3){
            // 分情况 2 个和 1个分别讨论
        }


        int resMin = Integer.MAX_VALUE;
        for (int i = 0;i < arr.length-2;i++){
            for (int j = i+1; j < arr.length-1; j++){
                for (int k = j+1; k < arr.length; k++){
                    ArrayList<Integer> list = new ArrayList<>();
                    int valI = arr[i];
                    int valJ = arr[j];
                    int valK = arr[k];
                    list.add(valI);
                    list.add(valJ);
                    list.add(valK);
                    int min = Integer.MAX_VALUE;
                    boolean[] isUsed = new boolean[3];
                    LinkedList<String> resList = new LinkedList<>();
                    int min_recursion = recursion(list,min,resList,isUsed);
                    resMin = Math.min(min_recursion,resMin);
                }
            }
        }
        
        return String.valueOf(resMin);
    }


    /**
     *
     * @param resList list中的元素不会改变，只用来进行遍历
     * @param min  max用来返回全排列的最终的结果
     * @return 返回全排列的最大值
     */

    // 对于全排列问题的想法：
    // 在回溯求解全排列问题时，使用isUsed()数组和不适用isUsed()数组的区别在于
    // 对于潜在搜索树的树形的不一致，如果是可重复的情况下，意味着某个元素可以被无限重复使用，这个时候不需要对走过的元素进行标记(N^N)
    // 在不可重复的情况下，需要对走过的元素进行标记，这时候就要使用isUsed[]数组，这时候的潜在搜索树的的节点个数就是N！
    // 这里的N！和N^N既代表的是潜在搜索树节点个数，同时也代表着是所有可能的解决方案的个数
    // 这里我们也可以看到其实不可重复的全排列的本质也是对于可重复的全排列的情况的一个子集[可以想象将]
    public static int recursion(ArrayList<Integer> originList,int min, LinkedList<String> resList,boolean[] isUsed){

        if (resList.size() == 3){

            StringBuilder sb = new StringBuilder();

            for (String str : resList){
                sb.append(str);
            }

            int one = Integer.parseInt(sb.toString());

            min = Math.min(min,one);

            return min;
        }

        for (int i = 0; i < originList.size(); i++){
            if (!isUsed[i]){ // 如果之前没有走过
                isUsed[i] = true;

                resList.push(originList.get(i).toString());

                min = recursion(originList,min,resList,isUsed);

                resList.pop();

                isUsed[i] = false;
            }
        }
        return min;
    }

}
