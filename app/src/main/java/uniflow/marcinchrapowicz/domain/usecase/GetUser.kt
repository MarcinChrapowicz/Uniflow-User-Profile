package uniflow.marcinchrapowicz.domain.usecase

import uniflow.marcinchrapowicz.data.repository.user.UserRepository
import io.uniflow.result.SafeResult
import uniflow.marcinchrapowicz.domain.entity.user.User

class GetUser(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: String): SafeResult<User> {
        return userRepository.getUser(userId)
    }
}