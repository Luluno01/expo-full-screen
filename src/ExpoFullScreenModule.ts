import { Platform, requireNativeModule } from 'expo-modules-core'


export interface ExpoFullScreenModule {
  enterFullScreen(): Promise<void>
  exitFullScreen(): Promise<void>
  enterFullScreenSync(): void
  exitFullScreenSync(): void
}

const dummyImpl: ExpoFullScreenModule = {
  async enterFullScreen() {},
  async exitFullScreen() {},
  enterFullScreenSync() {},
  exitFullScreenSync() {}
}

// It loads the native module object from the JSI or falls back to
// the bridge module (from NativeModulesProxy) if the remote debugger is on.
export default Platform.OS === 'android'
  ? requireNativeModule('ExpoFullScreen') as ExpoFullScreenModule
  : dummyImpl
