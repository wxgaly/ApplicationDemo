package wxgaly.android.annotationcompiler.util;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;

import wxgaly.android.annotation.BindView;
import wxgaly.android.annotation.OnClick;

/**
 * wxgaly.android.annotationcompiler.util.
 *
 * @author Created by WXG on 2019/4/1 001 15:51.
 * @version V1.0
 */
public class OnClickMethod {

    private Element mElement;
    private int[] mResIds;

    OnClickMethod(Element element) throws IllegalArgumentException {
        if (element.getKind() != ElementKind.METHOD) {
            throw new IllegalArgumentException(String.format("Only fields can be annotated with @%s",
                    OnClick.class.getSimpleName()));
        }
        mElement =  element;

        OnClick bindView = mElement.getAnnotation(OnClick.class);
        mResIds = bindView.value();
        for (int mResId : mResIds) {
            if (mResId < 0) {
                throw new IllegalArgumentException(
                        String.format("value() in %s for field %s is not valid !", BindView.class.getSimpleName(),
                                mElement.getSimpleName()));
            }
        }

    }

    /**
     * 获取变量名称
     *
     * @return
     */
    Name getFieldName() {
        return mElement.getSimpleName();
    }

    /**
     * 获取变量id
     *
     * @return
     */
    int[] getResIds() {
        return mResIds;
    }

}
