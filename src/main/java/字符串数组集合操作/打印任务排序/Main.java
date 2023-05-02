package 字符串数组集合操作.打印任务排序;


import java.util.*;

/**
 *
 * 题目描述：
 *     某个打印机根据打印队列执行打印任务。打印任务分为九个优先级，分别用数字1-9表示，
 *     数字越大优先级越高。打印机每次从队列头部取出第一个任务A，
 *     然后检查队列余下任务中有没有比A优先级更高的任务，如果有比A优先级高的任务，
 *     则将任务A放到队列尾部，否则就执行任务A的打印。
 *     请编写一个程序，根据输入的打印队列，输出实际的打印顺序。
 *
 * 输入描述：
 *     输入一行，为每个任务的优先级，优先级之间用逗号隔开，优先级取值范围是1~9
 *
 * 输出描述：
 *     输出一行，为每个任务的打印顺序，打印顺序从0开始，用逗号隔开
 *
 * 用例：
 *     输入：9,3,5
 *     输出：0,2,1
 *
 *     说明：队列头部任务的优先级为9，最先打印，故序号为0；
 *          接着队列头部任务优先级为3，队列中还有优先级为5的任务，优先级3任务被移到队列尾部；
 *          接着打印优先级为5的任务，故其序号为1；
 *          最后优先级为3的任务的序号为2。
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

        LinkedList<Integer[]> list = new LinkedList<>();

        for (int i = 0;i < arr.length; i++){
            Integer[] tmpArr = new Integer[2];
            tmpArr[0] = arr[i];
            tmpArr[1] = i;
            list.add(tmpArr);
        }


        int i = 0;

        ArrayList<Integer> resList = new ArrayList<>();
        System.out.println();
        while (true){

            if (list.size() == 0)
                break;
            if (!isContainsGreaterThanCur(list,list.get(0)[0])){
                resList.add(list.get(0)[1]);
                list.removeFirst();
            }else {
                list.addLast(new Integer[]{list.get(0)[0],list.get(0)[1]});
                list.removeFirst();
            }
        }

        StringJoiner joiner = new StringJoiner(",","","");

        for (Integer one : resList){
            joiner.add(String.valueOf(one));
        }
        return joiner.toString();
    }


    public static boolean isContainsGreaterThanCur(LinkedList<Integer[]> list,Integer target){

        boolean flag = false;

        for (int i = 1;i < list.size(); i++){
            if (list.get(i)[0] > target){
                flag = true;
            }
        }
        return flag;
    }
}
