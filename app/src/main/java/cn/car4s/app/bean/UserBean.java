package cn.car4s.app.bean;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import cn.car4s.app.AppConfig;
import cn.car4s.app.api.HttpCallback;
import cn.car4s.app.ui.activity.LoginActivity;
import cn.car4s.app.util.NetUtil;
import cn.car4s.app.util.PreferencesUtil;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/4/22.
 */
public class UserBean extends BaseBean {

    public String UserID;
    public String PhoneNumber_R;
    public String PhoneNumber_B;
    public String UserName;
    public String Sex;
    public String Birthday;
    public String Address;
    public String ProvinceID;
    public String CityID;
    public String AreaID;
    public String ReferralCode_I;
    public String AvailablePoint;
    public String FreezingPoint;
    public String TotalPoint;
    public String RegisterTime;
    public String ParentUserID;
    public String LastLoginTime;
    public String HeadPicturePath;
    public String Token;


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

    public static boolean checkUserLoginStatus() {
        if (!"".equals(PreferencesUtil.getPreferences(AppConfig.SP_KEY_USERINFO, ""))) {
            return true;
        }
        return false;
    }

    public static void toLogin(Activity activity, int requestcode) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivityForResult(intent, requestcode);
    }

    public static UserBean getLocalUserinfo() {
        UserBean bean = null;
        String json = PreferencesUtil.getPreferences(AppConfig.SP_KEY_USERINFO, "");
        Log.e("--->", "" + json);
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("Data");
            JSONObject temp = array.getJSONObject(0);
            bean = new Gson().fromJson(temp.toString(), UserBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("--->", "" + bean.AreaID);
        return bean;
    }
}
