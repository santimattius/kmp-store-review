import androidx.compose.ui.window.ComposeUIViewController
import com.santimattius.kmp.skeleton.core.data.source.ReviewDataSource
import com.santimattius.kmp.skeleton.core.data.source.ReviewInitParams
import com.santimattius.kmp.skeleton.core.data.source.getReviewDataSource
import org.koin.dsl.module

fun MainViewController() = ComposeUIViewController { App(platformModules = listOf(iOSModule())) }


fun iOSModule() = module {
    single<ReviewDataSource> { getReviewDataSource(ReviewInitParams("id123456789")) }
}
