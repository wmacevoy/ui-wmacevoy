import 'package:flutter/material.dart';

class ChatPage extends StatelessWidget {
  const ChatPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Help Desk Chat')),
      body: Center(
        child: Text('Chat UI goes here'),
      ),
    );
  }
}