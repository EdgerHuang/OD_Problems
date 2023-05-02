package 字符串数组集合操作.最大N个数与最小N个数的和;


import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * 题目描述：
 *     给定一个数组，编写一个函数来计算它的最大N个数与最小N个数的和。你需要对数组进行去重。
 *
 * 说明：
 *     数组中数字范围[0,1000]
 *     最大的N个数与最小的N个数不能有重叠，如有重叠，输入非法，返回-1
 *     输入非法返回-1
 *
 * 输入描述：
 *     第一行输入M, M表示数组大小
 *     第二行输入M个数，标识数组内容
 *     第三行输入N,N表达需要计算的最大，最小N个数
 *
 * 输出描述：
 *     输出最大N个数与最小N个数的和
 *
 *
 * 用例：
 *     输入：5
 *          95 88 83 64 100
 *          2
 *     输出: 342
 *     说明：最大2个数[100,95],最小2个数[93,64],输出为342
 *
 *     输入：5
 *           3 2 3 4 2
 *           2
 *     输出：-1
 *     说明：最大2个数[4,3],最小2个数[3,2],有重叠输出为-1。
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();

        int[] arr = new int[num];

        for (int i = 0;i < num; i++){
            arr[i] = sc.nextInt();
        }

        int N = sc.nextInt();

        System.out.println(getResult(num,arr,N));
    }

    public static int getResult(int num,int[] arr,int N){

        // 对数组进行去重
        TreeSet<Integer> set = new TreeSet<>((a,b) -> b-a);

        for (int one : arr){
            set.add(one);
        }

        int size = set.size();

        int halfSize = size / 2;

        // N的取值范围是[1,halfSize()]
        if (N < 1 || N > halfSize){
            return -1;
        }

        Integer[] newArr = set.toArray(Integer[]::new);

        int sum = 0;

        for (int i = 0;i < N;i++){
            int tmpSum = newArr[i] + newArr[newArr.length-1-i];
            sum += tmpSum;
        }
        return sum;
    }

}
