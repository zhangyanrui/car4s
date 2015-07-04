package cn.car4s.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.car4s.app.AppContext;
import cn.car4s.app.R;
import cn.car4s.app.bean.CarSerisBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * User: alex
 * Time: 2014/8/5     10:45
 * Email: xuebo.chang@langtaojin.com
 * Msg:
 */
public class CarSersisAdapter extends BaseAdapter {

    List<CarSerisBean> list;
    Context context;

    public CarSersisAdapter(List<CarSerisBean> list, Context context) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder;
        if (view == null) {
            viewholder = new Viewholder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_item_car_seris, null);
            viewholder.tv_item_pinendshowcartype_name = (TextView) view.findViewById(R.id.tv_item_pinendshowcartype_name);
            viewholder.tv_item_pinedshowcartype_title = (TextView) view.findViewById(R.id.tv_item_pinedshowcartype_title);
            viewholder.layout_all = (LinearLayout) view.findViewById(R.id.layout_all);
            viewholder.carimg = (ImageView) view.findViewById(R.id.img_car);
            view.setTag(viewholder);
        } else {
            viewholder = (Viewholder) view.getTag();
        }

        CarSerisBean bean = (CarSerisBean) list.get(i);
        viewholder.tv_item_pinendshowcartype_name.setText(bean.SeriesName);
        ImageLoader.getInstance().displayImage(bean.ImgPath, viewholder.carimg, AppContext.display_round_imageloader);


//        if (bean.isChecked) {
//            viewholder.layout_all.setBackgroundColor(context.getResources().getColor(R.color.color_bg_gray));
//        } else {
//            viewholder.layout_all.setBackgroundColor(context.getResources().getColor(R.color.color_white));
//        }
        return view;
    }


    static class Viewholder {
        TextView tv_item_pinendshowcartype_name;
        TextView tv_item_pinedshowcartype_title;
        ImageView carimg;
        LinearLayout layout_all;
    }
}
