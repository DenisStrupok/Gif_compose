package com.testinggifsproject.features.history

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.testinggifsproject.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen(
    actionBack: () -> Unit,
) {
    val vm: HistoryVM = koinViewModel()
    vm.getListHistory()
    val list = vm.listHistory.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.screen_bg), contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            Text(
                text = stringResource(id = R.string.common_history),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                color = Color.White,
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )
            Divider(
                color = Color.White, thickness = 2.dp, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            if (list.value.isEmpty()) {
                EmptyState()
            } else {
                ShowList()
            }
            Button(
                onClick = {
                    actionBack.invoke()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(text = stringResource(id = R.string.common_back), textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        //.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.list),
            contentDescription = null,
            modifier = Modifier
                .width(160.dp)
                .height(180.dp)
                .padding(16.dp),
            tint = Color.White
        )
        Text(
            text = stringResource(id = R.string.screen_history_empty_message),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Composable
fun ShowList() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.screen_clear_history),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic
        )
    }
}