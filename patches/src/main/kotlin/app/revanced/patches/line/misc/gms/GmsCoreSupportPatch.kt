package app.revanced.patches.line.misc.gms

import app.revanced.patcher.patch.Option
import app.revanced.patches.all.misc.resources.addResources
import app.revanced.patches.all.misc.resources.addResourcesPatch
import app.revanced.patches.line.misc.extension.sharedExtensionPatch
import app.revanced.patches.line.misc.gms.Constants.LINE_PACKAGE_NAME
import app.revanced.patches.line.misc.gms.Constants.REVANCED_LINE_PACKAGE_NAME


import app.revanced.patches.shared.castContextFetchFingerprint
import app.revanced.patches.shared.misc.gms.gmsCoreSupportPatch

import app.revanced.patches.shared.primeMethodFingerprint

@Suppress("unused")
val gmsCoreSupportPatch = gmsCoreSupportPatch(
    fromPackageName = LINE_PACKAGE_NAME,
    toPackageName = REVANCED_LINE_PACKAGE_NAME,
    primeMethodFingerprint = primeMethodFingerprint,
    earlyReturnFingerprints = setOf(
        castContextFetchFingerprint,
    ),
    mainActivityOnCreateFingerprint = splashActivityOnCreateFingerprint,
    extensionPatch = sharedExtensionPatch,
    gmsCoreSupportResourcePatchFactory = ::gmsCoreSupportResourcePatch,
) {

    compatibleWith(
        LINE_PACKAGE_NAME(
            "15.12.2",
        )
    )
}

private fun gmsCoreSupportResourcePatch(
    gmsCoreVendorGroupIdOption: Option<String>,
) = app.revanced.patches.shared.misc.gms.gmsCoreSupportResourcePatch(
    fromPackageName = LINE_PACKAGE_NAME,
    toPackageName = REVANCED_LINE_PACKAGE_NAME,
    gmsCoreVendorGroupIdOption = gmsCoreVendorGroupIdOption,
    spoofedPackageSignature = "61ed377e85d386a8dfee6b864bd85b0bfaa5af81",
    executeBlock = {
        addResources("shared", "misc.gms.gmsCoreSupportResourcePatch")

        val gmsCoreVendorGroupId by gmsCoreVendorGroupIdOption
    }
) {
    dependsOn(
        addResourcesPatch,
        )
}
