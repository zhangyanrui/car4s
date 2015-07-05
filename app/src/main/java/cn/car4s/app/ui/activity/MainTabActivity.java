package cn.car4s.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.widget.RadioGroup;
import cn.car4s.app.AppConfig;
import cn.car4s.app.R;
import cn.car4s.app.bean.UserBean;
import cn.car4s.app.ui.fragment.Tab1Fragment;
import cn.car4s.app.ui.fragment.Tab2Fragment;
import cn.car4s.app.ui.fragment.Tab3Fragment;

public class MainTabActivity extends BaseActivity implements IBase {
    private FragmentManager fm;
    private FragmentTabHost tabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    @Override
    public void initUI() {
        fm = getSupportFragmentManager();
        tabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabhost.setup(MainTabActivity.this, fm, android.R.id.tabcontent);

        tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("1"), Tab1Fragment.class, null);
        tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("2"), Tab2Fragment.class, null);
        tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator("3"), Tab3Fragment.class, null);
//        tabhost.addTab(tabhost.newTabSpec("tab4").setIndicator("4"), Tab4Fragment.class, null);
        radioGroup = (RadioGroup) findViewById(R.id.rap_tab_radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rdo_tab_1:
                        tabhost.setCurrentTabByTag("tab1");
                        break;
                    case R.id.rdo_tab_2:
                        if (UserBean.checkUserLoginStatus())
                            tabhost.setCurrentTabByTag("tab2");
                        else {
                            UserBean.toLogin(MainTabActivity.this, AppConfig.REQUEST_CODE_LOGIN_FROMTAB2);
                            change();
                        }
                        break;
                    case R.id.rdo_tab_3:
                        if (UserBean.checkUserLoginStatus())
                            tabhost.setCurrentTabByTag("tab3");
                        else {
                            UserBean.toLogin(MainTabActivity.this, AppConfig.REQUEST_CODE_LOGIN_FROMTAB3);
                            change();
                        }
                        break;
                    case R.id.rdo_tab_4:
                        tabhost.setCurrentTabByTag("tab4");
                        break;
                }
            }
        });
    }

    RadioGroup radioGroup;

    public void change() {
        radioGroup.check(R.id.rdo_tab_1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConfig.REQUEST_CODE_LOGIN_FROMTAB2 && resultCode == Activity.RESULT_OK) {
            tabhost.setCurrentTabByTag("tab2");
            radioGroup.check(R.id.rdo_tab_2);
        } else if (requestCode == AppConfig.REQUEST_CODE_LOGIN_FROMTAB3 && resultCode == Activity.RESULT_OK) {
            tabhost.setCurrentTabByTag("tab3");
            radioGroup.check(R.id.rdo_tab_3);
        }
    }

    @Override
    public void initData() {

    }
}
