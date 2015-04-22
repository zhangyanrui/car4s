package cn.car4s.app.ui.fragment;

import android.os.Bundle;
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
        rootview.findViewById(R.id.btn_actionbar_back).setVisibility(View.GONE);
        rootview.findViewById(R.id.btn_actionbar_conform).setVisibility(View.GONE);
        ((TextView) rootview.findViewById(R.id.tv_actionbar_title)).setText(getString(R.string.tab3));
    }

    @Override
    public void initData() {

    }
}
