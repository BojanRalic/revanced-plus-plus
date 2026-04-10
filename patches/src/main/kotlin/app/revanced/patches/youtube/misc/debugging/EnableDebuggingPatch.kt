package app.revanced.patches.youtube.misc.debugging

import app.revanced.patches.youtube.misc.extension.sharedExtensionPatch;
import app.revanced.patches.shared.misc.debugging.enableDebuggingPatch
import app.revanced.patches.youtube.misc.playservice.is2040OrGreater
import app.revanced.patches.youtube.misc.playservice.is2041OrGreater
import app.revanced.patches.youtube.misc.playservice.versionCheckPatch
import app.revanced.patches.youtube.misc.settings.PreferenceScreen
import app.revanced.patches.youtube.misc.settings.settingsPatch

@Suppress("unused")
val enableDebuggingPatch = enableDebuggingPatch(
    block = {
        dependsOn(
            sharedExtensionPatch,
            settingsPatch,
            versionCheckPatch
        )

        compatibleWith(
            "com.google.android.youtube"(
                "20.14.43",
                "20.21.37",
                "20.26.46",
                "20.31.42",
                "20.37.48",
                "20.40.45"
            )
        )
    },
    hookStringFeatureFlag = { true },
    // 20.40 has changes not worth supporting.
    hookLongFeatureFlag = { !is2040OrGreater || is2041OrGreater },
    hookDoubleFeatureFlag = { !is2040OrGreater || is2041OrGreater },
    preferenceScreen = PreferenceScreen.MISC,
)
