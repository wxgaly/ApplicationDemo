package wxgaly.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * wxgaly.android.annotationcompiler.annotation.
 *
 * @author Created by WXG on 2019/3/1 001 16:44.
 * @version V1.0
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface BindView {
    int value();
}
