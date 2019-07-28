# hello-flutter-boost

### 开发环境

#### Flutter

版本 `v1.5.8`

``` bash
flutter version v1.5.8
```

FlutterBoost 暂不支持更高版本的 Flutter。

#### FlutterBoost

分支 `flutter_1.5_upgrade_opt`

``` yaml
flutter_boost2:
  git:
    url: 'https://github.com/alibaba/flutter_boost.git'
    ref: 'flutter_1.5_upgrade_opt'
```

#### 其他

升级 gradle 不影响使用。

![520b82bf0cb6284d13bc32e770136889](./image/520b82bf0cb6284d13bc32e770136889.png)

### 编译运行

#### 接入阿里云语音合成服务

##### 参数生成

根据[指南](https://help.aliyun.com/document_detail/72138.html)，生成 `access_key_id` 、 `access_key_secret` 和 `app_id` 。

##### Android

进入目录 `android/app/src/main/res/values/`。

将文件 `sensitive_data_example.xml` 复制为文件 `sensitive_data.xml` 。

打开文件 `sensitive_data.xml` ，解除注释并填入对应参数。

