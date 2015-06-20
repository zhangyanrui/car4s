package cn.car4s.app.bean;

import cn.car4s.app.AppConfig;
import cn.car4s.app.util.NetUtil;
import com.squareup.okhttp.Callback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/4/22.
 */
public class UserBean extends BaseBean {

    public String PhoneNumber;
    public String PassWord;

    public UserBean(String phoneNumber, String passWord) {
        PhoneNumber = phoneNumber;
        PassWord = passWord;
    }

    public void login(Callback callback) throws IOException {
        Map map = new HashMap();
        map.put("action", "Login");
        map.put("PhoneNumber", this.PhoneNumber + "");
        map.put("PassWord", this.PassWord + "");
        NetUtil.doPostMap(AppConfig.APP_SERVER, map, callback);
    }


}
