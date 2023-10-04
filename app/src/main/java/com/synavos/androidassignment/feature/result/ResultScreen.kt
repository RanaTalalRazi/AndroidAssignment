package com.synavos.androidassignment.feature.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.synavos.androidassignment.R
import com.synavos.androidassignment.data.model.response.age.AgeResponse
import com.synavos.androidassignment.feature.home.HomeScreen
import com.synavos.androidassignment.feature.no_internet.NoInternetView
import com.synavos.androidassignment.ui.CustomLoader
import com.synavos.androidassignment.ui.MainViewModel
import com.synavos.androidassignment.ui.elements.CustomText
import com.synavos.androidassignment.ui.elements.UserList
import com.synavos.androidassignment.ui.state.UiState
import com.synavos.androidassignment.ui.theme.AndroidAssignmentTheme
import com.synavos.androidassignment.ui.theme.BgColor
import com.synavos.androidassignment.ui.theme.font
import com.synavos.androidassignment.util.nonScaledSp
import com.synavos.androidassignment.util.pSp

@Composable
fun ResultScreen(viewModel: ResultViewModel = hiltViewModel(),mainViewModel: MainViewModel) {

    val uiState by viewModel.uiState.collectAsState()
    val userInput=mainViewModel.userInput.value?:""
    Scaffold { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(BgColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column {

                    CustomText(
                        text = stringResource(id = R.string.result),
                        fontSize = 32.nonScaledSp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    LaunchedEffect(key1 = userInput ){
                        viewModel.getHistList(userInput)

                    }
                    uiState.let { state ->
                        when (state) {
                            is UiState.LOADING -> {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    CustomLoader()
                                }
                            }

                            is UiState.SUCCESS<*> -> {
                                val response = state.data as? List<AgeResponse>
                                if (!response.isNullOrEmpty())
                                    UserList(usersData = response)
                                else
                                    Box(modifier = Modifier.fillMaxSize()) {
                                        CustomText(
                                            modifier = Modifier
                                                .align(Alignment.Center),
                                            fontSize = 16.nonScaledSp,
                                            fontWeight = FontWeight.Medium,
                                            text = stringResource(id = R.string.no_data_found)
                                        )
                                    }

                            }

                            is UiState.OFFLINE -> {
                                Box(modifier = Modifier.fillMaxSize()) {
                                NoInternetView { viewModel.updateState(UiState.NORMAL) }}
                            }

                            is UiState.ERROR -> {
                                val error = (uiState as UiState.ERROR).error
                                val errorMsg = error ?: stringResource(id = R.string.generic_error)
                                Box(modifier = Modifier.fillMaxSize()) {
                                CustomText(
                                    text = errorMsg,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    color = Color.Red,
                                    fontSize = 16.pSp,
                                    textAlign = TextAlign.Center
                                )}
                            }


                            else -> {

                            }
                        }
                    }


                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignUpScreen() {
    AndroidAssignmentTheme {
        ResultScreen(mainViewModel = hiltViewModel())
    }
}



