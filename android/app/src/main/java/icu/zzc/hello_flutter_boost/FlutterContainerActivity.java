package icu.zzc.hello_flutter_boost;

import android.net.Uri;
import android.os.Bundle;

import com.idlefish.flutterboost.containers.BoostFlutterActivity;

import java.util.HashMap;
import java.util.Map;

public class FlutterContainerActivity extends BoostFlutterActivity {
    private String name;
    private Map<String, String> parameters;

    @Override
    public String getContainerUrl() {
        return name;
    }

    @Override
    public Map getContainerUrlParams() {
        return parameters;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get url from intent
        String u;
        try {
            u = this.getIntent().getExtras().getString("url");
            if (u == null) {
                u = "flutter://home";
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("There must be a parameter `url` to indicate which flutter page to render.");
        }
        if (!u.startsWith("flutter://")) {
            throw new IllegalArgumentException("Parameter `url` is \"" + u + "\" which does not start with \"flutter://\".");
        }
        // get name and parameter list
        int endIndex = u.indexOf('?');
        if (endIndex == -1) {
            name = u;
            parameters = null;
        } else {
            name = u.substring(0, endIndex);
            Uri uri = Uri.parse(u);
            String v;
            parameters = new HashMap<>();
            for (String k : uri.getQueryParameterNames()) {
                v = uri.getQueryParameter(k);
                if (v != null) {
                    parameters.put(k, v);
                }
            }
        }
    }
}
