package app.revanced.patches.all.misc.connectivity.location.hide

import app.revanced.patcher.extensions.replaceInstruction
import app.revanced.patcher.patch.bytecodePatch
import app.revanced.patches.all.misc.transformation.IMethodCall
import app.revanced.patches.all.misc.transformation.fromMethodReference
import app.revanced.util.forEachInstructionAsSequence
import app.revanced.util.getReference
import com.android.tools.smali.dexlib2.iface.instruction.FiveRegisterInstruction
import com.android.tools.smali.dexlib2.iface.reference.MethodReference

@Suppress("unused")
val hideMockLocationPatch = bytecodePatch(
    name = "Hide mock location",
    description = "Prevents the app from knowing the device location is being mocked by a third party app.",
    use = false,
) {
    apply {
        forEachInstructionAsSequence(
            match = match@{ _, _, instruction, instructionIndex ->
                val reference = instruction.getReference<MethodReference>() ?: return@match null
                if (fromMethodReference<MethodCall>(reference) == null) return@match null

                instruction to instructionIndex
            },
            transform = { method, entry ->
                val (instruction, index) = entry
                instruction as FiveRegisterInstruction

                // Replace return value with a constant `false` boolean.
                method.replaceInstruction(
                    index + 1,
                    "const/4 v${instruction.registerC}, 0x0",
                )
            },
        )
    }
}

private enum class MethodCall(
    override val definedClassName: String,
    override val methodName: String,
    override val methodParams: Array<String>,
    override val returnType: String,
) : IMethodCall {
    IsMock("Landroid/location/Location;", "isMock", emptyArray(), "Z"),
    IsFromMockProvider("Landroid/location/Location;", "isFromMockProvider", emptyArray(), "Z"),
}
