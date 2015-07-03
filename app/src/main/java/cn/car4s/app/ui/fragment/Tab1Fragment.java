package cn.car4s.app.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.car4s.app.AppConfig;
import cn.car4s.app.R;
import cn.car4s.app.bean.WebviewBean;
import cn.car4s.app.ui.activity.*;
import cn.car4s.app.util.DeviceUtil;
import cn.car4s.app.util.DialogUtil;
import cn.car4s.app.util.PreferencesUtil;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/4/22.
 */
public class Tab1Fragment extends BaseFragment implements IBase {
    View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_tab1, null);
        initUI();
        initData();
        return rootview;
    }

    TextView mActionbarBack;

    @Override
    public void initUI() {
        mActionbarBack = (TextView) rootview.findViewById(R.id.btn_actionbar_back_text);
        mActionbarBack.setVisibility(View.VISIBLE);
        mActionbarBack.setOnClickListener(onClickListener);
        ImageView mActionbarConfirm = (ImageView) rootview.findViewById(R.id.btn_actionbar_conform_img);
        mActionbarConfirm.setVisibility(View.VISIBLE);
        mActionbarConfirm.setImageResource(R.mipmap.ic_fragment1_tel);
        mActionbarConfirm.setOnClickListener(onClickListener);
        ((TextView) rootview.findViewById(R.id.tv_actionbar_title)).setText("指尖创业");

        ImageView btn_shengqian = (ImageView) rootview.findViewById(R.id.btn_shengqian);
        ImageView btn_zhengqian = (ImageView) rootview.findViewById(R.id.btn_zhengqian);
        ImageView btn_tiqian = (ImageView) rootview.findViewById(R.id.btn_tiqian);
        btn_shengqian.setOnClickListener(onClickListener);
        btn_zhengqian.setOnClickListener(onClickListener);
        btn_tiqian.setOnClickListener(onClickListener);
        ImageView viewpager = (ImageView) rootview.findViewById(R.id.viewpager);
        viewpager.getLayoutParams().height = (int) (DeviceUtil.getWidth() * 374 / 640);
    }

    String pos_name;

    @Override
    public void initData() {
        pos_name = PreferencesUtil.getPreferences(AppConfig.SP_KEY_CHOOSEPOSITION_NAME, "");
        if (TextUtils.isEmpty(pos_name)) {
            mActionbarBack.setText("选择网点");
        } else {
            mActionbarBack.setText("网点：" + pos_name);
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btn_actionbar_back_text:
                    mIntent = new Intent(getActivity(), ChoosePositionActivity.class);
                    Tab1Fragment.this.startActivityForResult(mIntent, AppConfig.REQUEST_CODE_CHOOSE_POS);
                    break;
                case R.id.btn_actionbar_conform_img:
                    DialogUtil.buildTelDialog(getActivity());
                    break;
                case R.id.btn_shengqian:
                    mIntent = new Intent(getActivity(), ShengqianActivity.class);
                    startActivity(mIntent);
                    break;
                case R.id.btn_zhengqian:
                    mIntent = new Intent(getActivity(), WebviewActivity.class);
                    WebviewBean bean = new WebviewBean("挣钱", "http://baike.baidu.com/subview/555/5133091.htm", true);
                    mIntent.putExtra(AppConfig.INTENT_PARA_KEY_BEAN, bean);
                    startActivity(mIntent);
                    break;
                case R.id.btn_tiqian:
                    mIntent = new Intent(getActivity(), PayDemoActivity.class);
                    startActivity(mIntent);
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConfig.REQUEST_CODE_CHOOSE_POS && resultCode == Activity.RESULT_OK) {
            initData();
        }
    }
}
