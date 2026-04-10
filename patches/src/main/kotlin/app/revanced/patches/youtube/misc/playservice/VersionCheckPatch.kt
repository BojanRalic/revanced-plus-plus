@file:Suppress("ktlint:standard:property-naming")

package app.revanced.patches.youtube.misc.playservice

import app.revanced.patcher.patch.resourcePatch
import app.revanced.util.findPlayStoreServicesVersion
import kotlin.properties.Delegates

// Use notNull delegate so an exception is thrown if these fields are accessed before they are set.
var is1946OrGreater: Boolean by Delegates.notNull()
    private set
var is1947OrGreater: Boolean by Delegates.notNull()
    private set
var is1949OrGreater: Boolean by Delegates.notNull()
    private set
var is2002OrGreater: Boolean by Delegates.notNull()
    private set
var is2003OrGreater: Boolean by Delegates.notNull()
    private set
var is2005OrGreater: Boolean by Delegates.notNull()
    private set
var is2007OrGreater: Boolean by Delegates.notNull()
    private set
var is2009OrGreater: Boolean by Delegates.notNull()
    private set
var is2010OrGreater: Boolean by Delegates.notNull()
    private set
var is2014OrGreater: Boolean by Delegates.notNull()
    private set
var is2015OrGreater: Boolean by Delegates.notNull()
    private set
var is2019OrGreater: Boolean by Delegates.notNull()
    private set
var is2020OrGreater: Boolean by Delegates.notNull()
    private set
var is2021OrGreater: Boolean by Delegates.notNull()
    private set
var is2022OrGreater: Boolean by Delegates.notNull()
    private set
var is2026OrGreater: Boolean by Delegates.notNull()
    private set
var is2028OrGreater: Boolean by Delegates.notNull()
    private set
var is2029OrGreater: Boolean by Delegates.notNull()
    private set
var is2030OrGreater: Boolean by Delegates.notNull()
    private set
var is2031OrGreater: Boolean by Delegates.notNull()
    private set
var is2034OrGreater: Boolean by Delegates.notNull()
    private set
var is2037OrGreater: Boolean by Delegates.notNull()
    private set
var is2039OrGreater: Boolean by Delegates.notNull()
    private set
var is2040OrGreater: Boolean by Delegates.notNull()
    private set
var is2041OrGreater: Boolean by Delegates.notNull()
    private set
var is2045OrGreater: Boolean by Delegates.notNull()
    private set
var is2046OrGreater: Boolean by Delegates.notNull()
    private set
var is2049OrGreater: Boolean by Delegates.notNull()
    private set
var is2102OrGreater: Boolean by Delegates.notNull()
    private set
var is2103OrGreater: Boolean by Delegates.notNull()
    private set
var is2105OrGreater : Boolean by Delegates.notNull()
    private set
var is2106OrGreater : Boolean by Delegates.notNull()
    private set
var is2107OrGreater : Boolean by Delegates.notNull()
    private set
var is2108OrGreater : Boolean by Delegates.notNull()
    private set

val versionCheckPatch = resourcePatch(
    description = "Uses the Play Store service version to find the major/minor version of the YouTube target app.",
) {
    apply {
        // The app version is missing from the decompiled manifest,
        // so instead use the Google Play services version and compare against specific releases.
        val playStoreServicesVersion = findPlayStoreServicesVersion()

        // All bug fix releases always seem to use the same play store version as the minor version.
        is1946OrGreater = 244705000 <= playStoreServicesVersion
        is1947OrGreater = 244799000 <= playStoreServicesVersion
        is1949OrGreater = 245005000 <= playStoreServicesVersion
        is2002OrGreater = 250299000 <= playStoreServicesVersion
        is2003OrGreater = 250405000 <= playStoreServicesVersion
        is2005OrGreater = 250605000 <= playStoreServicesVersion
        is2007OrGreater = 250805000 <= playStoreServicesVersion
        is2009OrGreater = 251006000 <= playStoreServicesVersion
        is2010OrGreater = 251105000 <= playStoreServicesVersion
        is2014OrGreater = 251505000 <= playStoreServicesVersion
        is2015OrGreater = 251605000 <= playStoreServicesVersion
        is2019OrGreater = 252005000 <= playStoreServicesVersion
        is2020OrGreater = 252105000 <= playStoreServicesVersion
        is2021OrGreater = 252205000 <= playStoreServicesVersion
        is2022OrGreater = 252305000 <= playStoreServicesVersion
        is2026OrGreater = 252705000 <= playStoreServicesVersion
        is2028OrGreater = 252905000 <= playStoreServicesVersion
        is2029OrGreater = 253005000 <= playStoreServicesVersion
        is2030OrGreater = 253105000 <= playStoreServicesVersion
        is2031OrGreater = 253205000 <= playStoreServicesVersion
        is2034OrGreater = 253505000 <= playStoreServicesVersion
        is2037OrGreater = 253805000 <= playStoreServicesVersion
        is2039OrGreater = 253980000 <= playStoreServicesVersion
        is2040OrGreater = 254105000 <= playStoreServicesVersion
        is2041OrGreater = 254205000 <= playStoreServicesVersion
        is2045OrGreater = 254605000 <= playStoreServicesVersion
        is2046OrGreater = 254705000 <= playStoreServicesVersion
        is2049OrGreater = 255005000 <= playStoreServicesVersion
        is2102OrGreater = 260305000 <= playStoreServicesVersion
        is2103OrGreater = 260405000 <= playStoreServicesVersion
        is2105OrGreater = 260605000 <= playStoreServicesVersion
        is2106OrGreater = 260705000 <= playStoreServicesVersion
        is2107OrGreater = 260805000 <= playStoreServicesVersion
        is2108OrGreater = 260905000 <= playStoreServicesVersion
    }
}
