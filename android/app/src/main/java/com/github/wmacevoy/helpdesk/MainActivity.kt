package com.github.wmacevoy.helpdesk

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.wmacevoy.helpdesk.ui.theme.HelpdeskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelpdeskTheme {
                HelpdeskMainScreen()
            }
        }
    }
}

@Composable
fun HelpdeskMainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Helpdesk", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(32.dp))

        HelpdeskButton("Trouble Logging In") {
            // Navigate to a login help screen
        }

        HelpdeskButton("I Need to Print") {
            // Navigate to a printing help screen
        }

        HelpdeskButton("Phone a Friend") {
            val phoneNumber = "tel:1234567890" // Replace with actual support number
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
            it.startActivity(intent)
        }
    }
}

@Composable
fun HelpdeskButton(text: String, onClick: (ComponentActivity) -> Unit) {
    val activity = (LocalContext.current as? ComponentActivity)
    Button(
        onClick = { activity?.let { onClick(it) } },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(50.dp)
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}