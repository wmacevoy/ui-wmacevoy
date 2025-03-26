import 'package:flutter/material.dart';
import '../widgets/helpdesk_option.dart';
import 'call_page.dart';
import 'chat_page.dart';
import 'tickets_page.dart';


class HomePage extends StatelessWidget {
  final ValueChanged<bool> onThemeChanged;

  const HomePage({super.key, required this.onThemeChanged});

  @override
  Widget build(BuildContext context) {
    bool isDarkMode = Theme.of(context).brightness == Brightness.dark;

    return Scaffold(
      appBar: AppBar(
        title: const Text('Help Desk'),
        actions: [
          Switch(
            value: isDarkMode,
            onChanged: onThemeChanged,
          ),
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            HelpDeskOption(
              icon: Icons.phone_in_talk,
              title: 'Call Support',
              description: 'Speak with a support agent.',
              onTap: () => Navigator.push(
                context,
                MaterialPageRoute(builder: (_) => CallPage()),
              ),
            ),
            HelpDeskOption(
              icon: Icons.chat,
              title: 'Chat with Us',
              description: 'Instant messaging with support.',
              onTap: () => Navigator.push(
                context,
                MaterialPageRoute(builder: (_) => ChatPage()),
              ),
            ),
            HelpDeskOption(
              icon: Icons.report_problem,
              title: 'Tickets',
              description: 'Make or track an issue.',
              onTap: () => Navigator.push(
                context,
                MaterialPageRoute(builder: (_) => TicketsPage()),
              ),
            ),
          ],
        ),
      ),
    );
  }
}