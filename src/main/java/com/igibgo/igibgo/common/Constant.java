package com.igibgo.igibgo.common;

import org.springframework.beans.factory.annotation.Value;

public class Constant {
    @Value("${video.upload.dir}")
    public static String VIDEO_UPLOAD_DIR;
}
