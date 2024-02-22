package com.htm.android_3d.engine.util.android;

import com.htm.android_3d.engine.util.android.assets.Handler;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class AndroidURLStreamHandlerFactory implements URLStreamHandlerFactory {

    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if ("assets".equals(protocol)) {
            return new com.htm.android_3d.engine.util.android.assets.Handler();
        } else if ("content".equals(protocol)){
            return new com.htm.android_3d.engine.util.android.content.Handler();
        }
        return null;
    }
}
