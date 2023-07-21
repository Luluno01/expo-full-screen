// Import the native module. On web, it will be resolved to ExpoFullScreen.web.ts
// and on native platforms to ExpoFullScreen.ts
import ExpoFullScreenModule from './ExpoFullScreenModule'


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
