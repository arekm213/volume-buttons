package expo.modules.volumebuttons

import kotlin.properties.Delegates

class VolumeButtonsObserver {
        companion object {
            val instance = VolumeButtonsObserver()
        }

        val observersList = mutableListOf<(String) -> Unit>()

        var newestEvent: String by Delegates.observable("") { _, _, newValue ->
            observersList.forEach { it(newValue) }
        }
}