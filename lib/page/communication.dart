import 'package:flutter/material.dart';
import 'package:flutter_boost2/flutter_boost.dart';

import 'home.dart';

class Communication extends StatelessWidget {
  Map _parameters;

  Communication([Map params]) {
    _parameters = params;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Communication'),
      ),
      body: Center(
        child: Column(
          children: [
            Text(_parameters.toString()),
            RaisedButton(
              child: Text('Open "flutter://home" by Flutter'),
              onPressed: () {
                Navigator.of(context).push(MaterialPageRoute(
                    builder: (context) => Home({'from': 'flutter'})));
              },
            ),
            RaisedButton(
              child: Text('Open "flutter://home" by Native'),
              onPressed: () {
                FlutterBoost.singleton.open('native://flutter-container',
                    urlParams: {'url': 'flutter://home?from=native'});
              },
            ),
          ],
        ),
      ),
    );
  }
}
