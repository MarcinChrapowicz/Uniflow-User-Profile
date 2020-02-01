package uniflow.marcinchrapowicz.userprofile.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.uniflow.androidx.flow.onEvents
import io.uniflow.androidx.flow.onStates
import uniflow.marcinchrapowicz.userprofile.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import uniflow.marcinchrapowicz.email.EmailActivity
import uniflow.marcinchrapowicz.mobilenumber.MobileNumberActivity
import uniflow.marcinchrapowicz.userprofile.openErrorFragment
import uniflow.marcinchrapowicz.utils.navigation.openFragment

class UserProfileActivity : AppCompatActivity() {

    private val viewModel: UserProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        onStates(viewModel) { state ->
            when (state) {
                is UserProfileState -> openFragment<UserProfileFragment>(R.id.user_profile_container)
            }
        }

        onEvents(viewModel){
            when(val event = it.take()) {
                is UserProfileEvent.Loading -> openFragment<LoadingFragment>(R.id.user_profile_container)
                is UserProfileEvent.OpenEmailActivity -> startActivity(Intent(this, EmailActivity::class.java))
                is UserProfileEvent.OpenMobileNumberActivity -> startActivity(Intent(this, MobileNumberActivity::class.java))
                is UserProfileEvent.RetryFragment -> {
                    openErrorFragment(R.id.user_profile_container) {
                        viewModel.getUser(event.userId)
                    }
                }
            }
        }

        viewModel.getUserWithException("123")
    }

    override fun onBackPressed() {
        finish()
    }
}
