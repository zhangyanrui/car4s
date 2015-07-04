package cn.car4s.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.car4s.app.R;
import cn.car4s.app.bean.OrderBean;

import java.util.List;

/**
 * User: alex
 * Time: 2014/8/5     10:45
 * Email: xuebo.chang@langtaojin.com
 * Msg:
 */
public class OrderAdapter extends BaseAdapter {

    List<Object> list;
    Context context;
    OrderDo orderDo;

    public OrderAdapter(List<Object> list, Context context, OrderDo orderDo) {
        this.list = list;
        this.context = context;
        this.orderDo = orderDo;
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
            view = LayoutInflater.from(context).inflate(R.layout.adapter_item_order, null);
            viewholder.title = (TextView) view.findViewById(R.id.tv_title);
            viewholder.desc1 = (TextView) view.findViewById(R.id.tv_desc1);
            viewholder.layout_all = (LinearLayout) view.findViewById(R.id.layout_all);
            viewholder.layout_zhifu = (LinearLayout) view.findViewById(R.id.layout_zhifu);
            viewholder.layout_bianji = (LinearLayout) view.findViewById(R.id.layout_bianji);
            viewholder.desc2 = (TextView) view.findViewById(R.id.tv_desc2);
            viewholder.tv_timeshengyu = (TextView) view.findViewById(R.id.tv_timeshengyu);
            viewholder.edit = (TextView) view.findViewById(R.id.edit);
            viewholder.cancel = (TextView) view.findViewById(R.id.cancel);
            viewholder.jiesuan = (TextView) view.findViewById(R.id.jiesuan);
            view.setTag(viewholder);
        } else {
            viewholder = (Viewholder) view.getTag();
        }
        Object object = list.get(i);
        OrderBean bean = (OrderBean) object;
        viewholder.layout_zhifu.setVisibility(View.GONE);
        viewholder.layout_bianji.setVisibility(View.GONE);
        if ("2".equals(bean.OrderStatus)) {
            viewholder.layout_zhifu.setVisibility(View.VISIBLE);
            viewholder.tv_timeshengyu.setVisibility(View.VISIBLE);
        } else if ("1".equals(bean.OrderStatus)) {
            viewholder.layout_bianji.setVisibility(View.VISIBLE);
            viewholder.tv_timeshengyu.setVisibility(View.GONE);
        }
        viewholder.title.setText("套餐: " + bean.ProductName);
        viewholder.desc1.setText("下单时间: " + bean.OrderTime);
        viewholder.desc2.setText("网店: " + bean.StationName);
        viewholder.edit.setTag(bean);
        viewholder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderBean bean1 = (OrderBean) view.getTag();
                orderDo.edit(bean1);
            }
        });
        viewholder.cancel.setTag(bean);
        viewholder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderBean bean1 = (OrderBean) view.getTag();
                orderDo.cancelOrder(bean1);
            }
        });
        viewholder.jiesuan.setTag(bean);
        viewholder.jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderBean bean1 = (OrderBean) view.getTag();
                orderDo.zhifu(bean1);

            }
        });

        return view;
    }

    public interface OrderDo {

        public void cancelOrder(OrderBean bean);

        public void zhifu(OrderBean bean);

        public void edit(OrderBean bean);
    }

    static class Viewholder {
        TextView title;
        TextView desc1;
        TextView desc2;
        TextView tv_timeshengyu;
        TextView edit;
        TextView cancel;
        TextView jiesuan;
        LinearLayout layout_all;
        LinearLayout layout_zhifu;
        LinearLayout layout_bianji;
    }
}