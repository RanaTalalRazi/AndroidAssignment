package com.synavos.androidassignment.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.synavos.androidassignment.R
import com.synavos.androidassignment.feature.no_internet.NoInternetView
import com.synavos.androidassignment.ui.CustomLoader
import com.synavos.androidassignment.ui.MainViewModel
import com.synavos.androidassignment.ui.elements.ButtonPrimary
import com.synavos.androidassignment.ui.elements.CustomText
import com.synavos.androidassignment.ui.elements.TextFieldPrimary
import com.synavos.androidassignment.ui.state.UiState
import com.synavos.androidassignment.ui.theme.AndroidAssignmentTheme
import com.synavos.androidassignment.ui.theme.BgColor
import com.synavos.androidassignment.util.nonScaledSp
import com.synavos.androidassignment.util.pSp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), mainViewModel: MainViewModel) {

    val uiState by viewModel.uiState.collectAsState()
    val name = rememberSaveable {
        mutableStateOf(mainViewModel.userInput.value?:"")
    }

    val isButtonEnable = rememberSaveable { mutableStateOf(mainViewModel.userInput.value?.isNotEmpty()?:false) }
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
                        text = stringResource(id = R.string.home),
                        fontSize = 32.nonScaledSp,
                        fontWeight = FontWeight.Medium
                    )

                    TextFieldPrimary(
                        onValueChange = { value ->
                            name.value = value
                            mainViewModel.userInput.value = value
                            shouldEnableButton(
                                isButtonEnable, name.value
                            )
                        },
                        label = { CustomText(text = stringResource(id = R.string.name)) },
                        value = name.value,
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        maxLines = 1,
                        errorMessage = stringResource(id = R.string.please_enter_name),
                        bgColor = Color.White
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    ButtonPrimary(
                        onClick = {
                            if (shouldEnableButton(
                                    isButtonEnable,
                                    name.value
                                )
                            ) {
                                viewModel.getAge(name.value)
                            }
                        },
                        modifier = Modifier
                            .height(48.dp)
                            .fillMaxWidth(),
                        enabled = isButtonEnable.value
                    ) {
                        CustomText(text = stringResource(id = R.string.submit))
                    }
                    uiState.let { state ->
                        when (state) {
                            is UiState.LOADING -> {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    CustomLoader()
                                }
                            }

                            is UiState.ERROR -> {
                                val error = (uiState as UiState.ERROR).error
                                val errorMsg = error ?: stringResource(id = R.string.generic_error)
                                CustomText(
                                    text = errorMsg,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    color = Color.Red,
                                    fontSize = 16.pSp,
                                    textAlign = TextAlign.Center
                                )
                            }

                            is UiState.SUCCESS<*> -> {
                            }

                            is UiState.OFFLINE -> {
                                NoInternetView { viewModel.updateState(UiState.NORMAL) }
                            }

                            else -> {}
                        }
                    }


                }
            }
        }


    }
}

fun shouldEnableButton(
    state: MutableState<Boolean>,
    name: String,
): Boolean {
    state.value = (name.isNotEmpty())
    return state.value
}

@Preview
@Composable
fun PreviewSignUpScreen() {
    AndroidAssignmentTheme {
        HomeScreen(mainViewModel = hiltViewModel())
    }
}