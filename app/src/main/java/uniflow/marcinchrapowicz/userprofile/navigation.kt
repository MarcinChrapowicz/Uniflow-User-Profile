package uniflow.marcinchrapowicz.userprofile

import uniflow.marcinchrapowicz.userprofile.ui.ErrorFragment
import uniflow.marcinchrapowicz.userprofile.ui.UserProfileActivity
import uniflow.marcinchrapowicz.utils.navigation.openFragment

fun UserProfileActivity.openErrorFragment(container: Int, actionButton: () -> Unit) {
    val errorFragment = ErrorFragment()
    openFragment<ErrorFragment>(container, addToBackStack = false, fragment = errorFragment)
    errorFragment.actionButton = actionButton
}
