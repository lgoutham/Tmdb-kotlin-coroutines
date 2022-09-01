package com.example.tmdb.di

import com.example.tmdb.network.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().clear()
        builder.addInterceptor(ApiKeyInterceptor())
        builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun providesTvSeriesService(retrofit: Retrofit): TvSeriesService {
        return retrofit.create(TvSeriesService::class.java)
    }

    @Provides
    @Singleton
    fun providesCelebritiesService(retrofit: Retrofit): CelebritiesService {
        return retrofit.create(CelebritiesService::class.java)
    }

    @Provides
    @Singleton
    fun providesSearchService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }
}