import { StyleSheet, Text, View } from 'react-native';

import * as ExpoFullScreen from '@untitled/expo-full-screen';

export default function App() {
  return (
    <View style={styles.container}>
      <Text>{ExpoFullScreen.hello()}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
