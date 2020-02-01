package uniflow.marcinchrapowicz.data.repository.user

import io.uniflow.result.SafeResult
import uniflow.marcinchrapowicz.domain.entity.user.User

interface UserRepository {
    suspend fun getUser(userId: String): SafeResult<User>
    suspend fun getUserException(userId: String): SafeResult<User>
}