package uniflow.marcinchrapowicz.userprofile.ui

import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.fromState
import uniflow.marcinchrapowicz.domain.usecase.GetUser
import uniflow.marcinchrapowicz.domain.usecase.GetUserException

class UserProfileViewModel(
    private val getUser: GetUser,
    private val getUserException: GetUserException
) : AndroidDataFlow() {

    init {
        setState { sendEvent(UserProfileEvent.Loading) }
    }

    fun getUserWithException(userId: String) = setState(
        {
            getUserException(userId).toState { it.mapToUserState() }
        },
        {
            sendEvent(UserProfileEvent.RetryView(userId))
        }
    )

    fun getUser(userId: String) = setState(
        {
            getUser.invoke(userId).toState {
                it.mapToUserState()
            }
        },
        {
            sendEvent(UserProfileEvent.RetryView(userId))
        }
    )

    fun updateUserInformation(name: String, email: String, mobile: String) = fromState<UserProfileState> {
        UserProfileState(name, email, mobile)
    }

    fun openMobileNumber() = fromState<UserProfileState> {
        sendEvent(UserProfileEvent.OpenMobileNumber)
    }

    fun openEmail() = fromState<UserProfileState> {
        sendEvent(UserProfileEvent.OpenEmail)
    }
}