package uniflow.marcinchrapowicz

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import uniflow.marcinchrapowicz.userprofile.di.repository_module
import uniflow.marcinchrapowicz.userprofile.di.usecase_module
import uniflow.marcinchrapowicz.userprofile.di.user_profile_module

class UserProfileApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UserProfileApplication.applicationContext)
            modules(repository_module + usecase_module + user_profile_module)
        }
    }
}