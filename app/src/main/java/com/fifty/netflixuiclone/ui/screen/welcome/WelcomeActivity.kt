package com.fifty.netflixuiclone.ui.screen.welcome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fifty.netflixuiclone.R
import com.fifty.netflixuiclone.ui.screen.dashboard.DashboardActivity
import com.fifty.netflixuiclone.ui.screen.welcome.viewpager.OnBoardingScreen
import com.fifty.netflixuiclone.util.UiConstants
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        setContent {
            val scope = rememberCoroutineScope()
            val sheetState = rememberBottomSheetState(
                initialValue = BottomSheetValue.Collapsed, animationSpec = tween(
                    durationMillis = 500, delayMillis = 0
                )
            )
            val scaffoldState = rememberBottomSheetScaffoldState(
                bottomSheetState = sheetState
            )
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetShape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp),
                sheetContent = {
                    BottomSheetContent(sheetState, context)
                },
                sheetPeekHeight = 0.dp
            ) {
                Surface {
                    Column(Modifier.background(Color.Black)) {
                        Box(modifier = Modifier.weight(90f)) {
                            OnBoardingScreen()
                            TopAppBarWithPrivacyAndSignInButtons()
                        }
                        Box(
                            modifier = Modifier
                                .weight(10f)
                                .padding(16.dp)
                        ) {
                            FullWidthButton(
                                getString(R.string.get_started),
                                containerColor = Color.Red,
                                contentColor = Color.White
                            ) {
                                scope.launch {
                                    sheetState.expand()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopAppBarWithPrivacyAndSignInButtons() {
    Box(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
    ) {
        Row {
            Box(
                modifier = Modifier
                    .weight(40f)
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.netflix_logo),
                    contentDescription = "Netflix Logo"
                )
            }
            Box(
                modifier = Modifier
                    .weight(60f)
                    .fillMaxHeight()
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "PRIVACY",
                        modifier = Modifier.padding(10.dp),
                        fontFamily = UiConstants.latoFontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text(
                        text = "SIGN IN",
                        modifier = Modifier.padding(10.dp),
                        fontFamily = UiConstants.latoFontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Button(colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Gray
                    ), onClick = {}) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_menu_dots_vertical),
                            colorFilter = ColorFilter.tint(Color.White),
                            contentDescription = "Sign In Menu"
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun FullWidthButton(
    buttonText: String,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp), onClick = onClick,
        shape = RoundedCornerShape(2.dp), colors = ButtonDefaults.buttonColors(
            containerColor = containerColor, contentColor = contentColor
        )
    ) {
        Text(text = buttonText)
    }
}


@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun BottomSheetContent(sheetState: BottomSheetState, context: Context) {
    var emailTextFieldState by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(26.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Close button inside bottom sheet.
            Row(
                horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            sheetState.collapse()
                        }
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_close_24),
                        contentDescription = stringResource(R.string.close_button_for_bottom_sheet),
                        colorFilter = ColorFilter.tint(Color.Gray),
                    )
                }
            }
            // Ready to watch text view.
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.ready_to_watch),
                fontSize = 28.sp,
                fontFamily = UiConstants.latoFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            // Enter your email text description.
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(R.string.enter_your_email_to_sign_in_or_create_account),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = colorResource(R.color.text_gray),
                fontFamily = UiConstants.latoFontFamily,
            )
            // Text field to input email address.
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = emailTextFieldState,
                onValueChange = {
                    emailTextFieldState = it
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray
                ),
                label = {
                    Text(text = stringResource(R.string.email))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            )

            // Get started Button on bottom sheet.
            Spacer(modifier = Modifier.height(30.dp))
            FullWidthButton(
                buttonText = stringResource(R.string.get_started),
                containerColor = Color.Red, contentColor = Color.White
            ) {
                context.startActivity(Intent(context, DashboardActivity::class.java))
            }
        }
    }
}