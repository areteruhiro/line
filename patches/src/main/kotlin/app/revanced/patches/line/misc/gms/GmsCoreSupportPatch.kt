package app.revanced.patches.line.misc.gms

import app.revanced.patcher.patch.Option
import app.revanced.patches.line.misc.extension.sharedExtensionPatch
import app.revanced.patches.shared.castContextFetchFingerprint
import app.revanced.patches.shared.misc.gms.gmsCoreSupportPatch
import app.revanced.patches.shared.primeMethodFingerprint

internal object LineConstants {
    const val MICROG_SIGNATURE = "d04afd875d3811b16019948ed85896f4d11d51d4"
}

@Suppress("unused")
val gmsCoreSupportPatch = gmsCoreSupportPatch(
    fromPackageName = Constants.LINE_PACKAGE_NAME,
    toPackageName = Constants.REVANCED_LINE_PACKAGE_NAME,
    primeMethodFingerprint = primeMethodFingerprint,
    earlyReturnFingerprints = setOf(
        castContextFetchFingerprint
    ),
    mainActivityOnCreateFingerprint = MainActivityFingerprint,
    extensionPatch = sharedExtensionPatch,
    gmsCoreSupportResourcePatchFactory = ::lineGmsCoreSupportResourcePatch,
) {
    compatibleWith(Constants.LINE_PACKAGE_NAME)
}

private fun lineGmsCoreSupportResourcePatch(
    gmsCoreVendorGroupIdOption: Option<String>,
) = app.revanced.patches.shared.misc.gms.gmsCoreSupportResourcePatch(
    fromPackageName = Constants.LINE_PACKAGE_NAME,
    toPackageName = Constants.REVANCED_LINE_PACKAGE_NAME,
    spoofedPackageSignature = LineConstants.MICROG_SIGNATURE,
    gmsCoreVendorGroupIdOption = gmsCoreVendorGroupIdOption,
)
