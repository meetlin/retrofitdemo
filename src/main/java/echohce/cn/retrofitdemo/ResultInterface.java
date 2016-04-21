package echohce.cn.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by lin on 2016/4/19.
 */
public interface ResultInterface {
    // 填上相对地址
    @GET("apistore/mobilenumber/mobilenumber")
    Call<ResultSet> getResult(
            @Header("apikey") String apikey,
            @Query("phone") String phone);

}
