package 字符串数组集合操作.字符串变换最小字符串;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String originStr = sc.next();

        System.out.println(getResult(originStr));
    }



    public static String getResult(String originStr){

        char[] chars = originStr.toCharArray();

        Arrays.sort(chars);

        // 排好序的字符串
        String dupStr = new String(chars);

        if (dupStr.equals(originStr)) {
            return originStr;
        }

        int i = 0;

        while (i < dupStr.length()){

            char tmpCh = dupStr.charAt(i);

            char originCh = originStr.charAt(i);

            // 如果在相同位置有相同的字符
            if (tmpCh == originCh){
                i++;
            }else {

                // 获取待交换的字符所在的索引
                int index = originStr.lastIndexOf(tmpCh);

                // 待交换字符的索引的目标索引位置为i
                char[] tmpChars =  originStr.toCharArray();

                char tmp = tmpChars[i];
                tmpChars[i] = tmpChars[index];
                tmpChars[index] = tmp;

                return new String(tmpChars);
            }
        }

        return originStr;
    }
}
