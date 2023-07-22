import { StyleSheet, Text, View, Button, TextInput } from 'react-native'
import { StatusBar } from 'expo-status-bar'

import * as ExpoFullScreen from '@untitled/expo-full-screen'
import { useCallback, useState } from 'react'


function useOnNumericInputChange(setter: (val: number) => void) {
  return useCallback((text: string) => {
    const val = parseFloat(text)
    if (isNaN(val)) return
    setter(val)
  }, [ setter ])
}

export default function App() {
  const [ systemUiVisibility, setSystemUiVisibility ] = useState(0)
  const onSystemUiVisibilityInputChange = useOnNumericInputChange(setSystemUiVisibility)
  const [ systemBarsAppearance, setSystemBarsAppearance ] = useState(0)
  const onSystemBarsAppearanceInputChange = useOnNumericInputChange(setSystemBarsAppearance)
  const [ systemUiFlag, setSystemUiFlag ] = useState(0)
  const onSystemUiFlagInputChange = useOnNumericInputChange(setSystemUiFlag)

  return (
    <View style={styles.container}>
      <StatusBar style="dark" />
      <Text style={styles.title}>Full Screen</Text>
      <View style={styles.item}>
        <Button onPress={ExpoFullScreen.enterFullScreen} title="Enter" />
        <Button onPress={ExpoFullScreen.exitFullScreen} title="Exit" />
        <Button onPress={ExpoFullScreen.enterFullScreenSync} title="Enter (Sync)" />
        <Button onPress={ExpoFullScreen.exitFullScreenSync} title="Exit (Sync)" />
      </View>
      <View style={styles.title}>
        <Text>systemUiVisibility:</Text>
        <TextInput
          style={{ borderStyle: 'solid', borderColor: 'black', borderWidth: 1 }}
          keyboardType='number-pad'
          value={systemUiVisibility.toString()} onChangeText={onSystemUiVisibilityInputChange} />
      </View>
      <View style={styles.item}>
        <Button
          onPress={() => setSystemUiVisibility(ExpoFullScreen.getSystemUiVisibility())}
          title="Get" />
        <Button
          onPress={() => ExpoFullScreen.setSystemUiVisibility(systemUiVisibility)}
          title="Set" />
        <Button
          onPress={() => ExpoFullScreen.setSystemUiVisibilitySync(systemUiVisibility)}
          title="Set (Sync)" />
      </View>
      <View style={styles.title}>
        <Text>systemBarsAppearance:</Text>
        <TextInput
          style={{ borderStyle: 'solid', borderColor: 'black', borderWidth: 1 }}
          keyboardType='number-pad'
          value={systemBarsAppearance.toString()} onChangeText={onSystemBarsAppearanceInputChange} />
      </View>
      <View style={styles.item}>
        <Button
          onPress={async () => setSystemBarsAppearance(ExpoFullScreen.getSystemBarsAppearance() ?? -1)}
          title="Get" />
        <Button
          onPress={() => ExpoFullScreen.setSystemBarsAppearance(systemBarsAppearance)}
          title="Set" />
        <Button
          onPress={() => ExpoFullScreen.setSystemBarsAppearanceSync(systemBarsAppearance)}
          title="Set (Sync)" />
      </View>
      <View style={styles.title}>
        <Text>systemUiFlag:</Text>
        <TextInput
          style={{ borderStyle: 'solid', borderColor: 'black', borderWidth: 1 }}
          keyboardType='number-pad'
          value={systemUiFlag.toString()} onChangeText={onSystemUiFlagInputChange} />
      </View>
      <View style={styles.item}>
        <Button
          onPress={() => setSystemUiFlag(ExpoFullScreen.getSystemUiFlag() ?? -1)}
          title="Get" />
        <Button
          onPress={() => ExpoFullScreen.setSystemUiFlag(systemUiFlag)}
          title="Set" />
        <Button
          onPress={() => ExpoFullScreen.setSystemUiFlagSync(systemUiFlag)}
          title="Set (Sync)" />
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
  title: {},
  item: {
    flex: 1,
    alignItems: 'center',
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginVertical: 4,
    paddingHorizontal: 16,
    width: '100%'
  }
})
