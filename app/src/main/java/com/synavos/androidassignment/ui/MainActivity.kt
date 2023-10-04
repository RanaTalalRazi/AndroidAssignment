package com.synavos.androidassignment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.synavos.androidassignment.feature.history.HistoryScreen
import com.synavos.androidassignment.feature.home.HomeScreen
import com.synavos.androidassignment.feature.result.ResultScreen
import com.synavos.androidassignment.ui.navigation.NavBarItems
import com.synavos.androidassignment.ui.navigation.NavRoutes
import com.synavos.androidassignment.ui.theme.AndroidAssignmentTheme
import com.synavos.androidassignment.ui.theme.font
import com.synavos.androidassignment.util.nonScaledSp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


val animationTween = 700

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    var navController: NavHostController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            MainScreen(navController = navController, viewModel)
        }
    }
}


@Composable
fun MainScreen(
    navController: NavHostController? = rememberNavController(), viewModel: MainViewModel
) {
    val currentOffset by remember { mutableStateOf(0f) }

    val showBottomBar = rememberSaveable {
        mutableStateOf(false)
    }
    navController!!.addOnDestinationChangedListener { _, destination, _ ->
        CoroutineScope(Dispatchers.IO).launch {
            showBottomBar.value = when (destination.route) {
                NavRoutes.Home.route, NavRoutes.History.route, NavRoutes.Result.route -> {
                    true
                }

                else -> {
                    false
                }
            }
        }

    }
    Scaffold(content = { paddingValues ->
        Box(modifier = Modifier.offset { IntOffset(0, currentOffset.roundToInt()) }) {
            NavigationHost(
                navController = navController,
                contentPadding = paddingValues,
                viewModel = viewModel
            )
        }
    }, bottomBar = {
        AnimatedVisibility(
            visible = showBottomBar.value, enter = slideInVertically(
                initialOffsetY = { it }, animationSpec = tween(100)
            ), exit = slideOutVertically(
                targetOffsetY = { it }, animationSpec = tween(100)
            )
        ) {
            BottomNavigationBar(navController = navController)
        }
    }, containerColor = MaterialTheme.colorScheme.background
    )
}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val initialItem = navBackStackEntry.value?.destination?.route
    BottomAppBar(modifier = Modifier.height(58.dp), containerColor = Color.White, actions = {
        NavBarItems.getBarItems(LocalContext.current).forEach { navItem ->
            val selected =
                (navItem.route.split("/").getOrNull(0) == initialItem?.split("/")?.getOrNull(0))

            val icon = navItem.image
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                IconButton(onClick = {
                    if (!selected) {
                        navController.navigate(navItem.route)
                    }
                }, modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box {
                            if (selected) Icon(
                                imageVector = icon,
                                contentDescription = navItem.title,
                                tint = MaterialTheme.colorScheme.primary,
                            )
                            else Icon(
                                imageVector = icon,
                                contentDescription = navItem.title,
                                tint = Color.Gray,
                            )

                        }
                        Text(
                            fontFamily = font,
                            text = navItem.title,
                            fontSize = 10.nonScaledSp,
                            style = TextStyle(color = if (selected) MaterialTheme.colorScheme.primary else Color.Gray)
                        )
                    }

                }
            }
        }
    })
}

@Composable
fun NavigationHost(
    navController: NavHostController, contentPadding: PaddingValues, viewModel: MainViewModel
) {

    var route = NavRoutes.Home.route


    NavHost(navController = navController,
        startDestination = route,
        modifier = Modifier.padding(contentPadding),
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(animationTween)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(animationTween)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(animationTween)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(animationTween)
            )
        }) {

        composable(
            NavRoutes.MainScreen.route,
        ) {
            MainScreen(navController, viewModel)
        }
        composable(NavRoutes.Home.route) {
            HomeScreen(mainViewModel = viewModel)
        }
        composable(NavRoutes.History.route) {
            HistoryScreen()
        }
        composable(NavRoutes.Result.route) {
            ResultScreen(mainViewModel = viewModel)
        }

    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidAssignmentTheme {}


}

@Composable
fun CustomLoader() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .fillMaxSize(), color = MaterialTheme.colorScheme.primary
        )
    }
}
