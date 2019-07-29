package icu.zzc.hello_flutter_boost;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Map;

class PageRouter {

    static final String WEB_VIEW_URL = "native://web-view";
    static final String FLUTTER_CONTAINER_URL = "native://flutter-container";
    static final String TEXT_TO_SPEECH_URL = "native://text-to-speech";

    static void openPageByUrl(Context context, String url, Map<String, Object> params) {
        // parse parameter
        Bundle parameter = new Bundle();
        Object obj;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            obj = entry.getValue();
            // ignore non-string or empty-string parameter
            if (obj instanceof String && obj != "") {
                parameter.putString(entry.getKey(), (String) obj);
            }
        }
        // router
        Intent intent;
        switch (url) {
            case WEB_VIEW_URL:
                intent = new Intent(context, WebViewActivity.class);
                break;
            case FLUTTER_CONTAINER_URL:
                intent = new Intent(context, FlutterContainerActivity.class);
                break;
            case TEXT_TO_SPEECH_URL:
                intent = new Intent(context, TextToSpeechActivity.class);
                break;
            default:
                return;
        }
        intent.putExtras(parameter);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
