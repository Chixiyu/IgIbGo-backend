package com.igibgo.igibgo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
public class FtpUtil {
    @Value("${ftp.username}")
    private static String ftpUsername;

    @Value("${ftp.password}")
    private static String password;

    /**
     * util class for ftp upload
     * @param fileDir file directory url for remote FTP server
     * @param file actual file
     */
    public static void FtpUpload(String fileDir, MultipartFile file) {
        try {
            FTPClient ftp = new FTPClient();
            // remote
//            ftp.connect("172.18.0.2", 21);
            // local
            ftp.connect("igibgo.cc", 21);
            ftp.login(ftpUsername, password);
            System.out.println("reply code: "+ftp.getReplyCode());
            if(!FTPReply.isPositiveCompletion(ftp.getReplyCode())){
                log.error("FTP login failed");
            }
            ftp.changeWorkingDirectory(fileDir);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            BufferedInputStream inputStream = new BufferedInputStream(file.getInputStream());
            ftp.storeFile(file.getOriginalFilename(), inputStream);
            inputStream.close();
            ftp.disconnect();
        } catch (Exception e) {
            log.error("Error happened in file cover upload: " + e.getMessage());
        }
    }

}
