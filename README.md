# Weather Forecast App

## Features Requirements:
 * Using any weather APi, request the weather from any location;
 * Create an UI that can show next weather's from this location;
 * Since weather doesn't change frequently we don't need to request all the time, this way we can save mobile data. So create a cache to solve this issue;
 * App must work in landscape and portrait orientation;

## Architecture Requirements:
 * Develop in Kotlin;
 * Use MVVM as architecture;
 * Use Retrofit to request from API and cache;
 * Use LiveData to keep the app lifecycle aware;

## Guide:
 * You may want to use https://openweathermap.org/api.
 * Retrofit: https://square.github.io/retrofit.

## Solution summary:
 * Project has separated into a two main sub-packages inside the 'weatherforecastapp' package.
   * 'data' package (into this package we have other sub-packages as model, response, service, utils), the idea is allocate here all Non visual features, resources that interact directly with data layer.
   * 'ui' package (into this package we have the main sub-package), the idea is allocate here all Activities, ViewModels and Adapters.
 * RetrofitService was use to request from API or cache data.
   * Was set baseUrl as 'https://api.openweathermap.org/data/2.5/'.
   * Was set Cache Interceptor.
 * Was used the API endpoint: 'forecast' since return a list of next weather's starting by current.
   * ApiService interface define 'getForecasts' method with 'q' and 'APPID' params.
   * 'q' its the API query param, defined as a '{{city}},{{country}}' pair, example: 'Manaus, BR'.
   * 'APPID' param its the API generated-key,  mandatory  for all request.
 * Based on API response were build some models:
   * 'Weather' model, to attend ui layer as an already formatted data, ready to be shown.
   * 'ForecastBodyResponse' model and its dependencies, to parse response body, transforming into structured data.
 * Based on the data models created, the view model was created and the views were designed.
 * 'ForecastActivity', it's the main activity to inflate the 'forecast_activity' layout.
 * 'ForecastViewModel', it's the view model for 'ForecastActivity'.
 * 'ForecastAdapter', it's the recyclerview adapter to inflate the 'recycler_item' layout.
 * 'forecast_activity' layout it's a FrameLayout, compose by, vertical and/or horizontal LinearLayouts, a top search bar, a top CardView enclosing the current weather information, and a RecyclerView enclosing the list of forecasts.
 * Was added 'screenshots' folder with some app images as a preview of the app.

## Solution Requirements:
 * Location access permission.
 * Internet access permission.
 * Sometimes you must need to clean: App info/Storage/Cache