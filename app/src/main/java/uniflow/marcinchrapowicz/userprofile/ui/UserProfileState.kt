package uniflow.marcinchrapowicz.userprofile.ui

import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState

data class UserProfileState(
    val name: String,
    val email: String,
    val mobile: String
) : UIState()

sealed class UserProfileEvent : UIEvent() {
    data class RetryView(val userId: String) : UserProfileEvent()

    object Loading : UserProfileEvent()
    object OpenEmail : UserProfileEvent()
    object OpenMobileNumber : UserProfileEvent()
}
