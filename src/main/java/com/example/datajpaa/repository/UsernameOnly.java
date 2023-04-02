package com.example.datajpaa.repository;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Id;

public interface UsernameOnly {

    @Value("#{target.username + ' ' + target.age}") // open projection. 여러개를 가져오는 방법(일단 엔티티를 다가져와서 셀렉트절에서 필요한건만 찍음)
    String getUsername(); // close projection 하나만 가져와서 조회하는 방법

//    int getAge();
}
