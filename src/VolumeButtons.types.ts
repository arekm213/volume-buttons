enum VOLUME_BUTTON {
  UP = "UP",
  DOWN = "DOWN",
}

export type ChangeEventPayload = {
  volumeButton: VOLUME_BUTTON;
};
