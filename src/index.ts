import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to ExpoFullScreen.web.ts
// and on native platforms to ExpoFullScreen.ts
import ExpoFullScreenModule from './ExpoFullScreenModule';
import ExpoFullScreenView from './ExpoFullScreenView';
import { ChangeEventPayload, ExpoFullScreenViewProps } from './ExpoFullScreen.types';

// Get the native constant value.
export const PI = ExpoFullScreenModule.PI;

export function hello(): string {
  return ExpoFullScreenModule.hello();
}

export async function setValueAsync(value: string) {
  return await ExpoFullScreenModule.setValueAsync(value);
}

const emitter = new EventEmitter(ExpoFullScreenModule ?? NativeModulesProxy.ExpoFullScreen);

export function addChangeListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onChange', listener);
}

export { ExpoFullScreenView, ExpoFullScreenViewProps, ChangeEventPayload };
