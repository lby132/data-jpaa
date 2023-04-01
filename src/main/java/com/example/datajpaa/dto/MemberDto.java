package com.example.datajpaa.dto;

import com.example.datajpaa.entity.Member;
import com.example.datajpaa.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String username;
    private int age;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }

    public MemberDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
    }

}
