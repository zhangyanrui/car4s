package cn.car4s.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.car4s.app.AppConfig;
import cn.car4s.app.R;
import cn.car4s.app.bean.SettingBean;
import cn.car4s.app.bean.UserBean;
import cn.car4s.app.ui.widget.SettingLayout;
import cn.car4s.app.util.PreferencesUtil;

import java.util.List;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/4/22.
 */
public class EditProfileActivity extends BaseActivity implements IBase {
    @InjectView(R.id.btn_actionbar_back_img)
    ImageView mActionbarBack;
    @InjectView(R.id.tv_actionbar_title)
    TextView mActionbarTitle;
    @InjectView(R.id.btn_login)
    TextView mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        ButterKnife.inject(this);
        initData();
        initUI();
    }

    @Override
    public void initUI() {
        mActionbarBack.setVisibility(View.VISIBLE);
        mActionbarBack.setImageResource(R.mipmap.ic_loginactivity_back);
        mActionbarBack.setOnClickListener(onClickListener);
        mActionbarTitle.setText("我的信息");
        mBtnLogin.setOnClickListener(onClickListener);


        SettingLayout mLayoutKeyongjifen = (SettingLayout) findViewById(R.id.setting_keyongjifen);
        SettingLayout mLayoutdongjiejifen = (SettingLayout) findViewById(R.id.setting_dongjiejifen);
        SettingLayout mLayoutfeedback = (SettingLayout) findViewById(R.id.setting_feedback);
        SettingLayout mLayoutAboutus = (SettingLayout) findViewById(R.id.setting_aboutus);

        List<SettingBean> listData = SettingBean.createEditUser(mUserbean);
        mLayoutKeyongjifen.setData(listData.get(0));
        mLayoutdongjiejifen.setData(listData.get(1));
        mLayoutfeedback.setData(listData.get(2));
        mLayoutAboutus.setData(listData.get(3));

        mLayoutKeyongjifen.setOnClickListener(onClickListener);
        mLayoutdongjiejifen.setOnClickListener(onClickListener);
        mLayoutfeedback.setOnClickListener(onClickListener);
        mLayoutAboutus.setOnClickListener(onClickListener);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btn_actionbar_back_img:
                    finish();
                    break;
                case R.id.btn_login://loginout
                    PreferencesUtil.putPreferences(AppConfig.SP_KEY_USERINFO, "");
                    setResult(Activity.RESULT_OK, null);
                    finish();
                    break;
                case R.id.setting_keyongjifen://loginout

                    break;
                case R.id.setting_dongjiejifen://loginout
                    break;
                case R.id.setting_feedback://loginout
                    break;
                case R.id.setting_aboutus://loginout
                    break;


            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConfig.REQUEST_CODE_RESETPWD && resultCode == Activity.RESULT_OK) {

        }

        if (requestCode == AppConfig.REQUEST_CODE_REGISTER && resultCode == Activity.RESULT_OK) {

        }
    }

    @Override
    public void initData() {
        mUserbean = UserBean.getLocalUserinfo();
    }
}
