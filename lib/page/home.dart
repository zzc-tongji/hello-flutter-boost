import 'package:flutter/material.dart';
// import 'package:flutter_boost2/flutter_boost.dart';

import 'communication.dart';

class Home extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Hello'),
      ),
      body: Center(
        child: RaisedButton(
          child: Text('Open Communication Page'),
          onPressed: () {
            Navigator.of(context)
                .push(MaterialPageRoute(builder: (context) => Communication()));
          },
        ),
      ),
    );
  }
}
