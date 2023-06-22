package com.example.spring.hate.repository;

import com.example.spring.hate.dto.MemberDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DbMemberRepository implements MemberRepository {


    private final JdbcTemplate jdbcTemplate;

    public DbMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(MemberDto memberDto) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("test");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id",memberDto.getId());
        parameters.put("name", memberDto.getName());
        parameters.put("age", memberDto.getAge());
        parameters.put("pwd", memberDto.getPwd());
        int id = simpleJdbcInsert.execute(parameters) ;
        return id;

    }

   @Override
    public List<MemberDto> memberList() {
        return jdbcTemplate.query("select * from test",memberRowMapper());
    }

    @Override
    public int updateForm() {
     int re= jdbcTemplate.update("update test set pwd=?,name=? where age=? ","john","cena","1");
        return re;
    }


    private RowMapper<MemberDto> memberRowMapper() {
        return (rs, rowNum) -> {
            MemberDto memberDto = new MemberDto();
            memberDto.setId(rs.getString("id"));
            memberDto.setName(rs.getString("name"));
            memberDto.setAge(rs.getInt("age"));
            memberDto.setPwd(rs.getString("pwd"));
            return memberDto;
        };
    }

}
