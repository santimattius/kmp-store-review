package com.santimattius.kmp.skeleton

import App
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.santimattius.kmp.skeleton.core.data.source.ReviewDataSource
import com.santimattius.kmp.skeleton.core.data.source.ReviewInitParams
import com.santimattius.kmp.skeleton.core.data.source.getReviewDataSource
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(platformModules = listOf(androidModule(this)))
        }
    }
}

fun androidModule(entryPoint: Activity) = module {
    single<ReviewDataSource> { getReviewDataSource(ReviewInitParams(entryPoint)) }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}