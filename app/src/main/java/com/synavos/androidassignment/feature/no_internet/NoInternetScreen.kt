package com.synavos.androidassignment.feature.no_internet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.synavos.androidassignment.R
import com.synavos.androidassignment.ui.elements.ButtonPrimary
import com.synavos.androidassignment.ui.elements.CustomText
import com.synavos.androidassignment.util.pSp


@Composable
fun NoInternetView(onClick: () -> Unit) {
    ErrorView(
        onClick = onClick,
        title = R.string.no_internet,
        desc = R.string.no_internet_desc,
        btnText = R.string.try_again,
        imageSrc = R.drawable.ic_globe
    )
}

@Composable
fun ErrorView(
    onClick: () -> Unit,
    title: Int,
    desc: Int?,
    btnText: Int,
    imageSrc: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageSrc),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier.size(130.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        CustomText(
            text = stringResource(id = title), fontSize = 24.pSp, textAlign = TextAlign.Center
        )
        desc?.let {
            Spacer(modifier = Modifier.height(12.dp))
            CustomText(text = stringResource(id = it), fontSize = 16.pSp)
        }
        Spacer(modifier = Modifier.height(24.dp))
        ButtonPrimary(
            onClick = onClick, modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
        ) {
            CustomText(text = stringResource(id = btnText), fontSize = 18.pSp)
        }
    }
}