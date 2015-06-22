package cn.car4s.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.car4s.app.AppConfig;
import cn.car4s.app.R;
import cn.car4s.app.bean.SettingBean;
import cn.car4s.app.bean.WebviewBean;
import cn.car4s.app.ui.activity.FeedbackActivity;
import cn.car4s.app.ui.activity.IBase;
import cn.car4s.app.ui.activity.WebviewActivity;
import cn.car4s.app.ui.widget.SettingLayout;

import java.util.List;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/4/22.
 */
public class Tab3Fragment extends BaseFragment implements IBase {
    View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_tab3, null);
        initUI();
        initData();
        return rootview;
    }

    @Override
    public void initUI() {
        ((TextView) rootview.findViewById(R.id.tv_actionbar_title)).setText(getString(R.string.tab3));
        rootview.findViewById(R.id.layout_actionbar_all).setBackgroundResource(R.color.transparent);
        SettingLayout mLayoutKeyongjifen = (SettingLayout) rootview.findViewById(R.id.setting_keyongjifen);
        SettingLayout mLayoutdongjiejifen = (SettingLayout) rootview.findViewById(R.id.setting_dongjiejifen);
        SettingLayout mLayoutfeedback = (SettingLayout) rootview.findViewById(R.id.setting_feedback);
        SettingLayout mLayoutAboutus = (SettingLayout) rootview.findViewById(R.id.setting_aboutus);
        mLayoutfeedback.setOnClickListener(onClickListener);
        mLayoutAboutus.setOnClickListener(onClickListener);


        List<SettingBean> listData = SettingBean.createSettingData();
        mLayoutKeyongjifen.setData(listData.get(0));
        mLayoutdongjiejifen.setData(listData.get(1));
        mLayoutfeedback.setData(listData.get(2));
        mLayoutAboutus.setData(listData.get(3));
    }

    @Override
    public void initData() {

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.setting_feedback:
                    mIntent = new Intent(getActivity(), FeedbackActivity.class);
                    startActivity(mIntent);
                    break;
                case R.id.setting_aboutus:
                    mIntent = new Intent(getActivity(), WebviewActivity.class);
                    WebviewBean bean = new WebviewBean("关于我们", "http://www.baidu.com", false);
                    mIntent.putExtra(AppConfig.INTENT_PARA_KEY_BEAN, bean);
                    startActivity(mIntent);
                    break;

            }
        }
    };
}
