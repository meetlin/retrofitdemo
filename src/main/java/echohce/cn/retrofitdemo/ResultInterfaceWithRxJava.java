package echohce.cn.retrofitdemo;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lin on 2016/4/21.
 */
public interface ResultInterfaceWithRxJava {
    @GET("apistore/mobilenumber/mobilenumber")
    Observable<ResultSet> getPhoneResult(
            @Header("apikey") String key,
            @Query("phone") String phone
    );
}
