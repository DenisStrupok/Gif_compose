package com.testinggifsproject.features.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.testinggifsproject.ui.theme.TestingGifsProjectTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainVM>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
        viewModel.test()
        lifecycleScope.launch {
            viewModel.test.collect { test ->
                Toast.makeText(this@MainActivity, "$test", Toast.LENGTH_LONG).show()
            }
        }
    }
}
