import { Platform, requireNativeModule } from 'expo-modules-core'


export interface ExpoFullScreenModule {
  SDK_INT: number
  enterFullScreen(): Promise<void>
  enterFullScreenSync(): void
  exitFullScreen(): Promise<void>
  exitFullScreenSync(): void
  getSystemUiVisibility(): number
  setSystemUiVisibility(visibility: number): Promise<void>
  setSystemUiVisibilitySync(visibility: number): void
  getSystemBarsAppearance(): number | null
  setSystemBarsAppearance(appearance: number, mask: number): Promise<void>
  setSystemBarsAppearanceSync(appearance: number, mask: number): void
}

const dummyImpl: ExpoFullScreenModule = {
  SDK_INT: 0,
  async enterFullScreen() { },
  enterFullScreenSync() { },
  async exitFullScreen() { },
  exitFullScreenSync() { },
  getSystemUiVisibility() { return 0 },
  async setSystemUiVisibility() { },
  setSystemUiVisibilitySync() { },
  getSystemBarsAppearance() { return null },
  async setSystemBarsAppearance() { },
  setSystemBarsAppearanceSync() { }
}

// It loads the native module object from the JSI or falls back to
// the bridge module (from NativeModulesProxy) if the remote debugger is on.
export default Platform.OS === 'android'
  ? requireNativeModule('ExpoFullScreen') as ExpoFullScreenModule
  : dummyImpl
