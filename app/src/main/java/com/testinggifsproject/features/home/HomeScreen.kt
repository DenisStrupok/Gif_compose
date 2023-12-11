package com.testinggifsproject.features.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.testinggifsproject.R
import com.testinggifsproject.model.Gif
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    onItemClick: (String) -> Unit,
    actionOnExit: () -> Unit,
    actionOpenHistory: () -> Unit
) {
    val viewModel: HomeVM = koinViewModel()
    val gifsState by remember {
        viewModel.gifs
    }.collectAsState()

    val gifName by remember {
        viewModel.gifName
    }.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.screen_bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Text(
                text = stringResource(id = R.string.screen_home_title),
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.White,
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )
            Divider(
                color = Color.White,
                thickness = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = Color.Gray),
                value = gifName ?: "", onValueChange = { name ->
                    viewModel.setNameGif(name)
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.hint_enter))
                }
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(gifsState?.data.orEmpty()) { _, gif ->
                    GifItem(gif = gif, onItemClick = onItemClick)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                Button(
                    onClick = {
                        actionOnExit.invoke()
                    },
                    modifier = Modifier
                        .weight(0.3F)
                        .padding(end = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Black
                    ),
                    border = BorderStroke(width = 2.dp, color = Color.White)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.exit_left),
                        contentDescription = null
                    )
                }
                Button(
                    onClick = {
                        actionOpenHistory.invoke()
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Green
                    ),
                    border = BorderStroke(width = 2.dp, color = Color.White)
                ) {
                    Text(
                        text = stringResource(id = R.string.common_history)
                    )
                    Icon(painter = painterResource(id = R.drawable.next), contentDescription = null)
                }
            }
        }
    }
}

@Composable
fun GifItem(gif: Gif, onItemClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(16.dp),
                brush = Brush.linearGradient(colors = listOf(Color.White, Color.Red, Color.Black))
            )
            .padding(16.dp)
            .clickable {
                onItemClick(gif.id)
            }
    ) {
        AsyncImage(
            model = gif.images.original?.url, contentDescription = null,
            modifier = Modifier.padding(8.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}