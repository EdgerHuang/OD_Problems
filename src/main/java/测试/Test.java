package 测试;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {


        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        List<Integer> resList = list.subList(0,1);
        System.out.println(resList);


    }



}
