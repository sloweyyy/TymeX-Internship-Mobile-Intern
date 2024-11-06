import com.example.currencyconverter.data.repository.CurrencyRepositoryImpl
import com.example.currencyconverter.domain.repository.CurrencyRepository
import com.example.currencyconverter.domain.usecases.GetExchangeRates
import com.example.currencyconverter.domain.usecases.GetSupportedCurrencies
import com.example.currencyconverter.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RetrofitInstance.apiService }

    single<CurrencyRepository> { CurrencyRepositoryImpl(get()) }

    factory { GetSupportedCurrencies(get()) }
    factory { GetExchangeRates(get()) }

    viewModel { MainViewModel(get(), get()) }
}