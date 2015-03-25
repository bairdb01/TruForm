package naddateam.truform.functionality;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by Erik on 3/18/2015.
 */
public class GifWebView extends WebView {

    public GifWebView(Context context, String path) {
        super(context);

        loadUrl(path);
    }
}
