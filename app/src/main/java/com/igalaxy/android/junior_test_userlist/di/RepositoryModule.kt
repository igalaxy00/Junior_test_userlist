package com.igalaxy.android.junior_test_userlist.di

import com.igalaxy.android.junior_test_userlist.data.UserRepositoryImpl
import com.igalaxy.android.junior_test_userlist.data.api.UserApi
import com.igalaxy.android.junior_test_userlist.domain.UserRepository
import com.igalaxy.android.junior_test_userlist.domain.model.cache.UserCacheMapper
import com.igalaxy.android.junior_test_userlist.domain.model.network.UserNetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides // TODO: REPLACE WITH @Binds ANNOTATION
    fun provideRepository(
        userApi: UserApi,
       // userDao: UserDao,
        userCacheMapper: UserCacheMapper,
        userNetworkMapper: UserNetworkMapper

    ): UserRepository {
        return UserRepositoryImpl( userNetworkMapper,userApi,userCacheMapper)
    }
}