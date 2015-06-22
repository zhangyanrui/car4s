package cn.car4s.app.bean;

import cn.car4s.app.AppConfig;
import cn.car4s.app.api.HttpCallback;
import cn.car4s.app.util.NetUtil;

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
    public String CodeNumber;
    public String ReferralCode;
    public String feedBackText;


    public UserBean(String phoneNumber, String passWord) {
        PhoneNumber = phoneNumber;
        PassWord = passWord;
    }

    public UserBean(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public UserBean(String phoneNumber, String passWord, String codeNumber, String referralCode) {
        PhoneNumber = phoneNumber;
        PassWord = passWord;
        CodeNumber = codeNumber;
        ReferralCode = referralCode;
    }

    public void login(HttpCallback callback) {
        Map map = new HashMap();
        map.put("action", "Login");
        map.put("PhoneNumber", this.PhoneNumber + "");
        map.put("PassWord", this.PassWord + "");
        NetUtil.doPostMap(AppConfig.APP_SERVER, map, callback);
    }


    public void register(HttpCallback callback) {
        Map map = new HashMap();
        map.put("action", "Register");
        map.put("PhoneNumber", this.PhoneNumber + "");
        map.put("PassWord", this.PassWord + "");
        map.put("CodeNumber", this.CodeNumber + "");
        map.put("ReferralCode", this.ReferralCode + "");
        NetUtil.doPostMap(AppConfig.APP_SERVER, map, callback);
    }

    public void getYanzhegnma(HttpCallback callback) {
        Map map = new HashMap();
        map.put("action", "GetCode");
        map.put("PhoneNumber", this.PhoneNumber + "");
        NetUtil.doPostMap(AppConfig.APP_SERVER, map, callback);
    }

    public void resetPwd(HttpCallback callback) {
        Map map = new HashMap();
        map.put("action", "ResetPassword");
        map.put("PhoneNumber", this.PhoneNumber + "");
        map.put("PassWord", this.PassWord + "");
        map.put("CodeNumber", this.CodeNumber + "");
        NetUtil.doPostMap(AppConfig.APP_SERVER, map, callback);
    }


    public void feedback(HttpCallback callback) {
        Map map = new HashMap();
        map.put("action", "AddFeedback");
        map.put("PhoneNumber", this.PhoneNumber + "");
        map.put("text", this.feedBackText + "");
        NetUtil.doPostMap(AppConfig.APP_SERVER, map, callback);
    }
}
