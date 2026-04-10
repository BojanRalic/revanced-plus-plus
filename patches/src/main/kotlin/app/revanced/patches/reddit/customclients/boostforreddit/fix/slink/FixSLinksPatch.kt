package app.revanced.patches.reddit.customclients.boostforreddit.fix.slink

import app.revanced.patcher.extensions.addInstruction
import app.revanced.patches.reddit.customclients.SET_ACCESS_TOKEN_METHOD
import app.revanced.patches.reddit.customclients.boostforreddit.misc.extension.sharedExtensionPatch
import app.revanced.patches.reddit.customclients.fixSLinksPatch
import app.revanced.patches.reddit.customclients.hookSLinkResolution

const val EXTENSION_CLASS_DESCRIPTOR = "Lapp/revanced/extension/boostforreddit/FixSLinksPatch;"

@Suppress("unused")
val fixSlinksPatch = fixSLinksPatch(
    extensionPatch = sharedExtensionPatch,
) {
    compatibleWith("com.rubenmayayo.reddit")

    apply {
        handleNavigationMethod.hookSLinkResolution("p1", "v1", EXTENSION_CLASS_DESCRIPTOR)

        getOAuthAccessTokenMethod.addInstruction(
            3,
            "invoke-static { v0 }, $EXTENSION_CLASS_DESCRIPTOR->$SET_ACCESS_TOKEN_METHOD",
        )
    }
}
