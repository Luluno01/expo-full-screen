import { Platform, requireNativeModule } from 'expo-modules-core'


export interface ExpoFullScreenModule {
  enterFullScreen(): void
  exitFullScreen(): void
}

const dummyImpl: ExpoFullScreenModule = {
  enterFullScreen() {},
  exitFullScreen() {}
}

// It loads the native module object from the JSI or falls back to
// the bridge module (from NativeModulesProxy) if the remote debugger is on.
export default Platform.OS === 'android'
  ? requireNativeModule('ExpoFullScreen') as ExpoFullScreenModule
  : dummyImpl
