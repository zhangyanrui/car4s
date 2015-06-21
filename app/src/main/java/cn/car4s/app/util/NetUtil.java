package cn.car4s.app.util;

import android.os.Handler;
import android.util.Log;
import cn.car4s.app.AppContext;
import cn.car4s.app.api.HttpCallback;
import cn.car4s.app.bean.NetReturnBean;
import com.google.gson.Gson;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.Map;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/5/12.
 */
public class NetUtil {

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Handler handler = new Handler(AppContext.getInstance().getMainLooper());
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String doGet(String request_url) throws IOException {
        Request request = new Request.Builder().url(request_url).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().toString();
    }


    public static String doPostJson(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    static NetReturnBean bean;

    public static void doPostMap(String url, Map<String, String> map, final HttpCallback callback) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        RequestBody body = builder.build();
        final Request request = new Request.Builder().url(url).post(body).build();

        Callback asyncCallback = new Callback() {


            @Override
            public void onFailure(final Request request, final IOException e) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToastShort(e.toString());
//                        callback.onFailure(request, e);
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                String result = null;
                if (response != null) {
                    result = response.body().string();
                }
                Log.e("onResponse", "" + result);
                bean = new Gson().fromJson(result, NetReturnBean.class);
                if (bean == null) return;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
//                        ToastUtil.showToastShort(bean.Message);
                        if ("0".equals(bean.Code)) {
                            callback.onResponse(bean);
                        } else {
                            ToastUtil.showToastShort(bean.Message);
                        }
                    }
                });
            }
        };
        okHttpClient.newCall(request).enqueue(asyncCallback);
    }


}
