package app.revanced.patches.reddit.customclients

import app.revanced.com.android.tools.smali.dexlib2.mutable.MutableMethod
import app.revanced.patcher.extensions.getInstruction
import app.revanced.patcher.extensions.replaceInstruction
import app.revanced.patcher.patch.BytecodePatchBuilder
import app.revanced.patcher.patch.Patch
import app.revanced.patcher.patch.bytecodePatch
import app.revanced.util.getReference
import app.revanced.util.indexOfFirstInstructionOrThrow
import com.android.tools.smali.dexlib2.iface.instruction.FiveRegisterInstruction
import com.android.tools.smali.dexlib2.iface.reference.MethodReference

const val INSTALL_NEW_CLIENT_METHOD = "install(Lokhttp3/OkHttpClient${'$'}Builder;)Lokhttp3/OkHttpClient;"
const val CREATE_NEW_CLIENT_METHOD = "createClient()Lokhttp3/OkHttpClient;"

fun fixRedgifsApiPatch(
    extensionPatch: Patch,
    block: BytecodePatchBuilder.() -> Unit = {},
) = bytecodePatch("Fix Redgifs API") {
    dependsOn(extensionPatch)

    block()
}

/**
 * Replaces the `OkHttpClient.Builder.build()` call in this method with a call to the extension's
 * [INSTALL_NEW_CLIENT_METHOD], passing the builder instance as the argument.
 *
 * @param extensionClassDescriptor The descriptor of the extension class.
 */
fun MutableMethod.replaceOkHttpBuildWithInstall(extensionClassDescriptor: String) {
    val index = indexOfFirstInstructionOrThrow {
        val reference = getReference<MethodReference>()
        reference?.name == "build" && reference.definingClass == "Lokhttp3/OkHttpClient\$Builder;"
    }
    val register = getInstruction<FiveRegisterInstruction>(index).registerC
    replaceInstruction(
        index,
        "invoke-static { v$register }, $extensionClassDescriptor->$INSTALL_NEW_CLIENT_METHOD",
    )
}

/**
 * Replaces the `OkHttpClient.Builder.build()` call in this method with a no-arg call to the
 * extension's [CREATE_NEW_CLIENT_METHOD].
 *
 * @param extensionClassDescriptor The descriptor of the extension class.
 */
fun MutableMethod.replaceOkHttpBuildWithCreate(extensionClassDescriptor: String) {
    val index = indexOfFirstInstructionOrThrow {
        val reference = getReference<MethodReference>()
        reference?.name == "build" && reference.definingClass == "Lokhttp3/OkHttpClient\$Builder;"
    }
    replaceInstruction(
        index,
        "invoke-static { }, $extensionClassDescriptor->$CREATE_NEW_CLIENT_METHOD",
    )
}
