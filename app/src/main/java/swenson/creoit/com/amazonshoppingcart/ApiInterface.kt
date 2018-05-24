package swenson.creoit.com.amazonshoppingcart

import android.util.Log
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



interface ApiInterface {

    @GET("/json/movies_2017.json")
    fun getMoviesName() : Observable<Response<ArrayList<MyMovie>>>

   companion object RetrofitFactory {

       fun create() : ApiInterface {

           val retrofit = Retrofit.Builder()
                   .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                   .addConverterFactory(GsonConverterFactory.create())
                   .baseUrl("https://api.androidhive.info")
                   .client(OkHttpClient.Builder().addNetworkInterceptor(
                           HttpLoggingInterceptor(
                                   HttpLoggingInterceptor.Logger {
                                       Log.i("ApiInterface", ": $it")
                                   }
                           ).setLevel(HttpLoggingInterceptor.Level.BASIC)).build())
                   .build()

           return retrofit.create(ApiInterface::class.java)
       }
   }
}