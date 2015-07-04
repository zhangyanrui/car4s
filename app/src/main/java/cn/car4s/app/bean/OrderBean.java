package cn.car4s.app.bean;

import cn.car4s.app.AppConfig;
import cn.car4s.app.api.ApiService;
import cn.car4s.app.api.HttpCallback;
import cn.car4s.app.util.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/4/22.
 */
public class OrderBean extends BaseBean {


//    参数名称	说明	备注	传参方式
//    action	AddOrder	必填
//    Token	用户密钥	必填	header
//    StationID	网点ID	必填
//    SeriesID	车系ID	必填
//    ServiceData	预约服务日期	必填	格式：yyyy-MM-dd
//    ServiceTimeID	预约服务时间ID	必填
//    TechnicianID	技师ID	必填
//    ProductID	产品ID	必填


    public String StationID;
    public String SeriesID;
    public String ServiceData;
    public String ServiceTimeID;
    public String TechnicianID;
    public String ProductID;

    public OrderBean() {
    }

    public OrderBean(String stationID, String seriesID, String serviceData, String serviceTimeID, String technicianID, String productID) {

        StationID = stationID;
        SeriesID = seriesID;
        ServiceData = serviceData;
        ServiceTimeID = serviceTimeID;
        TechnicianID = technicianID;
        ProductID = productID;
    }

    public void addorder(HttpCallback callback, OrderBean bean) {
        Map map = new HashMap();
        map.put("action", "AddOrder");
        map.put("StationID", "" + bean.StationID);
        map.put("SeriesID", "" + bean.SeriesID);
        map.put("ServiceData", "" + bean.ServiceData);
        map.put("ServiceTimeID", "" + bean.ServiceTimeID);
        map.put("TechnicianID", "" + bean.TechnicianID);
        map.put("ProductID", "" + ProductID);
        NetUtil.doPostMap(AppConfig.APP_SERVER + ApiService.INTERFACE_PRODUCT, map, callback);
    }

    static Type list_type = new TypeToken<List<OrderBean>>() {
    }.getType();

    public static List<OrderBean> getData(String json) {
        List<OrderBean> list = null;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("Data");
            list = new Gson().fromJson(array.toString(), list_type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
