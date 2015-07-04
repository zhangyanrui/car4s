package cn.car4s.app.ui.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.car4s.app.R;
import cn.car4s.app.bean.StationBean;

import java.util.List;

/**
 * User: alex
 * Time: 2014/8/5     10:45
 * Email: xuebo.chang@langtaojin.com
 * Msg:
 */
public class DialogTimeAdapter extends BaseAdapter {

    List<StationBean.TimeChoose> list;
    Context context;

    public DialogTimeAdapter(List<StationBean.TimeChoose> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View allView, ViewGroup viewGroup) {
        Viewholder viewholder;
        if (allView == null) {
            viewholder = new Viewholder();
            allView = LayoutInflater.from(context).inflate(R.layout.adapter_choose_station, null);
            viewholder.imageView = (ImageView) allView.findViewById(R.id.src_right);
            viewholder.textView = (TextView) allView.findViewById(R.id.tv_title);
            viewholder.textViewdesc = (TextView) allView.findViewById(R.id.tv_desc);
            allView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) allView.getTag();
        }
        viewholder.textViewdesc.setVisibility(View.GONE);
        viewholder.textView.getLayoutParams().width = RelativeLayout.LayoutParams.MATCH_PARENT;
        viewholder.textView.setGravity(Gravity.CENTER);
        StationBean.TimeChoose bean = (StationBean.TimeChoose) list.get(i);
        viewholder.textView.setText(bean.TimeName);
        return allView;
    }


    static class Viewholder {
        public ImageView imageView;
        public TextView textView;
        public TextView textViewdesc;
    }
}
