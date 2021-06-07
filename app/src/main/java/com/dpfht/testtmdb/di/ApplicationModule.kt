package com.dpfht.testtmdb.di

import android.content.Context
import com.dpfht.testtmdb.BuildConfig
import com.dpfht.testtmdb.Config
import com.dpfht.testtmdb.TheApplication
import com.dpfht.testtmdb.repository.AppRepository
import com.dpfht.testtmdb.repository.AppRepositoryImpl
import com.dpfht.testtmdb.rest.RestService
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val theApplication: TheApplication) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return theApplication
    }

    @Singleton
    @Provides
    fun provideRestService(retrofit: Retrofit): RestService {
        return retrofit.create(RestService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Config.API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideClient(
        certificatePinner: CertificatePinner,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient()
            .newBuilder()
            .certificatePinner(certificatePinner)

        if (!httpClientBuilder.interceptors().contains(httpLoggingInterceptor) && BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        return httpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideCertificatePinner(): CertificatePinner {
        return CertificatePinner.Builder()
            .add("api.themoviedb.org", "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add("api.themoviedb.org", "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add("api.themoviedb.org", "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()
    }

    @Singleton
    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideAppRepository(restService: RestService): AppRepository {
        return AppRepositoryImpl(restService)
    }
}
