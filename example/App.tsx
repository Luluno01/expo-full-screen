import { StyleSheet, Text, View, Pressable, Button } from 'react-native'

import ExpoFullScreen from '@untitled/expo-full-screen'


export default function App() {
  return (
    <View style={styles.container}>
      <Button onPress={ExpoFullScreen.enterFullScreen} title="Enter Full Screen" />
      <Button onPress={ExpoFullScreen.exitFullScreen} title="Exit Full Screen" />
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
})
