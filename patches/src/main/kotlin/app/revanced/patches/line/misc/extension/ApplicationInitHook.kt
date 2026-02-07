package app.revanced.patches.line.misc.extension

import app.revanced.patches.shared.misc.extension.extensionHook

/**
 * jp.naver.line.android.LineApplication.onCreate()
 * → Context 用のみ
 */
internal val applicationInitHook = extensionHook {
    returns("V")
    parameters()
    custom { method, classDef ->
        method.name == "onCreate" &&
                classDef.type == "Ljp/naver/line/android/LineApplication;"
    }
}



internal val splashActivityOnCreateHook = extensionHook {
    returns("V")
    parameters("Landroid/os/Bundle;")
    custom { method, classDef ->
        method.name == "onCreate" &&
                classDef.type == "Ljp/naver/line/android/activity/main/MainActivity;"
    }
}
