package com.example.spring.hate.controller;

import com.example.spring.hate.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class FileDownController {
    private MemberService memberService;
        @Autowired
      public FileDownController (MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping("/down")
    public void down(HttpServletResponse response) throws IOException {
        String path="C:\\test\\img";
        String file="4.txt";
        File f = new File(path,file);
        response.setContentType("application/download");
        response.setContentLength((int)f.length());
        response.setHeader("Content-disposition", "attachment;filename=\"" + file + "\"");
        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(f);
        FileCopyUtils.copy(fis, os);
        fis.close();
        os.close();
    }




}
