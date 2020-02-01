package uniflow.marcinchrapowicz.userprofile.di

import uniflow.marcinchrapowicz.userprofile.ui.UserProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uniflow.marcinchrapowicz.data.repository.user.UserRepository
import uniflow.marcinchrapowicz.data.repository.user.UserRepositoryImpl
import uniflow.marcinchrapowicz.domain.usecase.GetUser
import uniflow.marcinchrapowicz.domain.usecase.GetUserException

val user_profile_module = module {
    viewModel { UserProfileViewModel(get(), get()) }
}

val usecase_module = module {
    factory { GetUser(get()) }
    factory { GetUserException(get()) }
}

val repository_module = module {
    single<UserRepository> { UserRepositoryImpl() }
}