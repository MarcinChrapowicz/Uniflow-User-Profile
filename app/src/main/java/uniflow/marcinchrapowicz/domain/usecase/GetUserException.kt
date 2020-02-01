package uniflow.marcinchrapowicz.domain.usecase

import io.uniflow.result.SafeResult
import uniflow.marcinchrapowicz.data.repository.user.UserRepository
import uniflow.marcinchrapowicz.domain.entity.user.User

class GetUserException(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: String): SafeResult<User> {
        return userRepository.getUserException(userId)
    }
}