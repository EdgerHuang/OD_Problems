package 字符串数组集合操作.数组拼接;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 题目描述：
 *     现在有多组整数数组，需要将它们合并成一个新的数组。
 * 合并规则，从每个数组里面按顺序取出固定长度的内容合并到新的数组中，取完的内容会删除掉，
 * 如果该行不足固定长度或者已为空，则直接取出剩余部分的内容放到新的数组中，继续下一行。
 *
 * 输入描述：
 *     第一行是每次读取的固定长度，0 < 长度 < 10
 *     第二行是整数数组的数目，0 < 数目 < 1000
 *     第3-n行是需要合并的数组，不同的数组用回车换行分隔，数组内部用逗号分隔，最大不超过100个元素。
 *
 * 输出描述：
 *     输出一个新的数组，用逗号分隔。
 *
 * 用例：
 *     输入：3
 *          2
 *          2,5,6,7,9,5,7
 *          1,7,4,3,4
 *
 *     输出：2,5,6,1,7,4,7,9,5,3,4,7
 *
 *     说明：无
 *
 *
 */
public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 需要分隔的个数
        int k = sc.nextInt();

        int num = sc.nextInt();

        String[] arr = new String[num];

        for (int i = 0;i < num;i++){
            arr[i] = sc.next();
        }

        System.out.println(getResult(arr,num,k));
    }


    public static String getResult(String[] arr,int num, int k){

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0;i < num;i++){
            String[] tmpArr = arr[i].split(",");
            StringBuilder sb = new StringBuilder();
            for (String str:tmpArr){
                sb.append(str);
            }
            list.add(sb.toString());
        }

        StringBuilder joiner = new StringBuilder();


        while (true){

            int total = 0;
            // 每次计算整个list中的元素的总数
            for (String str : list){
                total += str.length();
            }

            // 当整个list中没有元素时，就返回
            if (total == 0){
                StringJoiner resJoin = new StringJoiner(",","","");
                for (char ch:joiner.toString().toCharArray()){
                    resJoin.add(String.valueOf(ch));
                }
                return resJoin.toString();
            }

            int i = 0;

            for (i = 0;i < list.size();i++){

                // 保证字符串的长度是大于等于k
                if (list.get(i).length() >= k){
                    String tmpStr = list.get(i).substring(0,k);
                    joiner.append(tmpStr);
                    String resTmpStr = list.get(i).substring(k);
                    list.set(i,resTmpStr);
                }else if (list.get(i).length() > 0){ // 字符串的长度处于是(0,k)之间

                    String tmpStr = list.get(i);
                    joiner.append(tmpStr);
                    list.set(i,"");
                }
                //else { // 字符串的长度为0，意味着是""
                    // do nothing
            }

        }

    }

}
