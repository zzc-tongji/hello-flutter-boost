import 'package:flutter/material.dart';
// import 'package:flutter_boost2/flutter_boost.dart';

import 'home.dart';

class Communication extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Communication'),
      ),
      body: Center(
        child: RaisedButton(
          child: Text('Open Home Page'),
          onPressed: () {
            Navigator.of(context)
                .push(MaterialPageRoute(builder: (context) => Home()));
          },
        ),
      ),
    );
  }
}
