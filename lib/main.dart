import 'package:flutter/material.dart';
import 'package:flutter_boost2/flutter_boost.dart';

import 'page/communication.dart';
import 'page/home.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
    FlutterBoost.singleton.registerPageBuilders({
      'flutter://home': (_, params, ___) => Home(params),
      'flutter://communication': (_, params, ___) => Communication(params),
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      builder: FlutterBoost.init(),
      // There must be an empty container here, or an error page will appear.
      home: Container(),
    );
  }
}
