package wxgaly.android.annotationapi.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * wxgaly.android.annotationapi.api.
 *
 * @author Created by WXG on 2019/3/4 004 11:41.
 * @version V1.0
 */
public class AnnotationUtils {

    public static Object tryInvoke(Method method, Object target, Object... arguments) {
        Throwable cause;
        try {
            return method.invoke(target, arguments);
        } catch (IllegalAccessException e) {
            cause = e;
        } catch (InvocationTargetException e) {
            cause = e;
        }
        throw new RuntimeException(
                "Unable to invoke " + method + " on " + target + " with arguments "
                        + Arrays.toString(arguments), cause);
    }

}
