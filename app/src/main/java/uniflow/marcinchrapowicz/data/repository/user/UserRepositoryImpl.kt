package uniflow.marcinchrapowicz.data.repository.user

import io.uniflow.result.SafeResult
import io.uniflow.result.SafeResult.Companion.success
import io.uniflow.result.SafeResult.Companion.failure
import uniflow.marcinchrapowicz.domain.entity.user.User
import java.lang.Exception

class UserRepositoryImpl : UserRepository {

    override suspend fun getUser(userId: String): SafeResult<User> {
        return success(User(userId, "First", "Last", "User123@gmail.com", "12345678"))
    }

    override suspend fun getUserException(userId: String): SafeResult<User> {
        return failure(Exception("Boom !!!"))
    }
}
