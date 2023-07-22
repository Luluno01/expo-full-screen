// Import the native module. On web, it will be resolved to ExpoFullScreen.web.ts
// and on native platforms to ExpoFullScreen.ts
import ExpoFullScreenModule from './ExpoFullScreenModule'


export const SDK_INT = ExpoFullScreenModule.SDK_INT

export async function enterFullScreen() {
  return ExpoFullScreenModule.enterFullScreen()
}

export async function exitFullScreen() {
  return ExpoFullScreenModule.exitFullScreen()
}

export function enterFullScreenSync() {
  ExpoFullScreenModule.enterFullScreenSync()
}

export function exitFullScreenSync() {
  ExpoFullScreenModule.exitFullScreenSync()
}

export function getSystemUiVisibility() {
  return ExpoFullScreenModule.getSystemUiVisibility()
}

export async function setSystemUiVisibility(visibility: number) {
  return ExpoFullScreenModule.setSystemUiVisibility(visibility)
}

export function setSystemUiVisibilitySync(visibility: number) {
  ExpoFullScreenModule.setSystemUiVisibilitySync(visibility)
}

export function getSystemBarsAppearance() {
  return ExpoFullScreenModule.getSystemBarsAppearance()
}

export async function setSystemBarsAppearance(appearance: number, mask: number = appearance) {
  return ExpoFullScreenModule.setSystemBarsAppearance(appearance, mask)
}

export function setSystemBarsAppearanceSync(appearance: number, mask: number = appearance) {
  ExpoFullScreenModule.setSystemBarsAppearanceSync(appearance, mask)
}

export const getSystemUiFlag = SDK_INT >= 30
  ? getSystemBarsAppearance
  : getSystemUiVisibility

export const setSystemUiFlag = SDK_INT >= 30
  ? setSystemBarsAppearance
  : setSystemUiVisibility

export const setSystemUiFlagSync = SDK_INT >= 30
    ? setSystemBarsAppearanceSync
    : setSystemUiVisibilitySync
