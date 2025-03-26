import 'package:flutter/material.dart';

class CallPage extends StatelessWidget {
  const CallPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Call Help Desk')),
      body: Center(
        child: Text('VoIP call UI goes here'),
      ),
    );
  }
}