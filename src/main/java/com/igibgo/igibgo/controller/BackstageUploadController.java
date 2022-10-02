package com.igibgo.igibgo.controller;

import com.igibgo.igibgo.service.BackstageUploadService;
import com.igibgo.igibgo.util.FtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@Slf4j
public class BackstageUploadController {

    private String videoPosition;
    private String videoCoverPosition;

    //  Video Section
    @PostMapping("/backstage/videos/upload")
    public void uploadVideo(@RequestParam("video") MultipartFile video) {
        FtpUtil.FtpUpload("/data/video",video);
        videoPosition="http://igibgo.cc:8084/chfs/shared/video/"+video.getOriginalFilename();
    }

    @PostMapping("/backstage/videoCovers/upload")
    public void uploadVideoCover(@RequestParam("videoCover") MultipartFile videoCover) {
        FtpUtil.FtpUpload("/data/videoCover",videoCover);
        videoCoverPosition="http://igibgo.cc:8084/chfs/shared/videoCover/"+videoCover.getOriginalFilename();
    }



    @Resource
    private BackstageUploadService uploadService;

    @PostMapping("/backstage/videos/videoInfo")
    public boolean videoInfoForm(String videoName, String videoSubtitle, String description) {
        try {
            uploadService.videoInfoForm(videoName, videoPosition, videoCoverPosition, videoSubtitle, description);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    //Note section
    private String notePosition;

    @PostMapping("/backstage/notes/upload")
    public void uploadNote(@RequestParam("noteFile") MultipartFile note) {
        FtpUtil.FtpUpload("/data/note",note);
        notePosition="/data/note/"+note.getOriginalFilename();
        log.debug("note position: "+notePosition);
    }

    @PostMapping("/backstage/notes/noteInfo")
    public boolean noteInfoForm(String noteTitle, String author, String subject) {
        try {
//            System.out.println("noteTitle: "+noteTitle+" author:"+author);
            uploadService.noteInfoForm(noteTitle, notePosition, author, subject);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
