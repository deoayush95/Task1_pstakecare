package rest;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.ArrayList;

import models.GitResults;
import models.Items;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;

/**
 * Created by Ashiq Uz Zoha on 9/13/15.
 * Dhrubok Infotech Services Ltd.
 * ashiq.ayon@gmail.com
 */
public class RestClient {

    private static GitApiInterface gitApiInterface ;
    private static String baseUrl = "http://staging.pstakecare.com/" ;

    public static GitApiInterface getClient() {
        if (gitApiInterface == null) {


            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });
            okClient.interceptors().add(logging);

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            gitApiInterface = client.create(GitApiInterface.class);
        }
        return gitApiInterface ;
    }

    public interface GitApiInterface {

        @GET("/mock/countries.json")
        Call<ArrayList<Items>> getUsersNamedTom();

    }

}
