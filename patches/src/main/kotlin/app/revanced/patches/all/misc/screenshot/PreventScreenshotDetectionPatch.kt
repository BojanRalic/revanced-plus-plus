package app.revanced.patches.all.misc.screenshot

import app.revanced.patcher.extensions.removeInstruction
import app.revanced.patcher.patch.bytecodePatch
import app.revanced.util.forEachInstructionAsSequence
import app.revanced.util.getReference
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.iface.reference.MethodReference
import com.android.tools.smali.dexlib2.immutable.reference.ImmutableMethodReference
import com.android.tools.smali.dexlib2.util.MethodUtil

private val registerScreenCaptureCallbackMethodReference = ImmutableMethodReference(
    "Landroid/app/Activity;",
    "registerScreenCaptureCallback",
    listOf(
        "Ljava/util/concurrent/Executor;",
        "Landroid/app/Activity\$ScreenCaptureCallback;",
    ),
    "V",
)

private val unregisterScreenCaptureCallbackMethodReference = ImmutableMethodReference(
    "Landroid/app/Activity;",
    "unregisterScreenCaptureCallback",
    listOf(
        "Landroid/app/Activity\$ScreenCaptureCallback;",
    ),
    "V",
)

@Suppress("unused")
val preventScreenshotDetectionPatch = bytecodePatch(
    name = "Prevent screenshot detection",
    description = "Removes the registration of all screen capture callbacks. This prevents the app from detecting screenshots.",
    use = false,
) {
    apply {
        forEachInstructionAsSequence(
            match = match@{ _, _, instruction, instructionIndex ->
                if (instruction.opcode != Opcode.INVOKE_VIRTUAL) return@match null

                val reference = instruction.getReference<MethodReference>() ?: return@match null

                instructionIndex.takeIf {
                    MethodUtil.methodSignaturesMatch(reference, registerScreenCaptureCallbackMethodReference) ||
                        MethodUtil.methodSignaturesMatch(reference, unregisterScreenCaptureCallbackMethodReference)
                }
            },
            transform = { mutableMethod, instructionIndex ->
                mutableMethod.removeInstruction(instructionIndex)
            },
        )
    }
}
