package com.example.zidingyi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ListView;

import com.example.zidingyi.bean.haohan;
import com.example.zidingyi.ui.QuickindexBar;
import com.example.zidingyi.util.toastUtil;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 侧拉索引:音乐APP,即时通讯,电商选择城市,短信验证选择城市都有这个类型自定义控件
 * 实现步骤:
 *  1.绘制A-Z的字母列表(自绘式自定义控件)
 *  2.响应触摸事件
 *  3.提供监听回调
 *  4.获取汉字拼音首字母,首字母 (pinying4j通过汉字得到他的拼音,只能一个字符一个字符的转换拼音)
 *  5.根据拼音排序
 *  6.根据首字母分组
 *  7.把监听回调和listview结合起来
 *  掌握解决问题的思路,把复杂的东西简单化,吧复杂的东西尽可能分成小的模块,把我模块关键点,一步一个脚印
 */
public class MainActivity extends AppCompatActivity {
    QuickindexBar qb;
    private ListView lv;
    private ArrayList<haohan> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        qb= (QuickindexBar) findViewById(R.id.qq);
        persons = new ArrayList<>();
        fillAndsortData(persons);
        //controller层设置适配器
        lv.setAdapter(new haohanAdapter(persons,this));
       //根据用户按住的字符自动跳转到对应的listview条目上
        qb.setOnLetterUpdateListener(new QuickindexBar.OnLetterUpdateListener() {
            @Override
            public void onLetterUpdate(String letter) {
                for (int i = 0; i <persons.size() ; i++) {
                   String l=persons.get(i).getPinyin().charAt(0)+"";
                    if (TextUtils.equals(letter,l)){
                        //找到第一个首字母是letter条目
                        lv.setSelection(i);
                        break;
                    }
                }
                toastUtil.showToast(MainActivity.this,letter);
            }
        });
    }
    /**
     * 填充数据,并排序
     * @param persons
     */
    private void fillAndsortData(ArrayList<haohan> persons) {
        //填充数据
        for (int i = 0; i < Cheeses.NAMES.length; i++) {
            String name=Cheeses.NAMES[i];
            persons.add(new haohan(name));
        }
        //排序
        Collections.sort(persons);


    }
}