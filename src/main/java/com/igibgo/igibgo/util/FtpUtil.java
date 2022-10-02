package com.igibgo.igibgo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.InputStream;

@Slf4j
public class FtpUtil {
    /**
     * util class for ftp upload
     * @param fileDir file directory url for remote FTP server
     * @param file actual file
     */
    public static void FtpUpload(String fileDir, MultipartFile file) {
        try {
            FTPClient ftp = new FTPClient();
            ftp.connect("igibgo.cc", 21);
            ftp.login("igibgo", "Hellochixiyu07!");
            ftp.changeWorkingDirectory(fileDir);
            InputStream inputStream = new BufferedInputStream(file.getInputStream());
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.storeFile(file.getOriginalFilename(), inputStream);
            inputStream.close();
            ftp.disconnect();
        } catch (Exception e) {
            log.error("Error happened in file cover upload: " + e.getMessage());
        }
    }

}