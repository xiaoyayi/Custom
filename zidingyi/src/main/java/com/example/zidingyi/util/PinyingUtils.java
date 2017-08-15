package com.example.zidingyi.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by 小亚 on 2017/8/15.
 * function:工具类方法,一般定义为静态,不要创建类对象一样就可以调用
 *
 */

public class PinyingUtils {
    //创建对象,以便调用
    private static HanyuPinyinOutputFormat hanyuPinyinOutputFormat;
    //吧得到的字符串改为字符数组
    public static String getPinyin(String string){
        hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        //不要音标
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //设置转换出大写字母
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);

        char[] chars=string.toCharArray();
        //创建一个装字符的容器,
        StringBuffer sb=new StringBuffer();

        for (int i = 0; i < chars.length; i++) {
            char c=chars[i];
            //如果是空格,跳出当前循环
            if (Character.isWhitespace(c)){
                continue;
            }
            //是不是汉字,如果不是直接拼写
            if (c>-128&&c<127){
                sb.append(c);
            }//是汉字那么我们获取拼音
            else{
                try {
                    String s=PinyinHelper.toHanyuPinyinStringArray(c, hanyuPinyinOutputFormat)[0];
                    //获取某个字符对应的拼音,可以获取到多音字  ,  单->shan ,dan  ,朴->  pu  ,piao
                    String s1 = PinyinHelper.toHanyuPinyinStringArray(c, hanyuPinyinOutputFormat)[0];

                    sb.append(s);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }
        }
    return sb.toString();
    }
}
