import 'package:flutter/material.dart';
import 'package:flutter_boost2/flutter_boost.dart';

import 'home.dart';

class Communication extends StatelessWidget {
  final Map _parameters;

  Communication([this._parameters]);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('通信机制 Communication'),
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
              child: Text('主页 Home'),
              onPressed: () {
                Navigator.of(context)
                    .push(MaterialPageRoute(builder: (context) => Home()));
              },
              color: Colors.lightBlueAccent,
            ),
            RaisedButton(
              child: Text('主页 Home'),
              onPressed: () {
                FlutterBoost.singleton.open('native://flutter-container',
                    urlParams: {'url': 'flutter://home'});
              },
              color: Colors.lightGreenAccent,
            ),
          ],
        ),
      ),
    );
  }
}
