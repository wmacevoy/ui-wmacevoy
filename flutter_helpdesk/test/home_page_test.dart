import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/mockito.dart';
import 'package:flutter_helpdesk/pages/home_page.dart';
import 'package:flutter_helpdesk/pages/call_page.dart';
import 'package:flutter_helpdesk/pages/chat_page.dart';
import 'package:flutter_helpdesk/pages/tickets_page.dart';

// Mock NavigatorObserver to monitor navigation events
class MockNavigatorObserver extends Mock implements NavigatorObserver {}

void main() {
  group('HomePage Navigation Tests', () {
    late MockNavigatorObserver mockObserver;

    setUp(() {
      mockObserver = MockNavigatorObserver();
    });

    Future<void> _buildHomePage(WidgetTester tester) async {
      await tester.pumpWidget(
        MaterialApp(
          home: HomePage(onThemeChanged: (_) {}),
          navigatorObservers: [mockObserver],
        ),
      );
    }

    testWidgets('Navigates to CallPage when Call Support is tapped', (WidgetTester tester) async {
      await _buildHomePage(tester);

      // Tap the 'Call Support' option
      await tester.tap(find.text('Call Support'));
      await tester.pumpAndSettle();
      expect(find.byType(CallPage), findsOneWidget);
    });

    testWidgets('Navigates to ChatPage when Chat with Us is tapped', (WidgetTester tester) async {
      await _buildHomePage(tester);
      await tester.tap(find.text('Chat with Us'));
      await tester.pumpAndSettle();
      expect(find.byType(ChatPage), findsOneWidget);
    });

    testWidgets('Navigates to TicketsPage when Tickets is tapped', (WidgetTester tester) async {
      await _buildHomePage(tester);
      await tester.tap(find.text('Tickets'));
      await tester.pumpAndSettle();
      expect(find.byType(TicketsPage), findsOneWidget);
    });
  });
}