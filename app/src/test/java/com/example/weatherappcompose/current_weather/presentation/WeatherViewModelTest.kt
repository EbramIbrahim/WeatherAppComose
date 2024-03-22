package com.example.weatherappcompose.current_weather.presentation


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherappcompose.core.common.Resource
import com.example.weatherappcompose.core.presentation.CountryState
import com.example.weatherappcompose.core.presentation.ForecastState
import com.example.weatherappcompose.current_weather.domain.models.Country
import com.example.weatherappcompose.current_weather.domain.usecase.UseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
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

            `when`(useCase.getRemoteCountryUseCase("TestCountry")).thenReturn(flowOf(successResource))


            val countryStatCollector = viewModel.countryState


            viewModel.getCountryFromRemote("TestCountry")

            // Assert loading state
            assert(countryStatCollector.value.isLoading)

            // Assert country state
            assert(countryStatCollector.value.country == country[0])

            // Assert loading state should be false now
            assert(!countryStatCollector.value.isLoading)

        }
    }

        @Test
        fun `test country flows`(): Unit = runBlockingTest {
            // Mocking the behavior of UseCase
            val country = listOf(Country("TestCountry", "", 0.0, 0.0, ""))
            val successResource = Resource.Success(country)
            `when`(useCase.getRemoteCountryUseCase("TestCountry")).thenReturn(MutableStateFlow(successResource))

            // Set up observer for countryState
            val countryStateObserver = mutableListOf<CountryState>()
            viewModel.countryState.collect { countryStateObserver.add(it) }

            // Trigger the method to get country
            viewModel.getCountryFromRemote("TestCountry")

            // Delay for a moment to let the flow emit the result
            delay(1000)

            // Assert loading state
            assert(countryStateObserver[0].isLoading)

            // Assert country state
            assert(countryStateObserver[1].countryState == country)

            // Assert loading state should be false now
            assert(!countryStateObserver[2].isLoading)
        }

    }

