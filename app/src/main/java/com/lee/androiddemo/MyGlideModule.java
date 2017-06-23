package com.lee.androiddemo;


import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

import java.io.File;

/**
 * Created by android on 2017/6/23.
 */

@GlideModule
public class MyGlideModule extends AppGlideModule {

    /**
     * 通过GlideBuilder设置默认的结构(Engine,BitmapPool ,ArrayPool,MemoryCache等等).
     *
     * @param context
     * @param builder
     */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/Glide";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        //重新设置内存限制
        builder.setMemoryCache(new LruResourceCache(10 * 1024 * 1024));
        //  super.applyOptions(context, builder);

        //  builder.setDiskCache(new InternalCacheDiskCacheFactory(context, 100 * 1024 * 1024));
      //  builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, filePath, 100 * 1024 * 1024));

        //设置缓存目录和 大小
        builder.setDiskCache(new DiskLruCacheFactory(filePath, 100 * 1024 * 1024));
    }

    /**
     * 为App注册一个自定义的String类型的BaseGlideUrlLoader
     *
     * @param context
     * @param registry
     */
    @Override
    public void registerComponents(Context context, Registry registry) {
        super.registerComponents(context, registry);
    }


    @Override
    public boolean isManifestParsingEnabled() {
        return super.isManifestParsingEnabled();
    }
}
