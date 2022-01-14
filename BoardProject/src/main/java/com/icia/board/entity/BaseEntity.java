package com.icia.board.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {
    @CreationTimestamp
    @Column(updatable = false) //업데이트 수정 불가능하게함
    private LocalDateTime createTime; //insert 한 시간

    @UpdateTimestamp
    @Column(insertable = false) //insert할때는 들어가지않게함)
    private LocalDateTime updateTime; //update를 수행한 시간
}
