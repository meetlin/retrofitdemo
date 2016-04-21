package echohce.cn.retrofitdemo;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lin on 2016/4/21.
 */
public class SinglePhoneApi {
    public static final String BASE_URL = "http://apis.baidu.com/";
    public static final String API_KEY = "79a8828cfd689c01ff060c142dabdce7";

    public static SinglePhoneApi getApi(){
        return ApiHolder.phoneApi;
    }

    private ResultInterfaceWithRxJava service;

    static class ApiHolder{
        private static SinglePhoneApi phoneApi = new SinglePhoneApi();
    }

    private SinglePhoneApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ResultInterfaceWithRxJava.class);
    }

    public ResultInterfaceWithRxJava getService(){
        return service;
    }

}
