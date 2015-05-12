package cn.car4s.app.api;

import cn.car4s.app.util.NetUtil;

import java.io.IOException;

/**
 * Description:ÍøÂç½Ó¿ÚÇëÇó
* Author: Alex
        * Email: xuebo.chang@langtaojin.com
* Time: 2015/5/12.
        */
public class ApiService {

    public static final String url = "https://raw.github.com/square/okhttp/master/README.md";

    public static String test_url() {
        String result = null;
        try {
            result = NetUtil.doGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
