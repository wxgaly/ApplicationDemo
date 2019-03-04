package wxgaly.android.annotationapi.api;

import java.lang.reflect.Method;

/**
 * wxgaly.android.annotationapi.api.
 *
 * @author Created by WXG on 2019/3/4 004 11:21.
 * @version V1.0
 */
public interface ViewOnClick {

    void onClick(Object object, Method method, int[] ids);
}
