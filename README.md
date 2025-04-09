# Product Catalog App

A Kotlin Multiplatform Mobile (KMM) app that displays a list of products and their details using Jetpack Compose and MVVM architecture.

## Features
- Product list with thumbnails, titles, and descriptions
- Product detail screen with image carousel, rating, price, and full description
- Error handling and retry mechanism
- Loading indicators
- Clean architecture with separation of concerns

## Setup
1. Clone the repository
2. Open in Android Studio (latest stable version recommended)
3. Build and run the app

## Technical Stack
- Kotlin
- Jetpack Compose
- MVVM architecture
- Retrofit for API calls
- Coil for image loading
- Navigation Compose

## Assumptions
- API will always return valid data structure
- Internet connection is required for first load
- No offline caching implemented

## Bonus Implementations
- Unit tests for ViewModel
- Error handling with retry option
- Loading indicators
- Image carousel for product details
- Rating component
