package app.revanced.patches.reddit.customclients.boostforreddit.fix.redgifs

import app.revanced.patches.reddit.customclients.fixRedgifsApiPatch
import app.revanced.patches.reddit.customclients.replaceOkHttpBuildWithCreate
import app.revanced.patches.reddit.customclients.boostforreddit.misc.extension.sharedExtensionPatch

private const val EXTENSION_CLASS_DESCRIPTOR = "Lapp/revanced/extension/boostforreddit/FixRedgifsApiPatch;"

@Suppress("unused")
val fixRedgifsApi = fixRedgifsApiPatch(
    extensionPatch = sharedExtensionPatch
) {
    compatibleWith("com.rubenmayayo.reddit")

    apply {
        createOkHttpClientMethod.replaceOkHttpBuildWithCreate(EXTENSION_CLASS_DESCRIPTOR)
    }
}
