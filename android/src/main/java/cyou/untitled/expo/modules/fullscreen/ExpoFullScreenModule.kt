package cyou.untitled.expo.modules.fullscreen

import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import expo.modules.kotlin.Promise
import com.facebook.react.bridge.UiThreadUtil
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import java.util.concurrent.FutureTask

class ExpoFullScreenModule : Module() {
  private val currentActivity
    get() = requireNotNull(appContext.currentActivity)

  private fun enterFullScreen() {
    val window = currentActivity.window
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, window.decorView).let {
      it.hide(WindowInsetsCompat.Type.systemBars())
      it.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
  }

  private fun enterFullScreenSync() {
    if (UiThreadUtil.isOnUiThread()) return enterFullScreen()
    val task = FutureTask { enterFullScreen() }
    UiThreadUtil.runOnUiThread(task)
    task.get()
  }

  private fun enterFullScreenAsync(
    onComplete: () -> Unit,
    onError: (err: Throwable) -> Unit
  ) {
    if (UiThreadUtil.isOnUiThread()) {
      try {
        enterFullScreen()
        onComplete()
      } catch (err: Throwable) {
        onError(err)
      }
    }
    UiThreadUtil.runOnUiThread {
      try {
        enterFullScreen()
        onComplete()
      } catch (err: Throwable) {
        onError(err)
      }
    }
  }
  private fun exitFullScreen() {
    val window = currentActivity.window
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(window, window.decorView).show(WindowInsetsCompat.Type.systemBars())
  }

  private fun exitFullScreenSync() {
    if (UiThreadUtil.isOnUiThread()) return exitFullScreen()
    val task = FutureTask { exitFullScreen() }
    UiThreadUtil.runOnUiThread(task)
    task.get()
  }

  private fun exitFullScreenAsync(
    onComplete: () -> Unit,
    onError: (err: Throwable) -> Unit
  ) {
    if (UiThreadUtil.isOnUiThread()) {
      try {
        exitFullScreen()
        onComplete()
      } catch (err: Throwable) {
        onError(err)
      }
    }
    UiThreadUtil.runOnUiThread {
      try {
        exitFullScreen()
        onComplete()
      } catch (err: Throwable) {
        onError(err)
      }
    }
  }

  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  override fun definition() = ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('ExpoFullScreen')` in JavaScript.
    Name("ExpoFullScreen")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("enterFullScreenSync") {
      enterFullScreenSync()
    }

    Function("exitFullScreenSync") {
      exitFullScreenSync()
    }

    // Defines a JavaScript function that always returns a Promise and whose native code
    // is by default dispatched on the different thread than the JavaScript runtime runs on.
    AsyncFunction("enterFullScreen") { promise: Promise ->
      enterFullScreenAsync(
        { promise.resolve(null) },
        {
          if (it is IllegalArgumentException) promise.reject("NO_ACTIVITY", "Failed to get current activity", it)
          else promise.reject("ANDROID_ERROR", "Unknown error (probably from Android framework)", it)
        }
      )
    }

    AsyncFunction("exitFullScreen") { promise: Promise ->
      exitFullScreenAsync(
        { promise.resolve(null) },
        {
          if (it is IllegalArgumentException) promise.reject("NO_ACTIVITY", "Failed to get current activity", it)
          else promise.reject("ANDROID_ERROR", "Unknown error (probably from Android framework)", it)
        }
      )
    }
  }
}
