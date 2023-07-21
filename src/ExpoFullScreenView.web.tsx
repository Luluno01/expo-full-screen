import * as React from 'react';

import { ExpoFullScreenViewProps } from './ExpoFullScreen.types';

export default function ExpoFullScreenView(props: ExpoFullScreenViewProps) {
  return (
    <div>
      <span>{props.name}</span>
    </div>
  );
}
