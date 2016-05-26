package uilts;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quantong.mobilefix.BaseApplication;

/**
 * Created by Constantine on 2016/4/20.
 */
public class ImageManager {

    public static void loadImage(String imageURL, ImageView imageView) {
        ImageLoader.getInstance().displayImage(imageURL, imageView, BaseApplication.displayImageOptions);
    }

    public static void destory() {
        if (ImageLoader.getInstance() != null) {
            ImageLoader.getInstance().destroy();
        }
    }
}
