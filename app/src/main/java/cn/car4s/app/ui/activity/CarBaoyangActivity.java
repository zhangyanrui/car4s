package cn.car4s.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

    @InjectView(R.id.btn_chooseCarbrand)
    RelativeLayout mBtnChooseCarbrand;
    @InjectView(R.id.layout_top_dabao)
    LinearLayout mLayoutTopDabao;
    @InjectView(R.id.layout_top_baoyang)
    LinearLayout mLayoutTopBaoyang;
    @InjectView(R.id.btn_dabao)
    TextView mBtnDabao;
    @InjectView(R.id.btn_xiaobao)
    TextView mBtnXiaobao;


    List<ProductBean> list = new ArrayList<ProductBean>();
    ProductAdapter adapter;
    RecyclerItemClickListener itemlistener;

    private int mType;//2hot  0dabao  1xiaobao

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbaoyang);
        mType = getIntent().getIntExtra("type", 2);
        ButterKnife.inject(this);
        initUI();
        initData();
    }

    @Override
    public void initUI() {
        mActionbarBack.setVisibility(View.VISIBLE);
        mActionbarBack.setImageResource(R.mipmap.ic_loginactivity_back);
        mActionbarBack.setOnClickListener(onClickListener);
        if (mType == 0) {
            mActionbarTitle.setText("大保");
            mLayoutTopDabao.setVisibility(View.VISIBLE);
        } else if (mType == 1) {
            mActionbarTitle.setText("小保");
            mLayoutTopDabao.setVisibility(View.VISIBLE);
        } else if (mType == 2) {
            mActionbarTitle.setText("汽车保养");
            mLayoutTopBaoyang.setVisibility(View.VISIBLE);
        }
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

        mBtnChooseCarbrand.setOnClickListener(onClickListener);
        mBtnDabao.setOnClickListener(onClickListener);
        mBtnXiaobao.setOnClickListener(onClickListener);
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
                case R.id.btn_dabao:
                    mIntent = new Intent(CarBaoyangActivity.this, CarBaoyangActivity.class);
                    mIntent.putExtra("type", 0);
                    startActivity(mIntent);
                    break;
                case R.id.btn_xiaobao:
                    mIntent = new Intent(CarBaoyangActivity.this, CarBaoyangActivity.class);
                    mIntent.putExtra("type", 1);
                    startActivity(mIntent);
                    break;
                case R.id.btn_chooseCarbrand:
                    break;
            }
        }
    };

    private ProductBean mProductBean = null;

    @Override
    public void initData() {
        if (mType == 2)
            mProductBean = new ProductBean(mType, 0, 1, true);
        else
            mProductBean = new ProductBean(mType, 0, 1, false);
        loadData();
    }

    public void loadData() {
        mProductBean.getProductList(callback, mProductBean);
    }
}
