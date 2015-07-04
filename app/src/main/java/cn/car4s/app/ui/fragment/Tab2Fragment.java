package cn.car4s.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.car4s.app.R;
import cn.car4s.app.api.HttpCallback;
import cn.car4s.app.bean.OrderBean;
import cn.car4s.app.ui.activity.IBase;
import cn.car4s.app.ui.activity.ProductDetailActivity;
import cn.car4s.app.ui.adapter.OrderAdapter;
import cn.car4s.app.ui.widget.xlistview.XListView;
import cn.car4s.app.util.ToastUtil;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/4/22.
 */
public class Tab2Fragment extends BaseFragment implements IBase {
    View rootview;
    XListView listView;
    List<Object> list = new ArrayList<Object>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_tab2, null);
        initUI();
        initData();
        return rootview;
    }

    OrderAdapter adapter;

    @Override
    public void initUI() {
        ((TextView) rootview.findViewById(R.id.tv_actionbar_title)).setText(getString(R.string.tab2));
        listView = (XListView) rootview.findViewById(R.id.listview);
        listView.setXListViewListener(new MyRefreshListener());
        adapter = new OrderAdapter(list, getActivity(), orderDo);
        listView.setAdapter(adapter);
    }


    HttpCallback callback = new HttpCallback() {
        @Override
        public void onFailure(Request request, IOException e) {
            listView.stopRefresh();
            listView.stopLoadMore(false);
        }

        @Override
        public void onResponse(String result) {
            listView.stopRefresh();
            listView.stopLoadMore(false);
            Log.e("--->", "" + result);
            if (mPageNo == 1) {
                list.clear();
            }
            list.addAll(new OrderBean().getData(result));
            adapter.notifyDataSetChanged();
            if (list.size() == 0) {
                ToastUtil.showToastShort("暂无订单");
            }
        }
    };


    int mPageNo;

    public void load(Boolean isRefresh) {
        if (isRefresh) {
            mPageNo = 1;
        }
        new OrderBean().getorderList(callback, mPageNo, -1);
    }

    @Override
    public void initData() {
        load(true);
    }

    class MyRefreshListener implements XListView.IXListViewListener {
        @Override
        public void onRefresh() {
            load(true);
        }

        @Override
        public void onLoadMore() {
            load(false);
        }
    }

    OrderAdapter.OrderDo orderDo = new OrderAdapter.OrderDo() {

        @Override
        public void cancelOrder(OrderBean bean) {
            new OrderBean().cancelOrder(callbackCancelorder, bean.OrderID);
        }

        @Override
        public void zhifu(OrderBean orderBean) {
            Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
            intent.putExtra("type", 1);
            intent.putExtra("orderid", orderBean.OrderID);
            startActivity(intent);
        }

        @Override
        public void edit(OrderBean bean) {
            Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
            intent.putExtra("type", 2);
            intent.putExtra("orderid", bean.OrderID);
            startActivity(intent);
        }
    };
    HttpCallback callbackCancelorder = new HttpCallback() {
        @Override
        public void onFailure(Request request, IOException e) {
        }

        @Override
        public void onResponse(String result) {
            Log.e("--->", "" + result);
            load(true);
        }
    };

}
