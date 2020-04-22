package uniflow.marcinchrapowicz.userprofile.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verifySequence
import io.uniflow.android.test.MockedViewObserver
import io.uniflow.android.test.mockObservers
import io.uniflow.core.flow.data.UIState
import io.uniflow.core.logger.DebugMessageLogger
import io.uniflow.core.logger.UniFlowLogger
import io.uniflow.result.SafeResult
import io.uniflow.result.SafeResult.Companion.success
import io.uniflow.test.rule.TestDispatchersRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uniflow.marcinchrapowicz.domain.entity.user.User
import uniflow.marcinchrapowicz.domain.usecase.GetUser
import uniflow.marcinchrapowicz.domain.usecase.GetUserException


class UserProfileViewModelTest {

    @get:Rule
    var testDispatchersRule = TestDispatchersRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getUser: GetUser = mockk(relaxed = true)
    private val getUserException: GetUserException = mockk(relaxed = true)

    private val user = User("123", "First", "Last", "User123@gmail.com", "12345678")

    private lateinit var view: MockedViewObserver
    private lateinit var viewModel: UserProfileViewModel

    init {
        UniFlowLogger.init(DebugMessageLogger())
    }

    @Before
    fun before() {
        viewModel = UserProfileViewModel(getUser, getUserException)
        view = viewModel.mockObservers()
    }

    @Test
    fun `empty state`() {
        verifySequence {
            view.hasState(UIState.Empty)
        }
    }

    @Test
    fun `get user information`() {
        val userId = "123"

        coEvery { getUser(userId) } returns success(user)

        viewModel.getUser(userId)

        verifySequence {
            view.hasState(UIState.Empty)
            view.hasEvent(UserProfileEvent.Loading)
            view.hasState(user.mapToUserState())
        }
    }

    @Test
    fun `get user information exception`() {
        val userId = "123"

        coEvery { getUserException(userId) } returns SafeResult.failure(IllegalStateException(("message")))

        viewModel.getUser(userId)

        verifySequence {
            view.hasState(UIState.Empty)
            view.hasEvent(UserProfileEvent.Loading)
            view.hasEvent(UserProfileEvent.RetryView("123"))
        }
    }

    @Test
    fun `update user information`() {
        val userId = "123"
        val name = "nameTest"
        val email = "emailTest"
        val mobile = "mobileTest"

        coEvery { getUser(userId) } returns success(user)

        viewModel.getUser(userId)
        viewModel.updateUserInformation(name, email, mobile)

        verifySequence {
            view.hasState(UIState.Empty)
            view.hasEvent(UserProfileEvent.Loading)
            view.hasState(user.mapToUserState())
            view.hasState(user.mapToUserState().copy(name = name, email = email, mobile = mobile))
        }
    }
}