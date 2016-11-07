package demo.liuchen.com.zhihudiary.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.util.Log;

/**
 * Created by meng on 2016/11/6.
 */

public class LruUtil {

    public static LruCache<String, Bitmap> getLruCache() {
        LruCache<String, Bitmap> lruCache;
        //获取当前系统中最大的运行时空间
        long maxMemory = Runtime.getRuntime().maxMemory();

        lruCache = new LruCache<String, Bitmap>((int) (maxMemory / 2)) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

        return lruCache;
    }


    public static Bitmap getBitmap(LruCache lruCache, String url, String filePath) {

        Bitmap bitmap = null;

        bitmap = (Bitmap) lruCache.get(url);
        if (bitmap != null) {
            Log.e("meng","lru" );
            return bitmap;
        } else {
            byte[] data = SDCardUtils.loadByteFromSDCard(filePath);
            if (data != null) {
                final Bitmap bitmap1 = BitmapFactory
                        .decodeByteArray(data, 0, data.length);
                Log.e("meng","sd" );
                return bitmap1;
            } else {
                return null;
            }
        }
    }


}
