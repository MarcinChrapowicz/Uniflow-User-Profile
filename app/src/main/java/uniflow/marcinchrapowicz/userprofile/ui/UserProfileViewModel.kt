package uniflow.marcinchrapowicz.userprofile.ui

import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.actionOn
import io.uniflow.core.flow.data.UIEvent
import uniflow.marcinchrapowicz.domain.usecase.GetUser
import uniflow.marcinchrapowicz.domain.usecase.GetUserException

class UserProfileViewModel(
    private val getUser: GetUser,
    private val getUserException: GetUserException
) : AndroidDataFlow() {

    init {
        action { sendEvent(UserProfileEvent.Loading) }
    }

    fun getUserWithException(userId: String) = action(
        onAction = {
            val state = getUserException(userId).toState { it.mapToUserState() }
            setState(state)
        },
        onError = { exception, state ->
            sendEvent (UserProfileEvent.RetryView(userId))
        }
    )

    fun getUser(userId: String) = action(
        onAction = {
            val state = getUser.invoke(userId).toState { it.mapToUserState() }
            setState(state)
        },
        onError = { exception, state ->
            sendEvent(UserProfileEvent.RetryView(userId))
        }
    )

    fun updateUserInformation(name: String, email: String, mobile: String) = actionOn<UserProfileState>(
        onAction = {
            setState ( UserProfileState(name, email, mobile))
        },
        onError = { exception, state ->
            sendEvent(UIEvent.Fail())
        }
    )

    fun openMobileNumber() = actionOn<UserProfileState> {
        sendEvent(UserProfileEvent.OpenMobileNumber)
    }

    fun openEmail() = actionOn<UserProfileState> {
        sendEvent(UserProfileEvent.OpenEmail)
    }
}