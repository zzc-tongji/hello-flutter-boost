package icu.zzc.hello_flutter_boost;

import android.app.Application;
import android.content.Context;

import com.idlefish.flutterboost.BoostEngineProvider;
import com.idlefish.flutterboost.BoostFlutterEngine;
import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.Platform;
import com.idlefish.flutterboost.interfaces.IFlutterEngineProvider;

import io.flutter.app.FlutterApplication;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.view.FlutterMain;

import java.util.Map;

public class EntryPoint extends FlutterApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        FlutterBoost.init(new Platform() {
            @Override
            public boolean isDebug() {
                return true;
            }

            @Override
            public Application getApplication() {
                return EntryPoint.this;
            }

            @Override
            public IFlutterEngineProvider engineProvider() {
                return new BoostEngineProvider() {
                    @Override
                    public BoostFlutterEngine createEngine(Context context) {
                        return new BoostFlutterEngine(
                            context,
                            new DartExecutor.DartEntrypoint(
                                context.getResources().getAssets(),
                                FlutterMain.findAppBundlePath(context),
                                "main"
                            ),
                            "/"
                        );
                    }
                };
            }

            @Override
            public void openContainer(Context context, String url, Map<String, Object> urlParams, int requestCode, Map<String, Object> exts) {
                PageRouter.openPageByUrl(context, url, urlParams);
            }

            @Override
            public int whenEngineStart() {
                return LAZY;
            }
        });
    }
}
