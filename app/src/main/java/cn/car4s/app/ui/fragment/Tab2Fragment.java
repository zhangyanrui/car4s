package cn.car4s.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.car4s.app.R;
import cn.car4s.app.ui.activity.IBase;
import cn.car4s.app.ui.activity.LoginActivity;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/4/22.
 */
public class Tab2Fragment extends BaseFragment implements IBase {
    View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_tab2, null);

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);

        initUI();
        initData();
        return rootview;
    }

    @Override
    public void initUI() {
        ((TextView) rootview.findViewById(R.id.tv_actionbar_title)).setText(getString(R.string.tab2));

        RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void initData() {

    }
}
