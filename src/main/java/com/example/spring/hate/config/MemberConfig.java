package com.example.spring.hate.config;

import com.example.spring.hate.repository.DbMemberRepository;
import com.example.spring.hate.repository.MemberRepository;
import com.example.spring.hate.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MemberConfig {

   /* private final DataSource dataSource;
    public MemberConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new DbMemberRepository(dataSource);
    }*/
}
