package app.revanced.patches.reddit.customclients.sync.syncforreddit.fix.redgifs

import app.revanced.patcher.extensions.addInstructions
import app.revanced.patches.reddit.customclients.fixRedgifsApiPatch
import app.revanced.patches.reddit.customclients.replaceOkHttpBuildWithInstall
import app.revanced.patches.reddit.customclients.sync.syncforreddit.extension.sharedExtensionPatch

internal const val EXTENSION_CLASS_DESCRIPTOR = "Lapp/revanced/extension/syncforreddit/FixRedgifsApiPatch;"

@Suppress("unused")
val fixRedgifsApi = fixRedgifsApiPatch(
    extensionPatch = sharedExtensionPatch
) {
    compatibleWith(
        "com.laurencedawson.reddit_sync",
        "com.laurencedawson.reddit_sync.pro",
        "com.laurencedawson.reddit_sync.dev",
    )

    apply {
        createOkHttpClientMethod.replaceOkHttpBuildWithInstall(EXTENSION_CLASS_DESCRIPTOR)

        getDefaultUserAgentMethod.addInstructions(
            0,
            """
            invoke-static { }, $getOriginalUserAgentMethod
            move-result-object v0
            return-object v0
            """
        )
    }
}
