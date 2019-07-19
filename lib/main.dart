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
      'home': (pageName, params, uniqueId) => Home(),
      'communication': (pageName, params, uniqueId) => Communication(),
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      builder: FlutterBoost.init(),
      home: Container(),
    );
  }
}
