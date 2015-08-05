package cn.car4s.app.service;

import cn.car4s.app.AppConfig;
import cn.car4s.app.api.ApiService;
import cn.car4s.app.api.HttpCallback;
import cn.car4s.app.util.NetUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/8/5.
 */
public class WXRequestService {

    public static void getOrder(HttpCallback callback, String orderId) {
        Map map = new HashMap();
        map.put("action", "UnifiedOrder");
        map.put("OrderID", orderId + "");
        NetUtil.doPostMap(AppConfig.APP_SERVER + ApiService.INTERFACE_WEIXINPAY, map, callback);
    }
}
