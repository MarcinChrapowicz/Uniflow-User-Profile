package uniflow.marcinchrapowicz.domain.entity.user

data class User(
        val id: String,
        val firstName: String,
        val lastName: String,
        val email: String,
        val mobile: String
)