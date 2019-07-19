package icu.zzc.hello_flutter_boost;

import android.os.Bundle;
import com.idlefish.flutterboost.containers.BoostFlutterActivity;
import java.util.Map;

public class MainActivity extends BoostFlutterActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public String getContainerUrl() {
    return "home";
  }

  @Override
  public Map getContainerUrlParams() {
    return null;
  }
}
