package uniflow.marcinchrapowicz.userprofile.ui

import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.actionOn
import uniflow.marcinchrapowicz.domain.usecase.GetUser
import uniflow.marcinchrapowicz.domain.usecase.GetUserException

class UserProfileViewModel(
    private val getUser: GetUser,
    private val getUserException: GetUserException
) : AndroidDataFlow() {

    fun getUserWithException(userId: String) = action(
        onAction = {
            sendEvent { UserProfileEvent.Loading }
            val state = getUserException(userId).toState { it.mapToUserState() }
            setState { state }
        },
        onError = { exception, ui ->
            sendEvent {UserProfileEvent.RetryView(userId) }
        }
    )

    fun getUser(userId: String) = action(
        onAction = {
            sendEvent { UserProfileEvent.Loading }
            val state = getUser.invoke(userId).toState { it.mapToUserState() }
            setState { state }
        },
        onError = { exception, ui ->
            sendEvent(UserProfileEvent.RetryView(userId))
        }
    )

    fun updateUserInformation(name: String, email: String, mobile: String) = actionOn<UserProfileState> {
        setState ( UserProfileState(name, email, mobile))
    }

    fun openMobileNumber() = actionOn<UserProfileState> {
        sendEvent(UserProfileEvent.OpenMobileNumber)
    }

    fun openEmail() = actionOn<UserProfileState> {
        sendEvent(UserProfileEvent.OpenEmail)
    }
}