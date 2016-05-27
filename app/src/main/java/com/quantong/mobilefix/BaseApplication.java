package com.quantong.mobilefix;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Constantine on 2016/4/20.
 */
public class BaseApplication extends Application {

    private final String TAG = "BaseApplication";
    private String cacheFilePath;
    public static DisplayImageOptions displayImageOptions;
    public static InputMethodManager inputMethodManager;

    @Override
    public void onCreate() {
        super.onCreate();
        //initImageLoader(mContext);
        //displayImageOptions = getDisplayImageOptions(mContext);
        //inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        //CrashHandler.getInstance().init(this);
    }

    public void initImageLoader(Context context) {
        String cachePath;
        File cacheFile;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File sdCardDir = Environment.getExternalStorageDirectory();
            cachePath = sdCardDir.getPath() + "/" + this.getResources().getString(R.string.app_name) + "/images";
            cacheFile = new File(cachePath);
            if (!cacheFile.exists()) {
                cacheFile.mkdirs();
            }
        } else {
            cachePath = this.getFilesDir().getPath() + "/images";
            cacheFile = new File(cachePath);
            if (!cacheFile.exists()) {
                cacheFile.mkdirs();
            }
        }

        cacheFilePath = cachePath;

        Log.d("BaseApplication", cacheFilePath);

        File cacheDir = StorageUtils.getOwnCacheDirectory(this, cacheFilePath);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context).memoryCacheExtraOptions(480, 800).
                threadPoolSize(3) //线程池内线程的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(50 * 1024 * 1024)  // SD卡缓存的最大值
                .tasksProcessingOrder(QueueProcessingType.LIFO)// 由原先的discCache -> diskCache
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(configuration);
    }

    private DisplayImageOptions getDisplayImageOptions(Context context) {
        displayImageOptions = new DisplayImageOptions.Builder()
                //.showImageForEmptyUri(R.drawable.iv_emptyorerror)//设置图片Uri为空或是错误的时候显示的图片
                .cacheInMemory(false)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .build();//构建完成
        return displayImageOptions;
    }

}
