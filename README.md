
# Project Overview

This project contains multiple challenges, each with its own set of tasks and solutions. Below is the folder structure and a brief description of each component.

## Folder Structure
```
gitattributes
.gitignore
.vscode/
    settings.json
Challenge 1/
src/
├── androidTest/
│   └── java/
│       └── com/example/currencyconverter/
│           └── ApplicationTest.kt
└── main/
    ├── java/
    │   └── com/example/currencyconverter/
    │       └── data/
    │           └── api/
    │               ├── ApiResponse.kt
    │               ├── ApiService.kt
    │               └── ExchangeRatesApi.kt
    │       └── model/
    │           └── Currency.kt
    │       └── repository/
    │           └── CurrencyRepositoryImpl.kt
    │       └── di/
    │           └── AppModule.kt
    │       └── domain/
    │           └── repository/
    │               └── CurrencyRepository.kt
    │       └── usecases/
    │           ├── GetExchangeRates.kt
    │           └── GetSupportedCurrencies.kt
    │       └── presentation/
    │           └── theme/
    │               ├── Color.kt
    │               ├── Theme.kt
    │               └── Type.kt
    │       └── view/
    │           └── MainActivity.kt
    │       └── viewmodel/
    │           └── MainViewModel.kt
    │       └── utils/
    │           ├── RetrofitInstance.kt
    │           └── CurrencyConverterApp.kt
    ├── res/
    │   ├── values/
    │   │   |...
    │   ├── layout/
    │   │   ├── activity_main.xml
    ├── AndroidManifest.xml
    └── build.gradle.kts
Challenge 2/
    Question2.1/
        Product.java
        InventoryManagement.java
    Question2.2/
        MissingNumberFinder.java

README.md
LICENSE
```

## Challenges

### Challenge 1

This challenge includes a Gradle-based project with various configurations and settings. The main components are:


- **androidTest/**: Contains Android instrumentation tests for the application.
- **main/**: Contains the main source code of the application.
- **data/**: Contains data-related classes and interfaces.
  - **api/**: Contains API interfaces and classes for fetching currency data.
- **model/**: Contains data models.
- **repository/**: Contains data repositories for managing currency data.
- **di/**: Contains dependency injection related classes.
- **domain/**: Contains business logic and domain entities.
- **usecases/**: Contains use cases for interacting with domain logic.
- **presentation/**: Contains presentation-related classes.
  - **theme/**: Contains theme-related classes for customizing the app's look and feel.
- **view/**: Contains UI-related classes, such as Activities and Fragments.
- **viewmodel/**: Contains view models responsible for managing UI state and data.
- **utils/**: Contains utility classes and methods.
- **res/**: Contains resources for the application, such as layouts, drawables, strings, and styles.
- **AndroidManifest.xml**: Defines the application's configuration and components.
- **build.gradle.kts**: Defines the build configurations for the project.

### Challenge 2

This challenge includes two questions, each with its own Java class:

- **Question2.1/**:
  - `InventoryManagement.java`: Contains methods to manage inventory, including calculating total value, finding the most expensive product, checking stock, and sorting products by price and quantity.
  - `Product.java`: Contains a class representing a product with properties such as name, price, quantity, and category.

- **Question2.2/**:
  - `MissingNumberFinder.java`: Contains a method to find the missing number in an array.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
