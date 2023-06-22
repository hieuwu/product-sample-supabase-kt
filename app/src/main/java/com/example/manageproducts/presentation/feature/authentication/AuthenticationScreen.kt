package com.example.manageproducts.presentation.feature.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.manageproducts.presentation.navigation.AuthenticationDestination
import com.example.manageproducts.presentation.navigation.SignUpDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthenticationViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colorScheme.primary,
                title = {
                    Text(
                        text = "Login",
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                },
            )
        }
    ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues).padding(20.dp)) {
            val email = viewModel.email.collectAsState(initial = "")
            val password = viewModel.password.collectAsState()
            androidx.compose.material.OutlinedTextField(
                label = {
                    Text(
                        text = "Email",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                maxLines = 1,
                shape = RoundedCornerShape(32),
                modifier = modifier.fillMaxWidth(),
                value = email.value,
                onValueChange = {
                    viewModel.onEmailChange(it)
                },
            )
            androidx.compose.material.OutlinedTextField(
                label = {
                    Text(
                        text = "Password",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                maxLines = 1,
                shape = RoundedCornerShape(32),
                modifier = modifier.fillMaxWidth()
                    .padding(top = 12.dp),
                value = password.value,
                onValueChange = {
                    viewModel.onPasswordChange(it)
                },
            )
            Button(modifier = modifier.fillMaxWidth().padding(top = 12.dp),
                onClick = {
                    viewModel.onLogin()
            }) {
                Text("Login")
            }
            OutlinedButton(modifier = modifier.fillMaxWidth().padding(top = 12.dp), onClick = {
                navController.navigate(SignUpDestination.route)
            }) {
                Text("Sign up")
            }
        }
    }
}