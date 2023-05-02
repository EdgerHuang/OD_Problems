package 字符串数组集合操作.寻找相同子串;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String strT = sc.next();
        String strP = sc.next();

       int resIndex =  getResult(strT,strP);
       if (resIndex >= 1){
           System.out.println(resIndex);
       }else {
           System.out.println("NO");
       }

    }

    public static int getResult(String strT,String strP){

        String[] arr = strT.split(strP);

        if (arr.length > 1){
            int firStrLen = arr[0].length();
            return firStrLen + 1;
        }else{
            return -1;
        }
    }
}
