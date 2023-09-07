package com.igalaxy.android.junior_test_userlist.di

import com.google.gson.Gson
import com.igalaxy.android.junior_test_userlist.data.api.UserApi
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
object RetrofitModule {

    //готовим для инжекшена gson
    @Singleton
    @Provides
    fun provideGson(): Gson {
        val dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
        return Gson().newBuilder()
            .setDateFormat(dateFormat)
            .create()
    }

    //готовим для инжекшена okhttpclient

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
            //.addInterceptor(logging)
        return OkHttpClient.Builder().build()
    }

    //создаем обьект ретрофита чтобы когда юзаем не создавать
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com/")
             .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    //указываем что именно наш АПи мы юзаем для ретрофита
    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

}