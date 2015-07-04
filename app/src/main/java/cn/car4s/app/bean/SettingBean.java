package cn.car4s.app.bean;

import cn.car4s.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/4/22.
 */
public class SettingBean extends BaseBean {

    public int resourseLeft, resouseRight;
    public String title, desc;

    public SettingBean(int resourseLeft, int resouseRight, String title, String desc) {
        this.resourseLeft = resourseLeft;
        this.resouseRight = resouseRight;
        this.title = title;
        this.desc = desc;
    }

    static List<SettingBean> listData = null;

    public static List<SettingBean> createSettingData(UserBean mUserbean) {
        listData = null;
        if (listData == null) {
            listData = new ArrayList<SettingBean>();
            SettingBean bean = new SettingBean(R.mipmap.setting_myjifen, 0, "可用积分", mUserbean.AvailablePoint);
            SettingBean bean2 = new SettingBean(R.mipmap.setting_myjifen, 0, "冻结积分", mUserbean.FreezingPoint);
            SettingBean bean3 = new SettingBean(R.mipmap.setting_feedback, R.mipmap.setting_goto, "意见反馈", "");
            SettingBean bean4 = new SettingBean(R.mipmap.setting_aboutus, R.mipmap.setting_goto, "关于我们", "");
            listData.add(bean);
            listData.add(bean2);
            listData.add(bean3);
            listData.add(bean4);
        }
        return listData;
    }

    public static List<SettingBean> createSettingDataDetial() {
        listData = null;
        if (listData == null) {
            listData = new ArrayList<SettingBean>();
            SettingBean bean = new SettingBean(R.mipmap.ic_detial_shijian, R.mipmap.setting_goto, "快保网店", "");
            SettingBean bean2 = new SettingBean(R.mipmap.ic_detial_shijian, R.mipmap.setting_goto, "服务时间", "");
            SettingBean bean3 = new SettingBean(R.mipmap.ic_detial_jishi, R.mipmap.setting_goto, "技师", "");
            listData.add(bean);
            listData.add(bean2);
            listData.add(bean3);
        }
        return listData;
    }

}
