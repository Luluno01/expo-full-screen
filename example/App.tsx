import { StyleSheet, View, Button } from 'react-native'
import { StatusBar } from 'expo-status-bar'

import * as ExpoFullScreen from '@untitled/expo-full-screen'


export default function App() {
  return (
    <View style={styles.container}>
      <StatusBar style="dark" />
      <View style={styles.item}>
        <Button onPress={ExpoFullScreen.enterFullScreen} title="Enter Full Screen" />
      </View>
      <View style={styles.item}>
        <Button onPress={ExpoFullScreen.exitFullScreen} title="Exit Full Screen" />
      </View>
      <View style={styles.item}>
        <Button onPress={ExpoFullScreen.enterFullScreenSync} title="Enter Full Screen (Sync)" />
      </View>
      <View style={styles.item}>
        <Button onPress={ExpoFullScreen.exitFullScreenSync} title="Exit Full Screen (Sync)" />
      </View>
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
  item: {
    marginBottom: 8
  }
})
