package com.subject.millie.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.subject.domain.model.HeadLine
import com.subject.millie.ui.detail.DetailRoute
import com.subject.millie.ui.list.ListRoute
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

enum class Route(
    val route: String,
) {
    LIST(route = "list"),
    WEBVIEW(route = "webview"),
}

const val CONST_HEAD_LINE_URL = "ConstHeadLineURL"
fun NavController.navigateWebView(item: HeadLine) {
    val encodedUrl = URLEncoder.encode("${item.url}", StandardCharsets.UTF_8.toString())
    navigate("${Route.WEBVIEW.route}/$encodedUrl")
}

@Composable
fun MainNavigationComponent(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Route.LIST.route
    ) {
        composable(Route.LIST.route) {
            ListRoute(navController = navController)
        }
        composable("${Route.WEBVIEW.route}/{$CONST_HEAD_LINE_URL}",
            arguments = listOf(
                navArgument(name = "$CONST_HEAD_LINE_URL") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )) {
            DetailRoute(navController = navController)
        }
    }
}
