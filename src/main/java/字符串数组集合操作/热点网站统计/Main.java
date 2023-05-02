package 字符串数组集合操作.热点网站统计;


/**
 *
 * 题目描述：
 *     企业路由器的统计页面，有一个功能需要动态统计公司访问最多的网页URL top N。
 * 请设计一个算法，可以高效动态统计Top N的页面。
 *
 * 输入描述：
 *     每一行都是一个URL或者一个数字，如果是URL,代表一段时间内的网页访问；
 *     如果是一个数字N,代表本次需要输出的Top N个URL。
 *
 * 输入约束：
 *     1、总访问网页数量小于5000个，单网页访问次数小于65535次；
 *     2、网页URL仅由字母，数字和点分隔符组成，且长度小于等于127字节；
 *     3、数字是正整数，小于等于10且小于当前总访问网页数；
 *
 * 输出描述：
 *     每行输入要对应一行输输入，出，输出按访问次数排序的前N个URL，用逗号分隔。
 *
 * 输出要求：
 *     1、每次输出要统计之前所有不仅是本次输入；
 *     2、如果有访问次数相等的URL，按URL的字符串字典序升序排列，输出排序靠前的URL；
 *
 * 用例：
 *     输入：news.qq.com
 *           news.sina.com.cn
 *           news.qq.com
 *           news.qq.com
 *           game.163.com
 *           game.163.com
 *           www.huawei.com
 *           www.cctv.com
 *           3
 *           www.huawei.com
 *           www.cctv.com
 *           www.huawei.com
 *           www.cctv.com
 *           www.huawei.com
 *           www.cctv.com
 *           www.huawei.com
 *           www.cctv.com
 *           www.huawei.com
 *           3
 *     输出：news.qq.com,game.163.com,news.sina.com.cn
 *           www.huawei.com,www.cctv.com,news.qq.com
 *     说明：无
 *
 *     输入：news.qq.com
 *           www.cctv.com
 *           1
 *           www.huawei.com
 *           www.huawei.com
 *           2
 *           3
 *     输出：news.qq.com
 *           www.huawei.com,news.qq.com
 *           www.huawei.com,news.qq.com,www.cctv.com
 *     说明：无
 *
 *
 *
 */

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);

        ArrayList<String> list = new ArrayList<>();

        while (sc.hasNextLine()){

            String str = sc.nextLine();

            if (str.equals("")){
                break;
            }
            list.add(str);
        }

        getResult(list);

    }

    public static void getResult(ArrayList<String> list){

        HashMap<String,Integer> map = new HashMap<>();

        // 阶段性统计[泛型用于阶段性统计]
        ArrayList<ArrayList<String>> resList = new ArrayList<>();

        for (int i = 0;i < list.size(); i++){

            // 需要进行统计和收集
            if (isNum(list.get(i))){
                int tmpInt = Integer.parseInt(list.get(i));
                ArrayList<String> tmpList = map.keySet().stream().sorted(Comparator.comparing((String s)->-map.get(s))).limit(tmpInt).collect(Collectors.toCollection(ArrayList<String>::new));
                resList.add(tmpList);
            }else {
                map.put(list.get(i),map.getOrDefault(list.get(i),0)+1);
            }
        }


        for (ArrayList<String> tmpOneList : resList){

            StringJoiner joiner = new StringJoiner(",","","");

            for (String str : tmpOneList){
                joiner.add(str);
            }
            System.out.println(joiner.toString());
        }
    }


    // 判断一个字符串是否可以转换为数字
    public static boolean isNum(String str){

        try {
         Integer one = Integer.parseInt(str);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
