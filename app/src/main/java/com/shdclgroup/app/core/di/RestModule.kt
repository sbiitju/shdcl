package com.shdclgroup.app.core.di
import android.content.Context
import android.util.Log
import com.shdclgroup.app.core.network.ConnectivityAndInternetAccess
import com.shdclgroup.app.prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.shdclgroup.app.BuildConfig
import com.shdclgroup.app.features.history.data.datasources.HistoryApi
import com.shdclgroup.app.features.home.data.remote.ProjectApi
import com.shdclgroup.app.features.login.data.remote.LoginApi
import okhttp3.logging.HttpLoggingInterceptor


@InstallIn(SingletonComponent::class)
@Module
class RestModule {

    @Provides
    @Singleton
    fun provideHttpClient(@ApplicationContext appContext: Context): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(45, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(getAuthInterceptor(appContext))

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
        }

        return clientBuilder.build()
    }

    fun getAuthInterceptor(appContext: Context): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val requestBuilder = chain.request().newBuilder()
                val token = prefs.get<String>("JWT")
                if (!token.isNullOrEmpty()) {
                    Log.d("dbg", "Token: Bearer $token")
                    requestBuilder.header("Authorization", "Bearer $token")
                        .addHeader("Cache-control", "no-cache")
                }
                try {
                    return chain.proceed(requestBuilder.build())
                } catch (e: Exception) {
                    if (ConnectivityAndInternetAccess.isConnectedToInternet(
                            appContext,
                            chain.request().url.host
                        )
                    )
                        throw e
                    else
                        throw e
                }
            }
        }
    }


    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }


//
//    @Provides
//    @Singleton
//    fun provideTeacherApi(retrofit: Retrofit): TeacherApi {
//        return retrofit.create(TeacherApi::class.java)
//    }
//    @Provides
//    @Singleton
//    fun provideStudentApi(retrofit: Retrofit): StudentApi {
//        return retrofit.create(StudentApi::class.java)
//    }
//
    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProjectApi(retrofit: Retrofit): ProjectApi {
        return retrofit.create(ProjectApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHistoryApi(retrofit: Retrofit): HistoryApi {
        return retrofit.create(HistoryApi::class.java)
    }
//
//
//    @Provides
//    @Singleton
//    fun provideDraftOrderApi(retrofit: Retrofit): DraftOrderApi {
//        return retrofit.create(DraftOrderApi::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideTourPlanApi(retrofit: Retrofit): TourPlanApi {
//        return retrofit.create(TourPlanApi::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideNewTourPlanApi(retrofit: Retrofit): NewTourPlanApi {
//        return retrofit.create(NewTourPlanApi::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideAppSyncApi(retrofit: Retrofit): AppSyncApi {
//        return retrofit.create(AppSyncApi::class.java)
//    }
//    @Provides
//    @Singleton
//    fun provideChemistApi(retrofit: Retrofit): ChemistApi {
//        return retrofit.create(ChemistApi::class.java)
//    }
//    @Provides
//    @Singleton
//    fun provideVisitPlanApi(retrofit: Retrofit): VisitPlanApi {
//        return retrofit.create(VisitPlanApi::class.java)
//    }
//    @Provides
//    @Singleton
//    fun provideVisitActivity(retrofit: Retrofit): VisitActivityApi {
//        return retrofit.create(VisitActivityApi::class.java)
//    }

}