package app.revanced.patches.reddit.customclients.baconreader.fix.redgifs

import app.revanced.patcher.extensions.removeInstructions
import app.revanced.patcher.extensions.typeReference
import app.revanced.patches.reddit.customclients.baconreader.misc.extension.sharedExtensionPatch
import app.revanced.patches.reddit.customclients.fixRedgifsApiPatch
import app.revanced.patches.reddit.customclients.replaceOkHttpBuildWithInstall
import app.revanced.util.indexOfFirstInstructionOrThrow
import com.android.tools.smali.dexlib2.Opcode

internal const val EXTENSION_CLASS_DESCRIPTOR = "Lapp/revanced/extension/baconreader/FixRedgifsApiPatch;"

@Suppress("unused")
val fixRedgifsApi = fixRedgifsApiPatch(
    extensionPatch = sharedExtensionPatch
) {
    compatibleWith(
        "com.onelouder.baconreader",
        "com.onelouder.baconreader.premium",
    )

    apply {
        // Remove conflicting OkHttp interceptors before replacing the build() call.
        val originalInterceptorInstallIndex = getOkHttpClientMethod.indexOfFirstInstructionOrThrow {
            opcode == Opcode.NEW_INSTANCE && typeReference?.type == $$"Lcom/onelouder/baconreader/media/gfycat/RedGifsManager$HeaderInterceptor;"
        }
        getOkHttpClientMethod.removeInstructions(originalInterceptorInstallIndex, 5)

        getOkHttpClientMethod.replaceOkHttpBuildWithInstall(EXTENSION_CLASS_DESCRIPTOR)
    }
}
