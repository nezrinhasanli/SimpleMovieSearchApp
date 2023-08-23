package com.nezrin.movieapp.di

import com.nezrin.movieapp.repo.MovieRepo
import com.nezrin.movieapp.utils.Constant
import com.nezrin.movieapp.retrofit.WebServiceAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleApp {

    @Provides
    @Singleton
    fun provideRepo(api: WebServiceAPI):MovieRepo{
        return MovieRepo(api)
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit):WebServiceAPI{
        return retrofit.create(WebServiceAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val clientBuilder = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

         return Retrofit.Builder()
            .baseUrl(Constant.base_url)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(clientBuilder)
            .build()
    }

}