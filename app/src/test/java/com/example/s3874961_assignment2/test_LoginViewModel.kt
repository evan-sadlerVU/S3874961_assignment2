// src/test/java/.../LoginViewModelSuccessTest.kt
import com.example.s3874961_assignment2.data.LoginRepository
import com.example.s3874961_assignment2.data.LoginRequest
import com.example.s3874961_assignment2.data.LoginResponse
import com.example.s3874961_assignment2.ui.login.LoginViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelSuccessTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `onLoginClicked emits navigateToDashboard, then clears`() = runTest(testDispatcher) {
        val repo = mockk<LoginRepository>()
        val expected = LoginResponse(keypass = "abc123")
        coEvery { repo.login(LoginRequest("user", "pass")) } returns expected

        val vm = LoginViewModel(repo)

        vm.onLoginClicked("user", "pass")
        advanceUntilIdle() // let viewModelScope.launch finish

        assertEquals("abc123", vm.navigateToDashboard.value?.keypass)
        coVerify(exactly = 1) { repo.login(LoginRequest("user", "pass")) }

        vm.onNavigationComplete()
        assertNull(vm.navigateToDashboard.value)
    }
    @Test
    fun `onLoginClicked failure emits loginError and no navigation`() = runTest(testDispatcher) {
        val repo = mockk<LoginRepository>()
        coEvery { repo.login(any()) } throws RuntimeException("Boom")

        val vm = LoginViewModel(repo)

        vm.onLoginClicked("u", "p")
        advanceUntilIdle()

        assertTrue(vm.loginError.value?.contains("Login failed", ignoreCase = true) == true)
        assertNull(vm.navigateToDashboard.value)

        vm.onErrorShown()
        assertNull(vm.loginError.value)
    }
}
