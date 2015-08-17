package cn.car4s.app.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.car4s.app.AppConfig;
import cn.car4s.app.AppContext;
import cn.car4s.app.R;
import cn.car4s.app.bean.UserBean;
import cn.car4s.app.bean.WebviewBean;
import cn.car4s.app.ui.activity.WebviewActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class IvsPagerAdapter extends PagerAdapter {
    List<Object> mImgList;
    LayoutInflater mInflater;
    int mType;// 网络图片封装的bean和本地图片不同。网络图片无删除，本地可以删除。

    Context context;

    public IvsPagerAdapter(List<Object> mImgList, Context context, int mtype, myin in) {
        super();
        this.context = context;
        this.mImgList = mImgList;
        mInflater = LayoutInflater.from(context);
        mType = mtype;
        myinterface = in;
    }

    @Override
    public int getCount() {
        return mImgList.size();
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public View instantiateItem(final ViewGroup container, final int position) {
        View view = mInflater.inflate(R.layout.pager_item, container, false);
        LinearLayout pointlayout = (LinearLayout) view.findViewById(R.id.pointLayout);
        for (int i = 0; i < mImgList.size(); i++) {
            ImageView imageView = new ImageView(context);
//            imageView.setImageResource(R.drawable.whitelunbo);
            pointlayout.addView(imageView);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        Object object = mImgList.get(position);
        if (object instanceof UserBean.BannerBean) {
            final UserBean.BannerBean bean = (UserBean.BannerBean) object;
            ImageLoader.getInstance().displayImage(bean.ImgPath, imageView, AppContext.display_imageloader);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bean != null && !TextUtils.isEmpty(bean.LinkURL)) {
                        Intent mIntent = new Intent(context, WebviewActivity.class);
                        WebviewBean bean2 = new WebviewBean("", "" + bean.LinkURL, false);
                        mIntent.putExtra(AppConfig.INTENT_PARA_KEY_BEAN, bean2);
                        context.startActivity(mIntent);
                    }
                }
            });
        }
        container.addView(view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    myin myinterface;

    public interface myin {

        public void delete(int position);
    }
}
