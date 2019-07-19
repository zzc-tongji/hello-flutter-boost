package icu.zzc.hello_flutter_boost;

import android.app.Application;
import android.content.Context;
import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.Platform;
import io.flutter.app.FlutterApplication;
import java.util.Map;

public class EntryPoint extends FlutterApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        FlutterBoost.init(new Platform() {
            @Override
            public Application getApplication() {
                return EntryPoint.this;
            }

            @Override
            public void openContainer(Context context, String url, Map<String, Object> urlParams, int requestCode, Map<String, Object> exts) {

            }
        });
    }
}
