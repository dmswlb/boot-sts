package com.shinhan.sbproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;


/*기본패키지: com.shinhan.sbproject
 * 그외의 폴더: @ComponentScan 추가
 * JpaRepository가 기본 패키지 하위에 존재하지 않는 경우 추가함: @EnableJpaRepositories
 * @EnableJpaRepositories를 사용하면 Repository가 있는 폴더는 모두 등록해야한다.
*/
@EnableAspectJAutoProxy		//@Aspect를 찾는다
@ComponentScan(basePackages = {"com.shinhan.sbproject", "com.shinhan.firstzone", "com.shinhan.firstzone2"})	//@Componet를 찾는다
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.shinhan.firstzone", "com.shinhan.sbproject"})		//@Entity를 찾는다
public class SbprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbprojectApplication.class, args);
	}

}
