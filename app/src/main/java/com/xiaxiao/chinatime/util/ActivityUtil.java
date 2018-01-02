package com.xiaxiao.chinatime.util;

import android.content.Context;
import android.content.Intent;

import com.xiaxiao.chinatime.activity.YearDetialActivity;

/**
 * Created by Administrator on 2017/12/28.
 */

public class ActivityUtil {
    public static void goYearDetial(Context context, int year) {
        Intent i = new Intent(context, YearDetialActivity.class);
        i.putExtra("year", year);
        context.startActivity(i);

    }
}
