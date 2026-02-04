package app.revanced.patches.line.misc.gms

import app.revanced.patcher.fingerprint


internal val splashActivityOnCreateFingerprint = fingerprint {
    returns("V")
    parameters("Landroid/os/Bundle;")
    custom { method, classDef ->
        method.name == "onCreate" &&
                classDef.type == "Ljp/naver/line/android/activity/main/MainActivity;"
    }
}
