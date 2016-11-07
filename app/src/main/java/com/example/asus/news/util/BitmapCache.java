package com.example.asus.news.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class BitmapCache implements ImageLoader.ImageCache {
    private Context context;
    private LruCache<String,Bitmap> lruCache;
    private int destWidth;
    private int destHeight;
    public BitmapCache(Context context) {
        this.context = context;
        destWidth=  context.getResources().getDisplayMetrics().widthPixels;
        destHeight=context.getResources().getDisplayMetrics().heightPixels/2;
        long maxMemory = Runtime.getRuntime().maxMemory();
        long maxSize = maxMemory / 8;
        lruCache=new LruCache<String, Bitmap>((int) maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {

                return value.getByteCount();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        Bitmap bitmap = lruCache.get(getNameByUrl(url));
        if (bitmap == null) {
            bitmap = getBitmapFromSDCard(getNameByUrl(url));
            if (bitmap != null) {
                putBitmap(url, bitmap);
            }
        }

        return bitmap;
    }

    private Bitmap getBitmapFromSDCard(String url) {
        String path = new File(context.getExternalCacheDir(), url).getAbsolutePath();
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置只加载图像的边界到内存中
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        //获取原图的宽度
        int outWidth = options.outWidth;
        //获取原图的高度
        int outHeight = options.outHeight;
      //  Log.d("zan", "btnClick: width:" + outWidth + ";height:" + outHeight);
        //缩放比例，该参数的值必须为2的n次幂
        int simpleSize = 1;
        while (outWidth / simpleSize > destWidth || outHeight / simpleSize > destHeight) {
            simpleSize *= 2;
        }
        /*****************************二次采样开始*******************************/
        //设置不仅只加载图片边界
        options.inJustDecodeBounds = false;
        //设置缩略图缩放比例
        options.inSampleSize = simpleSize;
        //设置图像的色彩模式,四种取值：
        //Bitmap.Config.ALPHA_8 加载只有透明度的图片，在这种色彩模式下，一个像素点占一个字节，图片所占内存大小：宽*高*1
        //Bitmap.Config.ARGB_4444   这种色彩模式下，透明度、红、绿、蓝各占4位，一个像素点占2个字节，图片所占内存大小：宽*高*2
        //Bitmap.Config.ARGB_8888（默认）   这种色彩模式下，透明度、红、绿、蓝各占8位，一个像素点占4个字节，图片所占内存大小：宽*高*4
        //Bitmap.Config.RGB_565 这种色彩模式下，红、绿、蓝各占5、6、5位，一个像素点占2个字节，图片所占内存大小：宽*高*2
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);

        return bitmap;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(getNameByUrl(url),bitmap);
        Bitmap bitmapFromSDCard = getBitmapFromSDCard(getNameByUrl(url));
        if (bitmapFromSDCard == null) {
            saveBitmap2SDCard(getNameByUrl(url), bitmap);
        }
    }

    private void saveBitmap2SDCard(String url, Bitmap bitmap) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(context.getExternalCacheDir(), url));
            if (url.endsWith(".png")||url.endsWith(".PNG")){
                bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            }else{
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getNameByUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1, url.length());
    }
}
