package cn.car4s.app.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.car4s.app.AppConfig;
import cn.car4s.app.R;
import cn.car4s.app.util.PreferencesUtil;
import cn.car4s.app.util.ToastUtil;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import net.sourceforge.simcpux.Constants;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);

        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            switch (resp.errCode) {
                case 0:
//                    ToastUtil.showToastShort("支付成功");
                    PreferencesUtil.putPreferences(AppConfig.SP_KEY_WXPAY, true);
                    break;
                case -1:
                    ToastUtil.showToastShort("支付失败");
                    break;
                case -2:
                    ToastUtil.showToastShort("支付取消");
                    break;
            }
            finish();

//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle(R.string.app_tip);
//            builder.setMessage(getString(R.string.pay_result_callback_msg, resp.errStr + ";code=" + String.valueOf(resp.errCode)));
//            builder.show();
        }
    }
}