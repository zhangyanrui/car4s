package cn.car4s.app.wxpay;

import android.content.Context;
import android.util.Log;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import net.sourceforge.simcpux.Constants;
import net.sourceforge.simcpux.MD5;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/8/5.
 */
public class WxpayUtil {
    static IWXAPI msgApi;
    static PayReq req;

    private static void genPayReq(WXpayBean bean) {
//
//        req.appId = Constants.APP_ID;
//        req.partnerId = Constants.MCH_ID;
//        req.prepayId = resultunifiedorder.get("prepay_id");
//        req.packageValue = "Sign=WXPay";
//        req.nonceStr = genNonceStr();
//        req.timeStamp = String.valueOf(genTimeStamp());


        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", bean.appid));
        signParams.add(new BasicNameValuePair("noncestr", bean.noncestr));
        signParams.add(new BasicNameValuePair("package", "Sign=WXPay"));
        signParams.add(new BasicNameValuePair("partnerid", bean.partnerid));
        signParams.add(new BasicNameValuePair("prepayid", bean.prepayid));
        signParams.add(new BasicNameValuePair("timestamp", bean.timestamp));

        req.sign = genAppSign(signParams);

//        sb.append("sign\n" + req.sign + "\n\n");
//
//        show.setText(sb.toString());

        Log.e("orion", signParams.toString());

    }

    public static void sendPayReq(Context context, WXpayBean bean) {
        WXAPIFactory.createWXAPI(context, null);
        req = new PayReq();
        genPayReq(bean);
        msgApi.registerApp(Constants.APP_ID);
        msgApi.sendReq(req);
    }

    private static String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);

//        this.sb.append("sign str\n" + sb.toString() + "\n\n");
        String appSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        Log.e("orion", appSign);
        return appSign;
    }

}
