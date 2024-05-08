package expo.modules.volumebuttons.example

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent

import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
import com.facebook.react.defaults.DefaultReactActivityDelegate

import expo.modules.ReactActivityDelegateWrapper
import expo.modules.volumebuttons.VolumeButtonsModule
import expo.modules.volumebuttons.VolumeButtonsObserver

class MainActivity : ReactActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    // Set the theme to AppTheme BEFORE onCreate to support
    // coloring the background, status bar, and navigation bar.
    // This is required for expo-splash-screen.
    setTheme(R.style.AppTheme);

    super.onCreate(null)
  }

  override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode !== KeyEvent.KEYCODE_VOLUME_DOWN && keyCode !== KeyEvent.KEYCODE_VOLUME_UP) || VolumeButtonsObserver.Companion.instance.observersList.isEmpty()) {
            return super.onKeyDown(keyCode, event)
        }
        if(keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            VolumeButtonsObserver.Companion.instance.newestEvent = "UP"
        }
        if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            VolumeButtonsObserver.Companion.instance.newestEvent = "DOWN"
        }
        return true
  }

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  override fun getMainComponentName(): String = "main"

  /**
   * Returns the instance of the [ReactActivityDelegate]. We use [DefaultReactActivityDelegate]
   * which allows you to enable New Architecture with a single boolean flags [fabricEnabled]
   */
  override fun createReactActivityDelegate(): ReactActivityDelegate {
    return ReactActivityDelegateWrapper(
          this,
          BuildConfig.IS_NEW_ARCHITECTURE_ENABLED,
          object : DefaultReactActivityDelegate(
              this,
              mainComponentName,
              fabricEnabled
          ){})
  }

  /**
    * Align the back button behavior with Android S
    * where moving root activities to background instead of finishing activities.
    * @see <a href="https://developer.android.com/reference/android/app/Activity#onBackPressed()">onBackPressed</a>
    */
  override fun invokeDefaultOnBackPressed() {
      if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
          if (!moveTaskToBack(false)) {
              // For non-root activities, use the default implementation to finish them.
              super.invokeDefaultOnBackPressed()
          }
          return
      }

      // Use the default back button implementation on Android S
      // because it's doing more than [Activity.moveTaskToBack] in fact.
      super.invokeDefaultOnBackPressed()
  }
}
