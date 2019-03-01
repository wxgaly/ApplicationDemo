package wxgaly.android.annotationapi.api;

import android.app.Activity;
import android.view.View;

/**
 * wxgaly.android.annotationapi.api.
 *
 * @author Created by WXG on 2019/3/1 001 17:22.
 * @version V1.0
 */
public class ActivityViewFinder implements ViewFinder {
    @Override
    public View findView(Object object, int id) {
        return ((Activity) object).findViewById(id);
    }
}
