package 字符串数组集合操作.找出符合要求的字符串子串;

/**
 * 题目描述：
 *     给定两个字符串，从字符串2中找出字符串1中的所有字符，去重并按照ASCII值从小到大排序。
 *     输入字符串1：长度不超过1024
 *     输入字符串2：长度不超过1000000
 *     字符范围满足ASCII编码要求，按照ASCII的值由小到大排序
 *
 *
 * 输入描述：
 *     bach
 *     bbaaccedfg
 * 输出描述：
 *     abc
 *
 * 备注：
 *     输入字符串1 为给定字符串bach，输入字符串2 bbaaccedfg
 *     从字符串2中找出字符串1的字符，去除重复的字符，并且按照ASCII值从小到大排序，得到输出的结果为abc。
 *     字符串1中的字符h在字符串2中找不到不输出。
 *
 * 用例：
 *     输入：fach
 *           bbaaccedfg
 *     输出：acf
 */


/**
 * 解题思路：
 *    首先我们需要明确一点，如果在字符串1中包含有重复的字符，则在字符串2中必须也要将字符串1中的重复字符全部找出，比如
 *    字符串1为"aabbbcde",则在字符串2中含有两个'a',两个'b'...这样。然后找到后才是去重和按照字典排序.
 *
 *    因为对于字符串2中不存在字符串1的字符，可以不进行输出
 *    那么对于字符串1的重复字符，在字符串2中存在一个或者数量等于重复字符再或者数量大于该字符，最后的输出结果都是一样的
 *
 *
 *    所以对于这道题可以有多重解法
 *    1. 可以使用TreeSet对字符串1和2 进行填装，填装后使用双指针分别遍历字符串1(i)和2(j)，i是一直前进，如果j和i相等，就将i进行append()然后(i++,j++)
 *       但是如果不相等，append(""),i++，j暂停，然后将追加后的字符串直接输出。
 *
 *    2. 使用暴力，对字符串1进行逐字符遍历，然后每遍历到一个字符，在字符串2中使用s.contains(ch)来查看是否存在，存在的话追加append(ch)
 *        否则追加append("").再讲收集到的结果存放到set中，然后进行排序(Arrays.sort(s.toCharArray()))
 *
 *    3. 使用HashMap<Character,Integer>来进行存储每个字符的个数，每次遍历字符串1中的字符时，去map中查找看是否存在
 *       如果存在，则将其进行append(),同时将其对应的数量--，以此类推
 *
 *
 */
public class Main {



}
