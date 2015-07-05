package cn.car4s.app.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.car4s.app.AppConfig;
import cn.car4s.app.R;
import cn.car4s.app.api.HttpCallback;
import cn.car4s.app.bean.SettingBean;
import cn.car4s.app.bean.UserBean;
import cn.car4s.app.ui.widget.SettingLayout;
import cn.car4s.app.ui.widget.SettingLayoutSmall;
import cn.car4s.app.util.DialogUtil;
import cn.car4s.app.util.PreferencesUtil;
import com.squareup.okhttp.Request;

import java.io.IOException;
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


        SettingLayoutSmall mLayoutKeyongjifen = (SettingLayoutSmall) findViewById(R.id.setting_keyongjifen);
        SettingLayoutSmall mLayoutdongjiejifen = (SettingLayoutSmall) findViewById(R.id.setting_dongjiejifen);
        SettingLayoutSmall mLayoutfeedback = (SettingLayoutSmall) findViewById(R.id.setting_feedback);
        SettingLayoutSmall mLayoutAboutus = (SettingLayoutSmall) findViewById(R.id.setting_aboutus);

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
                case R.id.setting_keyongjifen://
                    showNameDialog();
                    break;
                case R.id.setting_dongjiejifen://
                    break;
                case R.id.setting_feedback://
                    break;
                case R.id.setting_aboutus://
                    break;


            }
        }
    };
    HttpCallback callbackUpdate = new HttpCallback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(String result) {
            Log.e("--->", "" + result);
            mUserbean.refresh(callbackRefresh, mUserbean.Token);
        }
    };
    HttpCallback callbackRefresh = new HttpCallback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(String result) {
            Log.e("--->", "" + result);
            PreferencesUtil.putPreferences(AppConfig.SP_KEY_USERINFO, result);
            initData();
            initUI();
        }
    };

    public void showNameDialog() {
        View view = View.inflate(this, R.layout.dialog_edit, null);
        final Dialog dialog = DialogUtil.buildDialog(this, view, Gravity.CENTER, R.style.BottomDialogAnimation, true);
        final EditText dialog_edt = (EditText) view.findViewById(R.id.edt);
        View dialog_upload_sure = view.findViewById(R.id.dialog_upload_sure);
        dialog_upload_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String name = dialog_edt.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    mUserbean.UserName = name;
                    mUserbean.updateProfile(callbackUpdate, mUserbean);
                }
            }
        });
        View dialog_upload_cancel = view.findViewById(R.id.dialog_upload_cancel);
        dialog_upload_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


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
