package cn.car4s.app.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.car4s.app.R;
import cn.car4s.app.api.HttpCallback;
import cn.car4s.app.bean.ProductBean;
import cn.car4s.app.ui.adapter.ProductAdapter;
import cn.car4s.app.ui.widget.RecyclerItemClickListener;
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
public class CarBaoyangActivity extends BaseActivity implements IBase {
    @InjectView(R.id.btn_actionbar_back_img)
    ImageView mActionbarBack;
    @InjectView(R.id.tv_actionbar_title)
    TextView mActionbarTitle;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerView;

    List<ProductBean> list = new ArrayList<ProductBean>();
    ProductAdapter adapter;
    RecyclerItemClickListener itemlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbaoyang);
        ButterKnife.inject(this);
        initUI();
        initData();
    }

    @Override
    public void initUI() {
        mActionbarBack.setVisibility(View.VISIBLE);
        mActionbarBack.setImageResource(R.mipmap.ic_loginactivity_back);
        mActionbarBack.setOnClickListener(onClickListener);
        mActionbarTitle.setText("汽车保养");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ProductAdapter(list, this);
        recyclerView.setAdapter(adapter);
        itemlistener = new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                ShengqianGridBean bean = list.get(position);
//                if (bean.isSelcted) {
//
//                } else {
//                    ToastUtil.showToastShort("暂未开放");
//                }
            }
        });
        recyclerView.addOnItemTouchListener(itemlistener);
    }

    HttpCallback callback = new HttpCallback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(String result) {
            Log.e("--->", "" + result);
            list.addAll(ProductBean.getData(result));
            adapter.notifyDataSetChanged();

        }
    };


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btn_actionbar_back_img:
                    finish();
                    break;
            }
        }
    };

    private ProductBean mProductBean = null;

    @Override
    public void initData() {
        mProductBean = new ProductBean(0, 0, 1, true);
        loadData();
    }

    public void loadData() {
        mProductBean.getProductList(callback, mProductBean);
    }
}
