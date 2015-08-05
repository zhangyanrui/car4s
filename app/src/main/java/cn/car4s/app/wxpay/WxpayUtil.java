package cn.car4s.app.wxpay;

import android.content.Context;
import android.util.Log;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import net.sourceforge.simcpux.Constants;
import net.sourceforge.simcpux.MD5;
import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/8/5.
 */
public class WxpayUtil {

    private IWXAPI msgApi;
    private PayReq req;


    /**
     * 发起微信支付
     *
     * @param context
     * @param bean
     */
    public void sendPayReq(Context context, WXpayBean bean) {
        msgApi = WXAPIFactory.createWXAPI(context, null);
        req = new PayReq();
        genPayReq(bean);
        msgApi.registerApp(Constants.APP_ID);
        msgApi.sendReq(req);
    }

    private void genPayReq(WXpayBean bean) {
        req.appId = bean.appid;
        req.partnerId = bean.partnerid;
        req.prepayId = bean.prepayid;
        req.packageValue = "Sign=WXPay";
        req.nonceStr = bean.noncestr;
        req.timeStamp = bean.timestamp;

//        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
//        signParams.add(new BasicNameValuePair("appid", req.appId));
//        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
//        signParams.add(new BasicNameValuePair("package", req.packageValue));
//        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
//        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
//        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));
        req.sign = bean.paySign;
//        req.sign = genAppSign(signParams);
//        Log.e("orion", signParams.toString());
    }

//    private String genAppSign(List<NameValuePair> params) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < params.size(); i++) {
//            sb.append(params.get(i).getName());
//            sb.append('=');
//            sb.append(params.get(i).getValue());
//            sb.append('&');
//        }
//        sb.append("key=");
//        sb.append(Constants.API_KEY);
//
//        sb.append("sign str\n" + sb.toString() + "\n\n");
//        String appSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
//        Log.e("orion", appSign);
//
//        return appSign;
//    }

}
