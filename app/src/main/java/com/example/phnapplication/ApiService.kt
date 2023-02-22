package com.example.phnapplication

import androidx.viewbinding.BuildConfig
import com.example.phnapplication.models.Posts
import com.example.phnapplication.models.Users
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.*

val requestInterceptor = Interceptor { chain ->
    val url = chain.request()
        .url
        .newBuilder()
        .build()

    val request = chain
        .request()
        .newBuilder()
        .url(url)
        .build()

    return@Interceptor chain.proceed(request)
}

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient().newBuilder().connectTimeout(50, TimeUnit.SECONDS)
        .readTimeout(50, TimeUnit.SECONDS).writeTimeout(50, TimeUnit.SECONDS)
        .run {
            addInterceptor(interceptor)
        }
        .addInterceptor(requestInterceptor)
        .addInterceptor(interceptor)
        .build()

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply(fun HttpLoggingInterceptor.() {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.BODY
    })
}

val httpLogging = provideLoggingInterceptor()
val okHttpClient = provideOkHttpClient(httpLogging)

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface ApiService {
    @GET("users")
    suspend fun getAllUsers(): ArrayList<Users>

    @GET("posts")
    suspend fun getAllPosts(): ArrayList<Posts>
}

object PhnApi {
    val phnService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}