package com.example.datajpaa.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AutoCloseable.class)
public class Item implements Persistable<Long> {

    // @GeneratedValue 를 쓰지 않으면 아이디값이 null이라 merge를 하게 되는데 그걸 방지하기 위해서 아이디 값을 생성(?)해주는
    // 어쨋든 엔티티가 널이 아닌걸 만들어서 merge가 아니라 persist를 하기위한 로직
    // 머지하면 DB에서 값이 있는지 없는지 먼저 판단하기 때문에 select쿼리가 한번 나가게 되는 단점이 있기 때문에 persist를 호출해야한다.
    @Id
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;

    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return createdDate == null;
    }
}
