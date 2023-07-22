# @untitled/expo-full-screen

Enter and exit full screen & bindings for system UI/bars manipulation.

<!-- # API documentation

- [Documentation for the main branch](https://github.com/expo/expo/blob/main/docs/pages/versions/unversioned/sdk/@untitled/full-screen.md)
- [Documentation for the latest stable release](https://docs.expo.dev/versions/latest/sdk/@untitled/full-screen/) -->

- [@untitled/expo-full-screen](#untitledexpo-full-screen)
  - [API reference](#api-reference)
    - [Constants](#constants)
      - [`SDK_INT`](#sdk_int)
    - [Methods](#methods)
      - [`enterFullScreen`](#enterfullscreen)
      - [`enterFullScreenSync`](#enterfullscreensync)
      - [`exitFullScreen`](#exitfullscreen)
      - [`exitFullScreenSync`](#exitfullscreensync)
      - [`getSystemUiVisibility`](#getsystemuivisibility)
      - [`setSystemUiVisibility`](#setsystemuivisibility)
      - [`setSystemUiVisibilitySync`](#setsystemuivisibilitysync)
      - [`getSystemBarsAppearance`](#getsystembarsappearance)
      - [`setSystemBarsAppearance`](#setsystembarsappearance)
      - [`setSystemBarsAppearanceSync`](#setsystembarsappearancesync)
      - [`getSystemUiFlag`](#getsystemuiflag)
      - [`setSystemUiFlag`](#setsystemuiflag)
      - [`setSystemUiFlagSync`](#setsystemuiflagsync)
  - [Installation](#installation)
    - [Managed Expo projects](#managed-expo-projects)
    - [Bare React Native projects](#bare-react-native-projects)
      - [Add the package to your npm dependencies](#add-the-package-to-your-npm-dependencies)
      - [Configure for Android](#configure-for-android)
  - [Usage](#usage)
  - [Contributing](#contributing)

## API reference

**NOTE**: mixed usage of `setSystemUiVisibility` and `setSystemBarsAppearance` may lead to undefined system UI/bars states.

```TypeScript
import {
  SDK_INT,
  enterFullScreen,
  exitFullScreen,
  enterFullScreenSync,
  exitFullScreenSync,
  getSystemUiVisibility,
  setSystemUiVisibility,
  setSystemUiVisibilitySync,
  getSystemBarsAppearance,
  setSystemBarsAppearance,
  setSystemBarsAppearanceSync,
  getSystemUiFlag,
  setSystemUiFlag,
  setSystemUiFlagSync
} from '@untitled/expo-full-screen'
```

### Constants

#### `SDK_INT`

The Android SDK version of the software system currently running on this hardware device. Alias of [`android.os.Build.VERSION#SDK_INT`](https://developer.android.com/reference/android/os/Build.VERSION#SDK_INT). When it is larger than or equal to 30, [`getSystemBarsAppearance`](#getsystembarsappearance), [`setSystemBarsAppearance`](#setsystembarsappearance) and [`setSystemBarsAppearanceSync`](#systembarsappearancesync) are available.

### Methods

#### `enterFullScreen`

Enter full screen mode by doing the following:

1. Set decor view to render edge-to-edge.
2. Hide all system bars, including status bars, caption bar as well as navigation bars, but not IME.
3. Allow showing transient system bars when users swipe the edge of the screen ([`BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE`](https://developer.android.com/reference/androidx/core/view/WindowInsetsControllerCompat#BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE())).

#### `enterFullScreenSync`

Synchronous version of [`enterFullScreen`](#enterfullscreen).

#### `exitFullScreen`

Exit full screen mode by reversing what [`enterFullScreen`](#enterfullscreen) does:

1. Set decor view to not render under status bar and navigation bars.
2. Show all system bars, including status bars, caption bar as well as navigation bars, but not IME.
3. Reset system bars behavior ([`BEHAVIOR_SHOW_BARS_BY_TOUCH`](https://developer.android.com/reference/androidx/core/view/WindowInsetsControllerCompat#BEHAVIOR_SHOW_BARS_BY_TOUCH())).

#### `exitFullScreenSync`

Synchronous version of [`exitFullScreen`](#exitfullscreen).

#### `getSystemUiVisibility`

Get system UI visibility. Alias of [`getSystemUiVisibility`](https://developer.android.com/reference/android/view/View#getSystemUiVisibility()).

#### `setSystemUiVisibility`

Set system UI visibility. Alias of [`setSystemUiVisibility`](https://developer.android.com/reference/android/view/View#setSystemUiVisibility()).

#### `setSystemUiVisibilitySync`

Synchronous version of [`setSystemUiVisibility`](#setsystemuivisibility).

#### `getSystemBarsAppearance`

Retrieve the requested appearance of system bars. Alias of [`getSystemBarsAppearance`](https://developer.android.com/reference/android/view/WindowInsetsController#getSystemBarsAppearance()).

#### `setSystemBarsAppearance`

Control the appearance of system bars. Alias of [`setSystemBarsAppearance`](https://developer.android.com/reference/android/view/WindowInsetsController#setSystemBarsAppearance(int,%20int)).

#### `setSystemBarsAppearanceSync`

Synchronous version of [`setSystemBarsAppearance`](#setsystembarsappearance).

#### `getSystemUiFlag`

Alias of [`getSystemUiVisibility`](#getsystemuivisibility) when [`SDK_INT`](#sdk_int) is lower than 30, or [`getSystemBarsAppearance`](#getsystembarsappearance) otherwise. Useful when you will set the same flag value back later, so effectively you don't care what the SDK version really is.

#### `setSystemUiFlag`

Alias of [`setSystemUiVisibility`](#setsystemuivisibility) when [`SDK_INT`](#sdk_int) is lower than 30, or [`setSystemBarsAppearance`](#setsystembarsappearance) otherwise. Useful when you will set back the same flag value that you retrieved via [`getSystemUiFlag`](#getsystemuiflag), so effectively you don't care what the SDK version really is.

#### `setSystemUiFlagSync`

Synchronous version of [`setSystemUiFlag`](#setsystemuiflag).

## Installation

### Managed Expo projects

For [managed](https://docs.expo.dev/archive/managed-vs-bare/) Expo projects, simply install the package:

```
npm install git+https://github.com/Luluno01/expo-full-screen.git
```

### Bare React Native projects

For bare React Native projects, you must ensure that you have [installed and configured the `expo` package](https://docs.expo.dev/bare/installing-expo-modules/) before continuing.

#### Add the package to your npm dependencies

```
npm install git+https://github.com/Luluno01/expo-full-screen.git
```

#### Configure for Android

Optionally add the following content to your `styles.xml`:

```
<item name="android:windowLayoutInDisplayCutoutMode" tools:targetApi="o_mr1">shortEdges</item>
```

So that it looks like

```
<resources xmlns:tools="http://schemas.android.com/tools">
  <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
    <item name="android:textColor">@android:color/black</item>
    <item name="android:editTextStyle">@style/ResetEditText</item>
    <item name="android:editTextBackground">@drawable/rn_edit_text_material</item>
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="android:windowLayoutInDisplayCutoutMode" tools:targetApi="o_mr1">shortEdges</item>
  </style>
  <style name="ResetEditText" parent="@android:style/Widget.EditText">
    <item name="android:padding">0dp</item>
    <item name="android:textColorHint">#c8c8c8</item>
    <item name="android:textColor">@android:color/black</item>
  </style>
  <style name="Theme.App.SplashScreen" parent="AppTheme">
    <item name="android:windowBackground">@drawable/splashscreen</item>
  </style>
</resources>
```

## Usage

See [example](./example/App.tsx) for a working example.

## Contributing

<!-- Contributions are very welcome! Please refer to guidelines described in the [contributing guide]( https://github.com/expo/expo#contributing). -->

Contributions are very welcome! Simply open an issue or a pull request.
