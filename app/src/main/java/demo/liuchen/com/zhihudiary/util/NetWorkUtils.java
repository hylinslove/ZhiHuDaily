package demo.liuchen.com.zhihudiary.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by meng on 2016/11/04.
 */
public class NetWorkUtils {
    /**
     * 判断当前网络是否已连接
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        boolean result;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        result = netInfo != null && netInfo.isConnected();
        return result;
    }
}
