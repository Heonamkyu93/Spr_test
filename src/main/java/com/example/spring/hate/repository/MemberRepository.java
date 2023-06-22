package com.example.spring.hate.repository;

import com.example.spring.hate.dto.MemberDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MemberRepository {
    int save(MemberDto memberDto);


    List<MemberDto> memberList();


    int updateForm();
}
