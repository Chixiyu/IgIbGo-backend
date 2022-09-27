package com.igibgo.igibgo.controller;

import com.igibgo.igibgo.service.BackstageUploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;

@RestController
public class BackstageUploadController {

    private String videoPosition;
    private String videoCover;

    //  Video Section
    @PostMapping("/backstage/videos/upload")
    public boolean uploadVideo(@RequestParam("video") MultipartFile video) {
        try {
            System.out.println(video);
            String filename = video.getOriginalFilename();
            assert filename != null;
            File fileDirectory = new File("/home/asdf/Workdir/IdeaProjects/IgIbGo/web/public/dapesh/video/");
            File destFile = new File("/home/asdf/Workdir/IdeaProjects/IgIbGo/web/public/dapesh/video/" + filename);
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new FileNotFoundException("File directory not found and failed to create");
                }
            }
            videoPosition = StringUtils.substringAfterLast(destFile.getAbsolutePath(), "/public");
            video.transferTo(destFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/backstage/videoCovers/upload")
    public boolean uploadVideoCover(@RequestParam("videoCover") MultipartFile videoCover) {
        try {
            System.out.println(videoCover);
            String filename = videoCover.getOriginalFilename();
            assert filename != null;
            File fileDirectory = new File("/home/asdf/Workdir/IdeaProjects/IgIbGo/web/public/dapesh/videoCover/");
            File destFile = new File("/home/asdf/Workdir/IdeaProjects/IgIbGo/web/public/dapesh/videoCover/" + filename);
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new FileNotFoundException("File directory not found and failed to create");
                }
            }
            this.videoCover = StringUtils.substringAfterLast(destFile.getAbsolutePath(), "/public");
            videoCover.transferTo(destFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Resource
    private BackstageUploadService uploadService;

    @PostMapping("/backstage/videos/videoInfo")
    public boolean videoInfoForm(String videoName, String videoSubtitle, String description) {
        try {
            uploadService.videoInfoForm(videoName, videoPosition, videoCover, videoSubtitle, description);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Note section
    private String notePosition;

    @PostMapping("/backstage/notes/upload")
    public boolean uploadNote(@RequestParam("noteFile") MultipartFile note) {
        try {
            System.out.println(note);
            String filename = note.getOriginalFilename();
            assert filename != null;
            File fileDirectory = new File("/home/asdf/Workdir/IdeaProjects/IgIbGo/web/public/dapesh/notes/");
            File destFile = new File("/home/asdf/Workdir/IdeaProjects/IgIbGo/web/public/dapesh/notes/" + filename);
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new FileNotFoundException("File directory not found and failed to create");
                }
            }
            notePosition = StringUtils.substringAfterLast(destFile.getAbsolutePath(), "/public");
            note.transferTo(destFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/backstage/notes/noteInfo")
    public boolean noteInfoForm(String noteTitle, String author,String subject) {
        try {
//            System.out.println("noteTitle: "+noteTitle+" author:"+author);
            uploadService.noteInfoForm(noteTitle,notePosition,author,subject);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
