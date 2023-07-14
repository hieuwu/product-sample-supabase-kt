package com.example.manageproducts.presentation.feature.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun SignInSuccessScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    email: String,
    createdAt: String,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = "Sign in successfully!")
        Text(text = "Email $email")
        Text(text = "Created at $createdAt")
    }
}
