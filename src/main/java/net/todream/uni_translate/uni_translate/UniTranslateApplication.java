package net.todream.uni_translate.uni_translate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.todream.uni_translate.uni_translate.mapper")
public class UniTranslateApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniTranslateApplication.class, args);
	}

}
