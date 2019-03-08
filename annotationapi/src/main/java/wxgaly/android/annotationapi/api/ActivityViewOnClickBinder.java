package wxgaly.android.annotationapi.api;

import android.app.Activity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * wxgaly.android.annotationapi.api.
 *
 * @author Created by WXG on 2019/3/5 005 10:28.
 * @version V1.0
 */
public class ActivityViewOnClickBinder {

    private static final ActivityViewOnClickFinder activityFinder = new ActivityViewOnClickFinder();
    private static final Map<String, ViewBinder> binderMap = new LinkedHashMap<>();

    /**
     * Activity注解绑定 ActivityViewFinder
     *
     * @param activity
     */
    public static void bind(Activity activity) {
        bind(activity, activity, activityFinder);
    }

    /**
     * '注解绑定
     *
     * @param host   表示注解 View 变量所在的类，也就是注解类
     * @param object 表示查找 View 的地方，Activity & View 自身就可以查找，Fragment 需要在自己的 itemView 中查找
     * @param finder ui绑定提供者接口
     */
    private static void bind(Object host, Object object, ViewOnClick finder) {
        String className = host.getClass().getName();
        try {
            ViewBinder binder = binderMap.get(className);
            if (binder == null) {
                Class<?> aClass = Class.forName(className + "$$ViewBinder");
                binder = (ViewBinder) aClass.newInstance();
                binderMap.put(className, binder);
            }
//            if (binder != null) {
//                binder.onClick(host, object, finder);
//            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解除注解绑定 ActivityViewFinder
     *
     * @param host
     */
    public static void unBind(Object host) {
        String className = host.getClass().getName();
        ViewBinder binder = binderMap.get(className);
        if (binder != null) {
            binder.unBindView(host);
        }
        binderMap.remove(className);
    }

}