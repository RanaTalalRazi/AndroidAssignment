package com.synavos.androidassignment.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

val Int.nonScaledSp
    @Composable
    get() = (this / LocalDensity.current.fontScale).sp

val Int.pSp
    @Composable
    get() = run {
        this.nonScaledSp
    }

