import { requireNativeViewManager } from 'expo-modules-core';
import * as React from 'react';

import { ExpoFullScreenViewProps } from './ExpoFullScreen.types';

const NativeView: React.ComponentType<ExpoFullScreenViewProps> =
  requireNativeViewManager('ExpoFullScreen');

export default function ExpoFullScreenView(props: ExpoFullScreenViewProps) {
  return <NativeView {...props} />;
}
