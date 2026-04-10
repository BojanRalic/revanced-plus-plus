package app.revanced.patches.reddit.customclients

import app.revanced.com.android.tools.smali.dexlib2.mutable.MutableMethod
import app.revanced.patcher.extensions.ExternalLabel
import app.revanced.patcher.extensions.addInstructionsWithLabels
import app.revanced.patcher.extensions.getInstruction
import app.revanced.patcher.patch.BytecodePatchBuilder
import app.revanced.patcher.patch.Patch
import app.revanced.patcher.patch.bytecodePatch

const val RESOLVE_S_LINK_METHOD = "patchResolveSLink(Ljava/lang/String;)Z"
const val SET_ACCESS_TOKEN_METHOD = "patchSetAccessToken(Ljava/lang/String;)V"

fun fixSLinksPatch(
    extensionPatch: Patch,
    block: BytecodePatchBuilder.() -> Unit = {},
) = bytecodePatch("Fix /s/ links") {
    dependsOn(extensionPatch)

    block()
}

/**
 * Inserts an early-return SLink interception at the beginning of this method.
 *
 * Resolves the URL via the extension; returns early (true) when the URL is an /s/ link
 * that was successfully dispatched, or falls through when it is not.
 *
 * @param urlRegister The register holding the URL string parameter.
 * @param tempRegister A scratch register for the boolean result.
 * @param extensionClassDescriptor The descriptor of the extension class.
 */
fun MutableMethod.hookSLinkResolution(
    urlRegister: String,
    tempRegister: String,
    extensionClassDescriptor: String,
) = addInstructionsWithLabels(
    0,
    """
        invoke-static { $urlRegister }, $extensionClassDescriptor->$RESOLVE_S_LINK_METHOD
        move-result $tempRegister
        if-eqz $tempRegister, :continue
        return $tempRegister
    """,
    ExternalLabel("continue", getInstruction(0)),
)
