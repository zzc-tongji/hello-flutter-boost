import 'package:flutter/material.dart';
import 'package:flutter_boost2/flutter_boost.dart';

import 'communication.dart';

class Home extends StatelessWidget {
  Map _parameters;

  Home([Map params]) {
    _parameters = params;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Home'),
      ),
      body: Center(
        child: Column(
          children: [
            Text(_parameters.toString()),
            RaisedButton(
              child: Text('Open "flutter://communication" by Flutter'),
              onPressed: () {
                Navigator.of(context).push(MaterialPageRoute(
                    builder: (context) => Communication({'from': 'flutter'})));
              },
            ),
            RaisedButton(
              child: Text('Open "flutter://communication" by Native'),
              onPressed: () {
                FlutterBoost.singleton.open('native://flutter-container',
                    urlParams: {'url': 'flutter://communication?from=native'});
              },
            ),
          ],
        ),
      ),
    );
  }
}
