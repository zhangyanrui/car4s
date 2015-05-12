package cn.car4s.app.util;

import com.squareup.okhttp.*;

import java.io.IOException;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/5/12.
 */
public class NetUtil {

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String doGet(String request_url) throws IOException {
        Request request = new Request.Builder().url(request_url).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().toString();
    }


    public static String doPost(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }


}
