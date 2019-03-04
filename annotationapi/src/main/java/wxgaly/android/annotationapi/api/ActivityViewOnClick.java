package wxgaly.android.annotationapi.api;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Method;

/**
 * wxgaly.android.annotationapi.api.
 *
 * @author Created by WXG on 2019/3/4 004 11:25.
 * @version V1.0
 */
public class ActivityViewOnClick implements ViewOnClick {
    @Override
    public void onClick(Object object, Method method, int[] ids) {
        for (int id : ids) {
            View view = ((Activity) object).findViewById(id);
        }

    }
}
