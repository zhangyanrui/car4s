package cn.car4s.app;

import android.app.Application;
import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.concurrent.Executor;

/**
 * Description:
 * Author: Alex
 * Email: xuebo.chang@langtaojin.com
 * Time: 2015/4/22.
 */
public class AppContext extends Application {
    public static DisplayImageOptions display_imageloader;

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    public void initImageLoader() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(this, AppConfig.DIR_IMAGELOADER_CACHE); // SD������·��
        UnlimitedDiscCache AppDiskCache = new UnlimitedDiscCache(cacheDir);
        Executor executor = DefaultConfigurationFactory.createExecutor(3, 3, QueueProcessingType.FIFO);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).threadPriority(Thread.MAX_PRIORITY - 2)
                .taskExecutorForCachedImages(executor).denyCacheImageMultipleSizesInMemory().diskCache(AppDiskCache)
                .diskCacheSize(20 * 1024 * 1024).diskCacheFileNameGenerator(new Md5FileNameGenerator()).build();
        ImageLoader.getInstance().init(config);

        display_imageloader = new DisplayImageOptions.Builder()
                .showImageOnLoading(android.R.color.transparent)
                .showImageForEmptyUri(android.R.color.transparent)
                .showImageOnFail(android.R.color.transparent)
                .cacheInMemory(true).cacheOnDisk(true).bitmapConfig(Bitmap.Config.ARGB_8888)
                .considerExifParams(true).build();
    }
}
