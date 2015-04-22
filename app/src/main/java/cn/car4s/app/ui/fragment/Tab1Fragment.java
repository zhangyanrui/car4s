package cn.car4s.app.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.car4s.app.R;
import cn.car4s.app.ui.activity.IBase;

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
        rootview.findViewById(R.id.btn_actionbar_back).setVisibility(View.GONE);
        rootview.findViewById(R.id.btn_actionbar_conform).setVisibility(View.GONE);
        ((TextView) rootview.findViewById(R.id.tv_actionbar_title)).setText(getString(R.string.tab1));

        TextView btn_order = (TextView) rootview.findViewById(R.id.btn_order);
        TextView btn_weixiubaoyang = (TextView) rootview.findViewById(R.id.btn_weixiubaoyang);
        TextView btn_tobemember = (TextView) rootview.findViewById(R.id.btn_tobemember);
        btn_order.setOnClickListener(onClickListener);
        btn_weixiubaoyang.setOnClickListener(onClickListener);
        btn_tobemember.setOnClickListener(onClickListener);
    }

    @Override
    public void initData() {

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btn_order:
                    ((FragmentTabHost) getActivity().findViewById(android.R.id.tabhost)).setCurrentTabByTag("tab2");
                    break;
                case R.id.btn_weixiubaoyang:
                    break;
                case R.id.btn_tobemember:
                    break;
            }
        }
    };
}
