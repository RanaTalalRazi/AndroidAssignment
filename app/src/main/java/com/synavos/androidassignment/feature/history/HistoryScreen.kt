package com.synavos.androidassignment.feature.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.synavos.androidassignment.R
import com.synavos.androidassignment.data.model.response.age.AgeResponse
import com.synavos.androidassignment.ui.CustomLoader
import com.synavos.androidassignment.ui.elements.CustomText
import com.synavos.androidassignment.ui.elements.UserList
import com.synavos.androidassignment.ui.state.UiState
import com.synavos.androidassignment.ui.theme.AndroidAssignmentTheme
import com.synavos.androidassignment.ui.theme.BgColor
import com.synavos.androidassignment.util.nonScaledSp

@Composable
fun HistoryScreen(viewModel: HistoryViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()
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
                        text = stringResource(id = R.string.history),
                        fontSize = 32.nonScaledSp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    viewModel.getHistList()
                    uiState.let { state ->
                        when (state) {
                            is UiState.LOADING -> {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    CustomLoader()
                                }
                            }

                            is UiState.SUCCESS<*> -> {
                                val response = state.data as? List<AgeResponse>
                                if (!response.isNullOrEmpty()) UserList(usersData = response)
                                else Box(modifier = Modifier.fillMaxSize()) {
                                    CustomText(
                                        modifier = Modifier.align(Alignment.Center),
                                        fontSize = 16.nonScaledSp,
                                        fontWeight = FontWeight.Medium,
                                        text = stringResource(id = R.string.no_data_found)
                                    )
                                }

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
        HistoryScreen()
    }
}



