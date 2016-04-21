package echohce.cn.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.et);
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                retrofitQuery();
                retrofitQueryWithRxJava();
            }
        });
    }

    private static final String BASE_URL = "http://apis.baidu.com";
    private static final String API_KEY = "79a8828cfd689c01ff060c142dabdce7";

    private void retrofitQuery() {
        // 1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create()) // 解析方法Gson
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        // 创建访问API的请求
        ResultInterface service = retrofit.create(ResultInterface.class);
        Call<ResultSet> call = service.getResult(API_KEY, editText.getText().toString());

        // 发送请求
        call.enqueue(new Callback<ResultSet>() {
            @Override
            public void onResponse(Call<ResultSet> call, Response<ResultSet> response) {
                if (response.isSuccessful()) {
                    ResultSet resultSet = response.body();
                    if (resultSet != null) {
                        ResultSet.RetData retData = resultSet.getRetData();
                        Toast.makeText(MainActivity.this, retData.getProvince() + " " + retData.getCity(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultSet> call, Throwable t) {

            }
        });
    }

    private void retrofitQueryWithRxJava()
    {
        SinglePhoneApi phoneApi = SinglePhoneApi.getApi();
                    ResultInterfaceWithRxJava phoneService = phoneApi.getService();
                    phoneService.getPhoneResult(SinglePhoneApi.API_KEY,editText.getText().toString())
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ResultSet>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                    @Override
                    public void onNext(ResultSet resultSet) {
                        if (resultSet!=null && resultSet.getErrNum() == 0)
                        {
                            ResultSet.RetData data = resultSet.getRetData();
                            Toast.makeText(MainActivity.this, data.getCity(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
