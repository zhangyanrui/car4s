package cn.car4s.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.car4s.app.AppConfig;
import cn.car4s.app.R;
import cn.car4s.app.bean.WebviewBean;
import cn.car4s.app.ui.activity.IBase;
import cn.car4s.app.ui.activity.WebviewActivity;
import cn.car4s.app.util.DeviceUtil;
import cn.car4s.app.util.DialogUtil;

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

    @Override
    public void initUI() {
        TextView mActionbarBack = (TextView) rootview.findViewById(R.id.btn_actionbar_back_text);
        mActionbarBack.setText("选择网店");
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

    @Override
    public void initData() {

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btn_actionbar_back_text:
                    break;
                case R.id.btn_actionbar_conform_img:
                    DialogUtil.buildTelDialog(getActivity());
                    break;
                case R.id.btn_shengqian:
                    break;
                case R.id.btn_zhengqian:
                    mIntent = new Intent(getActivity(), WebviewActivity.class);
                    WebviewBean bean = new WebviewBean("挣钱", "http://baike.baidu.com/subview/555/5133091.htm", true);
                    mIntent.putExtra(AppConfig.INTENT_PARA_KEY_BEAN, bean);
                    startActivity(mIntent);
                    break;
                case R.id.btn_tiqian:
                    break;
            }
        }
    };
}
