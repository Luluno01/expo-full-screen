package cyou.untitled.expo.modules.fullscreen

import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class ExpoFullScreenModule : Module() {
  private val currentActivity
    get() = requireNotNull(appContext.currentActivity)

  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  override fun definition() = ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('ExpoFullScreen')` in JavaScript.
    Name("ExpoFullScreen")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("enterFullScreen") {
      val window = currentActivity.window
      WindowCompat.setDecorFitsSystemWindows(window, false)
      WindowInsetsControllerCompat(window, window.decorView).let {
        it.hide(WindowInsetsCompat.Type.systemBars())
        it.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
      }
    }

    Function("exitFullScreen") {
      val window = currentActivity.window
      WindowCompat.setDecorFitsSystemWindows(window, true)
      WindowInsetsControllerCompat(window, window.decorView)
        .show(WindowInsetsCompat.Type.systemBars())
    }
  }
}
