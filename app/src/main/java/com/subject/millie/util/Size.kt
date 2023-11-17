package com.subject.millie.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

/**
 * @return ScreenWidth Px
 */
@Composable
@NonRestartableComposable
fun getScreenWidth(): Int {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    return with(LocalDensity.current) { screenWidth.toPx().toInt() }
}

/**
 * @return ScreenHeight Px
 */
@Composable
@NonRestartableComposable
fun getScreenHeight(): Int {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    return with(LocalDensity.current) { screenHeight.toPx().toInt() }
}

@Composable
@NonRestartableComposable
fun getScreenSize() = Pair(getScreenWidth(), getScreenHeight())
