package 字符串数组集合操作.TLV解析II;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 *
 * 题目描述：
 *     两端通过格式的报文来通信，现在收到对端的一个TLV格式的消息包，要求生成匹配后的(tag, length, valueOffset)列表。
 *
 * 具体要求如下：
 *     1. 消息包中多组tag,length,value紧密排列，其中tag,length各占1字节(uint8_t) , value所占字节数等于length的值
 *     2. 结果数组中tag值已知，需要填充每个tag对应数据的length和valueOffset值(valueOffset为value在原消息包中的起始偏移量（从0开始，以字节为单位))
 *        即将消息包中的tag与结果数组中的tag进行匹配（可能存在匹配失败的情况，若结果数组中的tag在消息包中找不到，则length和valueOffset都为0)
 *     3. 消息包和结果数组中的tag值都按升序排列，且不重复
 *     4. 此消息包未被篡改，但尾部可能不完整，不完整的一组TLV请丢弃掉
 *
 * 输入描述：
 *     第一行: 一个字符串，代表收到的消息包。字符串长度在10000以内。
 *         说明1: 字符串使用十六进制文本格式（字母为大写）来展示消息包的数据，
 *                如0F04ABABABAB代表一组TLV:前两个字符(0F）代表tag值为15，
 *                接下来两个字符（04）代表length值为4字节，接下来8个字符即为4字节的value。
 *         说明2: 输入字符串中，每一组TLV紧密排列，中间无空格等分隔符
 *     第二行： 需要匹配的tag数量n (0 < n < 1000) 。
 *     后面n行： 需要匹配的n个tag值（十进制表示)，递增排列。
 *
 * 输出描述：
 *     和需要匹配的n个tag对应的n行匹配结果，每一行由长度和偏移量组成
 *
 * 用例：
 *
 *     输入：0F04ABABABAB
 *           1
 *           15
 *     输出：4 2
 *     说明：tag15(十六进制0F)对应数据的长度为4，其value从第三个字节开始，因此偏移量为2
 *
 *     输入：0F04ABABABAB1001FF
 *          2
 *          15
 *          17
 *     输出：4 2
 *          0 0
 *     说明：第二个tag匹配失败
 *
 */

/**
 *
 * 解题思路：
 *     对tagList进行遍历，一个tag一个tag地在originList中查找
 *
 *     在recursion()中，使用boolean返回值，同时记录下起始的查找位置
 *     如果在recursion()中返回false，说明没有找到，此时在查找下一个tag时，需要接着从上一次的起始查找位置开始查找
 *     而如果recursion(),中返回了true，说明已经找到，此时我们需要调整下次索引开始的位置
 *     因为我们记录结果的时候，有resList，此时，最后一个结果就是我们需要的，此时逻辑就通了
 *
 *
 *
 */

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String originStr = sc.next();

        int num = sc.nextInt();

        ArrayList<Integer> tagList = new ArrayList<>();

        for (int i = 0; i < num; i++){
            tagList.add(sc.nextInt());
        }

        getResult(originStr,num,tagList);
    }


    public static void getResult(String originStr, int num,  ArrayList<Integer> tagList){


        // 以字节为单位来存储原始的消息包数据
        ArrayList<String> originList = new ArrayList<>();

        for (int i = 0; i < originStr.length() - 1; i += 2){
            char ch = originStr.charAt(i);
            char chAnother = originStr.charAt(i + 1);
            originList.add(ch + String.valueOf(chAnother));
        }

        // 收集目标tag的value的length以及value的起始字节所在的次序
        ArrayList<Integer[]> resList = new ArrayList<>();

        int i = 0;

        for (Integer targetTag : tagList){


            // 此时已经找到了，可以去resList中最近的一次查找到的index + length就是下次的index位置
            if (recursion(originList,i,targetTag,resList)){

                i = resList.get(resList.size()-1)[1] + resList.get(resList.size()-1)[0];
            }

        }

        for (Integer[] one : resList){
            StringJoiner joiner = new StringJoiner(" ","","");
            joiner.add(String.valueOf(one[0]));
            joiner.add(String.valueOf(one[1]));
            System.out.println(joiner);
        }

    }

    // 当然是可以通过for循环来处理的，外层for循环tagList,内层循环while()

    // 这里挑战使用递归来处理

    /**
     *
     * @param originList 用来保存原始的字符串(字节数组)，不会改变
     * @param index     用来标识在originList中tag的索引
     * @param targetTag  代表目标tag值
     * @param resList   用来存放结果数组
     * @return
     */

    // 这里还有一个小细节，如果我们不在递归的参数中制定形参boolean flag,就需要return recursion()
    public static boolean recursion(ArrayList<String> originList, int index, Integer targetTag, ArrayList<Integer[]> resList){

        // 如果没有找到，或者是找到了都要返回
        // 没有找到意味着是遍历到了末尾，
        // 这里需要说说明的是消息包的不完整，是消息末尾数据的丢失，而不是在中间某个TLV数据的不完整，如果是这样，恐怕比较难办
        if (index >= originList.size()){ // 没有找到
            Integer[] tmpArr = {0,0};
            resList.add(tmpArr);
            return false;
        }
        // 因为是
        // 这里还有bug，因为消息包有可能不完整
        if (Integer.valueOf(Integer.parseInt(originList.get(index),16)).equals(targetTag)){

            Integer[] tmpArr = new Integer[2];

            // 这里我们必须保证在找到对应的tag后能够在length后有固定长度的字节数，否则也将其进行添加{0,0}的操作。
            tmpArr[0] = Integer.parseInt(originList.get(index+1),16);
            tmpArr[1] = index + 2;
           if (originList.size() - (index + 2) >= index + 1){
               resList.add(tmpArr);
               return true;
           }else { // 如果是这样可以进行截断处理，然后返回false，这样下一个就不会陷入死循环

               // 对originList做截断操作
               List<String> s = originList.subList(0,index);
               originList = new ArrayList<>(s);
               resList.add(new Integer[]{0,0});
               // 这里是可返回true的，因为这是最后的TLV了
               return true;
           }

        }else {
            return recursion(originList,index + Integer.parseInt(originList.get(index+1),16) + 2,targetTag, resList);
        }

    }

}
