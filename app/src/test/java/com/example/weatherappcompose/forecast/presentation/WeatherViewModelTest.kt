package com.example.weatherappcompose.forecast.presentation


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherappcompose.core.common.Resource
import com.example.weatherappcompose.forecast.domain.models.Country
import com.example.weatherappcompose.forecast.domain.usecase.UseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)

    @Mock
    private lateinit var useCase: UseCase

    private lateinit var viewModel: WeatherViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = WeatherViewModel(useCase)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun `test getCountry success`() {
        testCoroutineScope.runTest {
            val country = listOf(Country("TestCountry", "", 0.0, 0.0, ""))
            val successResource = Resource.Success(country)
            val countryStateFlow = MutableStateFlow(ForecastState())

            // Mocking the behavior of UseCase
            `when`(useCase.getRemoteCountryUseCase("TestCountry")).thenReturn(flowOf(successResource))

            // Observe the countryState Flow
            viewModel.countryState.value = countryStateFlow
            val countryStateObserver = viewModel.countryState.test()

            // Trigger the method to get country
            viewModel.getCountry("TestCountry")

            // Assert loading state
            assert(countryStateObserver.expectItem().isLoading)

            // Assert country state
            assert(countryStateObserver.expectItem().countryState == country)

            // Assert loading state should be false now
            assert(!countryStateObserver.expectItem().isLoading)

            // Cancel the observer
            countryStateObserver.cancel()
        }
    }

    // Add more tests for other scenarios like error cases, etc.
}
