package cn.car4s.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cn.car4s.app.AppConfig;
import cn.car4s.app.R;
import cn.car4s.app.util.PreferencesUtil;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/4/22.
 */
public class ChoosePositionActivity extends BaseActivity implements IBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseposition);
    }


    @Override
    public void initUI() {
        TextView mActionbarBack = (TextView) findViewById(R.id.btn_actionbar_back);
        mActionbarBack.setText("返回");
        mActionbarBack.setVisibility(View.VISIBLE);
        TextView mActionbarConfirm = (TextView) findViewById(R.id.btn_actionbar_conform);
        mActionbarConfirm.setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_actionbar_title)).setText("当前网店：");
        PreferencesUtil.getPreferences(AppConfig.SP_KEY_CHOOSEPOSITION, "");
    }

    @Override
    public void initData() {

    }
}
