package com.example.zidingyi.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 小亚 on 2017/8/14.
 */

public class toastUtil {
    private static Toast toast;
    public static void showToast(Context context,String msg){
        if (toast==null){
            toast=Toast.makeText(context,"",Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
}
