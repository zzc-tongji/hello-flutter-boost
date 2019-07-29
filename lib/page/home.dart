import 'package:flutter/material.dart';
import 'package:flutter_boost2/flutter_boost.dart';

import 'communication.dart';
import '../module/vw.dart';

class Home extends StatelessWidget {
  final Map _parameters;

  Home([this._parameters]);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Home'),
        leading: Builder(
          builder: (BuildContext context) {
            return IconButton(
              icon: const Icon(Icons.arrow_back),
              onPressed: () {
                // Just use `Navigator.of(context).pop();` to get back.
                //
                // FlutterBoost has encapsulated all things,
                // no matter whether this is the first page
                // of Activity (Android) or View Controller (iOS)
                Navigator.of(context).pop();
              },
            );
          },
        ),
      ),
      body: Center(
        child: Column(
          children: [
            RaisedButton(
              child: Text('通信机制 Communication'),
              onPressed: () {
                Navigator.of(context).push(
                    MaterialPageRoute(builder: (context) => Communication()));
              },
              color: Colors.lightBlueAccent,
            ),
            RaisedButton(
              child: Text('通信机制 Communication'),
              onPressed: () {
                FlutterBoost.singleton.open('native://flutter-container',
                    urlParams: {'url': 'flutter://communication'});
              },
              color: Colors.lightGreenAccent,
            ),
            RaisedButton(
              child: Text('语音合成 TTS'),
              onPressed: () {
                FlutterBoost.singleton.open('native://text-to-speech');
              },
            ),
            RaisedButton(
              child: Text('关于 About'),
              onPressed: () {
                FlutterBoost.singleton.open('native://web-view', urlParams: {
                  'url': 'https://github.com/alibaba/flutter_boost'
                });
              },
              color: Colors.orangeAccent,
            ),
          ],
        ),
      ),
    );
  }
}
