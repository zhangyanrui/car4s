package cn.car4s.app.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.widget.RadioGroup;
import cn.car4s.app.R;
import cn.car4s.app.ui.fragment.Tab1Fragment;
import cn.car4s.app.ui.fragment.Tab2Fragment;
import cn.car4s.app.ui.fragment.Tab3Fragment;
import cn.car4s.app.ui.fragment.Tab4Fragment;

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
        tabhost.addTab(tabhost.newTabSpec("tab4").setIndicator("4"), Tab4Fragment.class, null);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rap_tab_radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rdo_tab_1:
                        tabhost.setCurrentTabByTag("tab1");
                        break;
                    case R.id.rdo_tab_2:
                        tabhost.setCurrentTabByTag("tab2");
                        break;
                    case R.id.rdo_tab_3:
                        tabhost.setCurrentTabByTag("tab3");
                        break;
                    case R.id.rdo_tab_4:
                        tabhost.setCurrentTabByTag("tab4");
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {

    }
}
