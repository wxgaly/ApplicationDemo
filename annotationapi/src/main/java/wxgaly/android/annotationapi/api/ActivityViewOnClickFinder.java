package wxgaly.android.annotationapi.api;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import wxgaly.android.annotation.OnClick;

/**
 * wxgaly.android.annotationapi.api.
 *
 * @author Created by WXG on 2019/3/4 004 11:25.
 * @version V1.0
 */
public class ActivityViewOnClickFinder implements ViewOnClick {

    public static final String TAG = ActivityViewBinder.class.getSimpleName();

    @Override
    public void onClick(final Object object, final Method method) {

        OnClick onClick = method.getAnnotation(OnClick.class);
        if (onClick != null) {
            int[] ids = onClick.value();
            for (final int id : ids) {
                View view = ((Activity) object).findViewById(id);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Log.d(TAG, "onClick: " + id);
                            method.invoke(object);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}
