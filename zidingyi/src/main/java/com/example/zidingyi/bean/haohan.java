package com.example.zidingyi.bean;

import android.support.annotation.NonNull;

import com.example.zidingyi.util.PinyingUtils;

/**
 * Created by 小亚 on 2017/8/15.
 */

public class haohan implements Comparable<haohan>{
    private String name;
    private String pinyin;

    public haohan(String name) {
        this.name = name;
        //使用工具类根据汉字拿到拼音
        this.pinyin = PinyingUtils.getPinyin(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }


    @Override
    public int compareTo(@NonNull haohan haohan) {
        return this.pinyin.compareTo(haohan.pinyin);
    }
}
