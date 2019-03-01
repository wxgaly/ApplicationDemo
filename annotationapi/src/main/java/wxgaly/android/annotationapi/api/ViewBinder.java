package wxgaly.android.annotationapi.api;

/**
 * wxgaly.android.annotationapi.api.
 *
 * @author Created by WXG on 2019/3/1 001 17:19.
 * @version V1.0
 */
public interface ViewBinder<T> {
    void bindView(T host, Object object, ViewFinder finder);

    void unBindView(T host);
}
