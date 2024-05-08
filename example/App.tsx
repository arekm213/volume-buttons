import { useEffect } from "react";
import { StyleSheet, Text, View } from "react-native";
import * as VolumeButtons from "volume-buttons";

export default function App() {
  useEffect(() => {
    VolumeButtons.addChangeListener(({ volumeButton }) => {
      console.log("testing", volumeButton);
    });
    return () => {
      VolumeButtons?.removeChangeListeners();
    };
  }, []);

  return (
    <View style={styles.container}>
      <Text>Helloo</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});
