package org.jarsi.devicewatch.mineleng.zhcn.presentation.ui

import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView

/**
 * Touch feedback routed through [View.performHapticFeedback] so the system's own
 * haptic settings (on/off and intensity) always apply — no VIBRATE permission,
 * nothing when the user has turned touch feedback off.
 */
internal fun View.performTapHaptic() {
    performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
}

/** Wraps a click handler so the tap also plays the system touch-feedback haptic. */
@Composable
internal fun withTapHaptic(action: () -> Unit): () -> Unit {
    val view = LocalView.current
    return {
        view.performTapHaptic()
        action()
    }
}

/** [withTapHaptic] for handlers that take one argument (e.g. selection callbacks). */
@Composable
internal fun <T> withTapHaptic(action: (T) -> Unit): (T) -> Unit {
    val view = LocalView.current
    return { value ->
        view.performTapHaptic()
        action(value)
    }
}
