package com.chiron.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//생성될 수 있는 위치를 조정하는 어노테이션
//PRAMETER로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있습니다. 이 외에 TYPE등이 있다
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
//@interface는 이 파일을 어노테이션 클래스로 지정 LoginUser 어노테이션 생성
public @interface LoginUser {
}
