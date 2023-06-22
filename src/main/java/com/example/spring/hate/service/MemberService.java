package com.example.spring.hate.service;

import com.example.spring.hate.dto.MemberDto;
import com.example.spring.hate.dto.Tdto;
import com.example.spring.hate.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class MemberService {
   private final MemberRepository memberRepository;
   @Autowired
   public MemberService(MemberRepository memberRepository) {
        this.memberRepository=memberRepository;
    }

    public void membersave(MemberDto memberDto) {
       memberRepository.save(memberDto);
    }

    public ArrayList<MemberDto> memberList() {
    ArrayList<MemberDto> arrayList = (ArrayList<MemberDto>) memberRepository.memberList();
    return arrayList;
    }

    public int updateForm() {
    memberRepository.updateForm();
    int a =5;
   return 1;
   }

    public void file(Tdto tdto, MultipartFile file) {
    String path="C:\\test\\img";
    String uuid= UUID.randomUUID().toString();
    String saveName=file.getOriginalFilename();
    System.out.println(path);
    System.out.println(saveName);
    File f = new File(path,saveName);
        try {
            file.transferTo(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
