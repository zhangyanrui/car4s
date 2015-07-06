package cn.car4s.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.car4s.app.R;
import cn.car4s.app.api.HttpCallback;
import cn.car4s.app.util.ToastUtil;
import com.squareup.okhttp.Request;

import java.io.IOException;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/4/22.
 */
public class PingjiaJishiActivity extends BaseActivity implements IBase {
    @InjectView(R.id.btn_actionbar_back_img)
    ImageView mActionbarBack;
    @InjectView(R.id.tv_actionbar_title)
    TextView mActionbarTitle;


    @InjectView(R.id.edt_feedback_text)
    EditText mEdtText;
    @InjectView(R.id.btn_feedback_commit)
    TextView mBtnCommit;
    @InjectView(R.id.ratingbar1)
    RatingBar ratingBar1;
    @InjectView(R.id.ratingbar2)
    RatingBar ratingBar2;
    @InjectView(R.id.ratingbar3)
    RatingBar ratingBar3;
    @InjectView(R.id.ratingbar4)
    RatingBar ratingBar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jishipingjia);
        ButterKnife.inject(this);
        initData();
        initUI();
    }

    @Override
    public void initUI() {
        mActionbarBack.setVisibility(View.VISIBLE);
        mActionbarBack.setImageResource(R.mipmap.ic_loginactivity_back);
        mActionbarBack.setOnClickListener(onClickListener);
        mBtnCommit.setOnClickListener(onClickListener);
        mActionbarTitle.setText("技师评价");

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btn_actionbar_back_img:
                    finish();
                    break;
                case R.id.btn_feedback_commit:
                    String text = mEdtText.getText().toString().trim();
                    int score1 = ratingBar1.getNumStars();
                    int score2 = ratingBar2.getNumStars();
                    int score3 = ratingBar3.getNumStars();
                    int score4 = ratingBar4.getNumStars();
//                    UserBean userBean = new UserBean(phone, text);
//                    userBean.feedBackText = text;
//                    userBean.feedback(callback);
                    break;
            }
        }
    };
    HttpCallback callback = new HttpCallback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(String bean) {
            ToastUtil.showToastShort("提交成功");
            finish();

        }
    };

    @Override
    public void initData() {

    }
}
