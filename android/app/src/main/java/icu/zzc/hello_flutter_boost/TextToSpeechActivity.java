package icu.zzc.hello_flutter_boost;

/*
 * modified by http://download.taobaocdn.com/freedom/33627/compress/nls-sdk-android-190718.zip
 * */

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
/*
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
*/
import android.widget.EditText;
/*
import android.widget.SeekBar;
import android.widget.Spinner;
*/
import android.widget.Toast;

import com.alibaba.idst.token.AccessToken;
import com.alibaba.idst.util.NlsClient;
import com.alibaba.idst.util.SpeechSynthesizer;
import com.alibaba.idst.util.SpeechSynthesizerCallback;

import java.lang.ref.WeakReference;

/**
 * 调用 SpeechSynthesizer 的示例代码，用户在实际业务中可以用此处提供的 AudioPlayer 播放声音，也可以自行实现相关功能
 * 本示例代码只是用来展示调用步骤，用户应该在真实使用时按需添加异常处理、防止重复点击等逻辑
 */
public class TextToSpeechActivity extends AppCompatActivity /* implements AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener */ {
    private static final String TAG = "AliSpeechDemo";

    NlsClient client;
    private SpeechSynthesizer speechSynthesizer;
    /*
    private Spinner spinner;
    private String[] voices;
    private String chosenVoice;
    private int speechRate;
    */
    private static AudioPlayer audioPlayer;
    private static AccessToken accessToken;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 很不优雅地解除 NetworkOnMainThreadException
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // 获取从 Flutter Page 传递的 text
        text = this.getIntent().getExtras().getString("text");
        //
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(this.getString(R.string.text_to_speech_activity_title));

        setContentView(R.layout.activity_text_to_speech);
        
        /*
        SeekBar speedBar = findViewById(R.id.speed);
        speedBar.setOnSeekBarChangeListener(this);
        
        spinner = findViewById(R.id.voice);
        voices = new String[]{
            SpeechSynthesizer.VOICE_AMEI,
            SpeechSynthesizer.VOICE_NINGER,
            SpeechSynthesizer.VOICE_RUOXI,
            SpeechSynthesizer.VOICE_SICHENG,
            SpeechSynthesizer.VOICE_SIJIA,
            SpeechSynthesizer.VOICE_SIQI,
            SpeechSynthesizer.VOICE_SITONG,
            SpeechSynthesizer.VOICE_SIYUE,
            SpeechSynthesizer.VOICE_XIAOBEI,
            SpeechSynthesizer.VOICE_XIAOGANG,
            SpeechSynthesizer.VOICE_XIAOMEI,
            SpeechSynthesizer.VOICE_XIAOMENG,
            SpeechSynthesizer.VOICE_XIAOWEI,
            SpeechSynthesizer.VOICE_XIAOXUE,
            SpeechSynthesizer.VOICE_XIAOYUN,
            SpeechSynthesizer.VOICE_YINA};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, voices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        */

        audioPlayer = new AudioPlayer();

        // 第一步，创建client实例，client只需要创建一次，可以用它多次创建synthesizer
        client = new NlsClient();

        // 如果 Flutter Page 传递了 text ，那么立即转为语音。
        if (text != null) {
            startSynthesizer(null);
        }
    }
    
    /*

    // ======================= Code for SeekBar ===============================
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        speechRate = progress - 200;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    // ======================= Code for Spinner ===============================
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        chosenVoice = voices[position];
        Log.i(TAG, "User choose " + chosenVoice);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    
    */

    @Override
    public void onDestroy() {
        // 最终，释放客户端
        audioPlayer.stop();
        client.release();
        super.onDestroy();
    }

    public void startSynthesizer(View view) {
        refreshToken();

        EditText editText = findViewById(R.id.editText);
        String text;

        if (this.text != null) {
            text = this.text;
            this.text = null;
        } else {
            text = editText.getText().toString();
        }

        // 第二步，定义语音合成回调类
        MyCallback callback = new MyCallback();
        // 第三步，创建语音合成对象
        speechSynthesizer = client.createSynthesizerRequest(callback);
        callback.setSynthesizer(speechSynthesizer);

        // 第四步，设置token和appkey
        // Token有有效期，请使用https://help.aliyun.com/document_detail/72153.html 动态生成token
        speechSynthesizer.setToken(accessToken.getToken());
        // 请使用阿里云语音服务管控台(https://nls-portal.console.aliyun.com/)生成您的appkey
        speechSynthesizer.setAppkey(this.getString(R.string.app_key));

        /*
        // 第五步，设置相关参数，以下选项都会改变最终合成的语音效果，可以按文档调整试听效果
        // 设置人声
        Log.i(TAG, "Set chosen voice " + chosenVoice);
        speechSynthesizer.setVoice(chosenVoice);
        // 设置语速
        Log.i(TAG, "User set speechRate " + speechRate);
        speechSynthesizer.setSpeechRate(speechRate);
        */
        // 设置要转为语音的文本
        speechSynthesizer.setText(text);
        // 设置语音数据采样率
        speechSynthesizer.setSampleRate(SpeechSynthesizer.SAMPLE_RATE_16K);
        // 设置语音编码，pcm编码可以直接用audioTrack播放，其他编码不行
        speechSynthesizer.setFormat(SpeechSynthesizer.FORMAT_PCM);
        /*
        // 设置音量
        // speechSynthesizer.setVolume(50);
        // 设置语速
        // speechSynthesizer.setSpeechRate(0);
        // 设置语调
        // speechSynthesizer.setPitchRate(0);
        */
        // 第六步，开始合成
        if (speechSynthesizer.start() < 0) {
            Toast.makeText(TextToSpeechActivity.this, "启动语音合成失败！", Toast.LENGTH_LONG).show();
            speechSynthesizer.stop();
            return;
        }
        Log.d(TAG, "speechSynthesizer start done");
    }

    public void cancelSynthesizer(View view) {
        Log.d(TAG, "cancel pressed");
        speechSynthesizer.cancel();
        audioPlayer.stopPlay();
    }

    private static class MyCallback implements SpeechSynthesizerCallback {
        private WeakReference<SpeechSynthesizer> synthesizerWeakReference;

        public void setSynthesizer(SpeechSynthesizer speechSynthesizer) {
            synthesizerWeakReference = new WeakReference<>(speechSynthesizer);
        }

        // 语音合成开始的回调
        @Override
        public void onSynthesisStarted(String msg, int code) {
            Log.d(TAG, "OnSynthesisStarted " + msg + ": " + String.valueOf(code));
        }

        // 第七步，获取音频数据的回调，在这里把音频写入播放器
        @Override
        public void onBinaryReceived(byte[] data, int code) {
            Log.i(TAG, "binary received length: " + data.length);
            audioPlayer.setAudioData(data);
        }

        // 语音合成完成的回调，在这里关闭播放器
        @Override
        public void onSynthesisCompleted(final String msg, int code) {
            Log.d(TAG, "OnSynthesisCompleted " + msg + ": " + String.valueOf(code));
            // 第八步，结束合成
            synthesizerWeakReference.get().stop();


        }

        // 调用失败的回调
        @Override
        public void onTaskFailed(String msg, int code) {
            Log.d(TAG, "OnTaskFailed " + msg + ": " + String.valueOf(code));
            // 第八步，结束合成
            synthesizerWeakReference.get().stop();
        }

        // 连接关闭的回调
        @Override
        public void onChannelClosed(String msg, int code) {
            Log.d(TAG, "OnChannelClosed " + msg + ": " + String.valueOf(code));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void refreshToken() {
        if (accessToken == null || accessToken.getToken() == null || accessToken.getExpireTime() < System.currentTimeMillis() / 1000) {
            accessToken = new AccessToken(
                this.getString(R.string.access_key_id),
                this.getString(R.string.access_key_secret)
            );
            try {
                accessToken.apply();
            } catch (Exception e) {

            }
        }
    }
}
