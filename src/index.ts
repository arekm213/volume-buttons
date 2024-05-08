import {
  NativeModulesProxy,
  EventEmitter,
  Subscription,
} from "expo-modules-core";

// Import the native module. On web, it will be resolved to VolumeButtons.web.ts
// and on native platforms to VolumeButtons.ts
import { ChangeEventPayload } from "./VolumeButtons.types";
import VolumeButtonsModule from "./VolumeButtonsModule";

const emitter = new EventEmitter(
  VolumeButtonsModule ?? NativeModulesProxy.VolumeButtons,
);

export function addChangeListener(
  listener: (event: ChangeEventPayload) => void,
): Subscription {
  return emitter.addListener<ChangeEventPayload>("onChange", listener);
}

export function removeChangeListeners() {
  emitter.removeAllListeners("onChange");
}

export { ChangeEventPayload };
