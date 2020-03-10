package com.chiron.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity 클래스들이 상속할 경우 피드들도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class) //클래스에 Auditing 기능을 포함
public abstract class BaseTimeEntity {

    @CreatedDate //Entity가 생성되어 저장될 때 시간이 자동으로 저장
    private LocalDateTime createDate;

    @LastModifiedDate //Entity의 값을 변경할 때 시간이 자동 저장
    private LocalDateTime modifiedDate;


}
