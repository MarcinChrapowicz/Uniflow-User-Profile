package uniflow.marcinchrapowicz.userprofile.ui

import io.uniflow.core.flow.UIEvent
import io.uniflow.core.flow.UIState

data class UserProfileState(
    val name: String,
    val email: String,
    val mobile: String
) : UIState()

sealed class UserProfileEvent : UIEvent() {
    data class RetryFragment(val userId: String) : UserProfileEvent()

    object Loading : UserProfileEvent()
    object OpenEmailActivity : UserProfileEvent()
    object OpenMobileNumberActivity : UserProfileEvent()
}
