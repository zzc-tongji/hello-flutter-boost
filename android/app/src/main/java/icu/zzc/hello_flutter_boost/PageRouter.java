package icu.zzc.hello_flutter_boost;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Map;

public class PageRouter {

    public static final String FLUTTER_CONTAINER_URL = "native://flutter-container";

    public static void openPageByUrl(Context context, String url, Map<String, Object> params) {
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
            case FLUTTER_CONTAINER_URL:
                intent = new Intent(context, FlutterContainerActivity.class);
                break;
            default:
                return;
        }
        intent.putExtras(parameter);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
