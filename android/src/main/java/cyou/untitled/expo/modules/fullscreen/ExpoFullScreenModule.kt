package cyou.untitled.expo.modules.fullscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.facebook.react.bridge.UiThreadUtil
import expo.modules.kotlin.Promise
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import java.util.concurrent.FutureTask

class ExpoFullScreenModule : Module() {
  private val currentActivity
    get() = requireNotNull(appContext.currentActivity)

  private inline fun <R> runOnUiThreadSync(crossinline action: () -> R): R {
    if (UiThreadUtil.isOnUiThread()) return action()
    val task = FutureTask { return@FutureTask action() }
    UiThreadUtil.runOnUiThread(task)
    return task.get()
  }

  private inline fun <R> runOnUiThreadAsync(
    crossinline action: () -> R,
    crossinline onComplete: () -> Unit,
    crossinline onError: (err: Throwable) -> Unit) {
    if (UiThreadUtil.isOnUiThread()) {
      try {
        action()
        onComplete()
      } catch (err: Throwable) {
        onError(err)
      }
    }
    UiThreadUtil.runOnUiThread {
      try {
        action()
        onComplete()
      } catch (err: Throwable) {
        onError(err)
      }
    }
  }

  private fun enterFullScreen() {
    val window = currentActivity.window
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, window.decorView).let {
      it.hide(WindowInsetsCompat.Type.systemBars())
      it.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
  }

  private fun enterFullScreenSync() = runOnUiThreadSync { enterFullScreen() }

  private fun enterFullScreenAsync(
    onComplete: () -> Unit,
    onError: (err: Throwable) -> Unit
  ) = runOnUiThreadAsync({ enterFullScreen() }, onComplete, onError)

  private fun exitFullScreen() {
    val window = currentActivity.window
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(window, window.decorView).let {
      it.show(WindowInsetsCompat.Type.systemBars())
      it.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_TOUCH
    }
  }

  private fun exitFullScreenSync() = runOnUiThreadSync { exitFullScreen() }

  private fun exitFullScreenAsync(
    onComplete: () -> Unit,
    onError: (err: Throwable) -> Unit
  ) = runOnUiThreadAsync({ exitFullScreen() }, onComplete, onError)

  private fun getSystemUiVisibility(): Int {
    @Suppress("DEPRECATION")
    return currentActivity.window.decorView.systemUiVisibility
  }

  private fun setSystemUiVisibility(visibility: Int) {
    @Suppress("DEPRECATION")
    currentActivity.window.decorView.systemUiVisibility = visibility
  }

  private fun setSystemUiVisibilitySync(visibility: Int) =
    runOnUiThreadSync { setSystemUiVisibility(visibility) }

  private fun setSystemUiVisibilityAsync(
    visibility: Int,
    onComplete: () -> Unit,
    onError: (err: Throwable) -> Unit
  ) = runOnUiThreadAsync({ setSystemUiVisibility(visibility) }, onComplete, onError)

  @RequiresApi(Build.VERSION_CODES.R)
  private fun getSystemBarsAppearance(): Int? {
    return currentActivity.window.insetsController?.systemBarsAppearance
  }

  @RequiresApi(Build.VERSION_CODES.R)
  private fun setSystemBarsAppearance(appearance: Int, mask: Int) {
    requireNotNull(currentActivity.window.insetsController)
      .setSystemBarsAppearance(appearance, mask)
  }

  @RequiresApi(Build.VERSION_CODES.R)
  private fun setSystemBarsAppearanceSync(appearance: Int, mask: Int) =
    runOnUiThreadSync { setSystemBarsAppearance(appearance, mask) }

  @RequiresApi(Build.VERSION_CODES.R)
  private fun setSystemBarsAppearanceAsync(
    appearance: Int,
    mask: Int,
    onComplete: () -> Unit,
    onError: (err: Throwable) -> Unit
  ) = runOnUiThreadAsync({ setSystemBarsAppearance(appearance, mask) }, onComplete, onError)

  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  override fun definition() = ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('ExpoFullScreen')` in JavaScript.
    Name("ExpoFullScreen")

    // Sets constant properties on the module. Can take a dictionary or a closure that returns a dictionary.
    Constants(
      "SDK_INT" to Build.VERSION.SDK_INT
    )

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("enterFullScreenSync") {
      enterFullScreenSync()
    }

    Function("exitFullScreenSync") {
      exitFullScreenSync()
    }

    Function("getSystemUiVisibility") {
      return@Function getSystemUiVisibility()
    }

    Function("setSystemUiVisibilitySync") { visibility: Int ->
      setSystemUiVisibilitySync(visibility)
    }

    Function("getSystemBarsAppearance") {
      return@Function if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        getSystemBarsAppearance()
      } else {
        return@Function null
      }
    }

    Function("setSystemBarsAppearanceSync") { appearance: Int, mask: Int ->
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        setSystemBarsAppearanceSync(appearance, mask)
      }
    }

    // Defines a JavaScript function that always returns a Promise and whose native code
    // is by default dispatched on the different thread than the JavaScript runtime runs on.
    AsyncFunction("enterFullScreen") { promise: Promise ->
      enterFullScreenAsync(
        { promise.resolve(null) },
        { generalErrorHandler(promise, it) }
      )
    }

    AsyncFunction("exitFullScreen") { promise: Promise ->
      exitFullScreenAsync(
        { promise.resolve(null) },
        { generalErrorHandler(promise, it) }
      )
    }

    AsyncFunction("setSystemUiVisibility") { visibility: Int, promise: Promise ->
      setSystemUiVisibilityAsync(
        visibility,
        { promise.resolve(null) },
        { generalErrorHandler(promise, it) }
      )
    }

    AsyncFunction("setSystemBarsAppearance") { appearance: Int, mask: Int, promise: Promise ->
      setSystemBarsAppearanceAsync(
        appearance,
        mask,
        { promise.resolve(null) },
        { generalErrorHandler(promise, it) }
      )
    }
  }

  private fun generalErrorHandler(promise: Promise, err: Throwable) {
    if (err is IllegalArgumentException) promise.reject("NO_ACTIVITY", "Failed to get current activity", err)
    else promise.reject("ANDROID_ERROR", "Unknown error (probably from Android framework)", err)
  }
}
