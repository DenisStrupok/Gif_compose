package com.testinggifsproject.features.history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testinggifsproject.R
import com.testinggifsproject.model.GifTesModel
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
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            if (list.value.isEmpty()) {
                EmptyState()
            } else {
                ShowListHistory(list = list.value,
                    actionOnDelete = { id ->
                        vm.deleteGifById(id)
                    },
                    actionClearHistory = {
                        vm.clearHistory()
                    })
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = {
                        actionBack.invoke()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(
                        width = 2.dp, color = Color.White
                    )
                ) {
                    Icon(painter = painterResource(id = R.drawable.back), contentDescription = null)
                    Text(
                        text = stringResource(id = R.string.common_back),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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
fun ShowListHistory(
    list: List<GifTesModel>,
    actionOnDelete: (String) -> Unit,
    actionClearHistory: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(list) { gif ->
            GifItem(gifModel = gif, onDeleteClick = { id ->
                actionOnDelete.invoke(id)
            })
        }
    }
    Text(
        text = stringResource(id = R.string.screen_clear_history),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                actionClearHistory.invoke()
            },
        textAlign = TextAlign.Center,
        color = Color.White,
        fontSize = 14.sp,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun GifItem(gifModel: GifTesModel, onDeleteClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(width = 2.dp, color = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        ) {
//        Image(
//            painter = painterResource(gifModel.url),
//            contentDescription = null,
//            modifier = Modifier
//                .size(50.dp)
//                .clip(RoundedCornerShape(8.dp)) // заокруглення кутів для картинки
//
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = gifModel.title ?: "No Title",
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp),
                fontWeight = FontWeight.Bold
            )

            IconButton(
                onClick = { onDeleteClick.invoke(gifModel.id ?: "") },
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
    }
}