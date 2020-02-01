package uniflow.marcinchrapowicz.userprofile.ui

import uniflow.marcinchrapowicz.domain.entity.user.User

fun User.mapToUserState(): UserProfileState = UserProfileState("$firstName $lastName", email, mobile)
