package com.testinggifsproject.features.detail

import android.content.Context
import android.graphics.ImageDecoder
import android.os.Build
import android.widget.ImageView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.testinggifsproject.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    gifId: String? = null,
    actionBackClick: () -> Unit,
) {
    val viewModel: DetailVM = koinViewModel()
    gifId?.let { id ->
        viewModel.setGifId(id)
    }
    val gif = viewModel.gif.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.screen_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.screen_detail_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                color = Color.White,
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )

            Divider(
                color = Color.White, thickness = 2.dp, modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = stringResource(
                    id = R.string.screen_detail_name_gif,
                    gif.value?.data?.title ?: ""
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color.White,
                fontSize = 18.sp
            )

            val loader = ImageLoader.Builder(LocalContext.current).components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }.build()

            val painter = rememberImagePainter(
                data = gif.value?.data?.embedUrl,
                imageLoader = loader,
                builder = {
                    size(Size.ORIGINAL)
                    crossfade(true)
                    placeholder(android.R.drawable.ic_menu_gallery)
                }
            )

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(16.dp)
            )


            Text(
                text = stringResource(
                    id = R.string.screen_detail_author,
                    gif.value?.data?.username ?: ""
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color.White
            )

            Button(
                onClick = {
                    actionBackClick.invoke()
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                border = BorderStroke(width = 2.dp, color = Color.White),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
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