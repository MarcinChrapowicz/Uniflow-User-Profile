package uniflow.marcinchrapowicz.userprofile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.uniflow.androidx.flow.onStates
import kotlinx.android.synthetic.main.fragment_user_profile.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import uniflow.marcinchrapowicz.userprofile.R

class UserProfileFragment : Fragment() {

    private val viewModel: UserProfileViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onStates(viewModel) { state ->
            when (state) {
                is UserProfileState -> updateUserInformation(state)
            }
        }

        setListeners()
    }

    private fun updateUserInformation(state: UserProfileState) {
        user_profile_name.text = state.name
        user_profile_email.text = state.email
        user_profile_mobile.text = state.mobile
    }

    private fun setListeners() {
        user_profile_update_user_information.setOnClickListener {
            viewModel.updateUserInformation(
                user_profile_update_name.text.toString(),
                user_profile_update_email.text.toString(),
                user_profile_update_mobile_number.text.toString()
            )
        }

        user_profile_open_email.setOnClickListener {
            viewModel.openEmailActivity()
        }

        user_profile_open_mobile.setOnClickListener {
            viewModel.openMobileNumberActivity()
        }
    }
}
