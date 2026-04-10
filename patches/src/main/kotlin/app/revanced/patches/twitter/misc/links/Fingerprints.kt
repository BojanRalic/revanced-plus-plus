package app.revanced.patches.twitter.misc.links

import app.revanced.patcher.*
import app.revanced.patcher.patch.BytecodePatchContext

internal val BytecodePatchContext.sanitizeSharingLinksMethod by gettingFirstMethod(
    "<this>",
    "shareParam",
    "sessionToken",
) { returnType == "Ljava/lang/String;" }

// Returns a shareable link string based on a tweet ID and a username.
internal val BytecodePatchContext.linkBuilderMethod by gettingFirstMethod($$"/%1$s/status/%2$d")

internal val BytecodePatchContext.linkSharingDomainHelperMethod by gettingFirstMethodDeclaratively {
    name("getShareDomain")
    definingClass(EXTENSION_CLASS_DESCRIPTOR)
}
