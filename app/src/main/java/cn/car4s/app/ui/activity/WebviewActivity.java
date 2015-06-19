package cn.car4s.app.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.car4s.app.R;

/**
 * Description:支持js接口调用
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/5/13.
 */
public class WebviewActivity extends BaseActivity implements IBase {

    @InjectView(R.id.tv_actionbar_title)
    TextView tvActionbarTitle;
    @InjectView(R.id.webview)
    WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.inject(this);
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

    }
}
