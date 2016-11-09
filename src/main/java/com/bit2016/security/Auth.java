package com.bit2016.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 메소드, 파라미터, 클래스 중 메서드에 달아줌
@Retention(RetentionPolicy.RUNTIME) // 종속기간
public @interface Auth {
	String role() default "user";
	
}
