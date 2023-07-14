package com.example.manageproducts

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.manageproducts.presentation.navigation.ProductListDestination
import com.example.manageproducts.presentation.navigation.SignUpDestination
import com.example.manageproducts.presentation.navigation.SuccessfulAuthDestination
import com.example.manageproducts.presentation.navigation.navRegistration
import com.example.manageproducts.ui.theme.ManageProductsTheme
import dagger.hilt.android.AndroidEntryPoint
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.handleDeeplinks
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var supabaseClient: SupabaseClient

    var navigate: () -> Unit = {}
    private lateinit var route: String

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supabaseClient.handleDeeplinks(intent = intent,
            onSessionSuccess = { userSession ->
                Log.d("LOGIN", "Log in successfully with user info: ${userSession.user}")
                route = userSession.user?.run {
                    SuccessfulAuthDestination.createRouteWithParam(
                        email = email ?: "",
                        createdAt = createdAt.toString()
                    )
                } ?: ""
                navigate()
            })
        setContent {
            val navController = rememberNavController()
            navigate = {
                navController.navigate(
                    route
                )
            }
            ManageProductsTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination
                Scaffold { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = ProductListDestination.route,
                        Modifier.padding(innerPadding)
                    ) {
                        navRegistration(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ManageProductsTheme {
        Greeting("Android")
    }
}