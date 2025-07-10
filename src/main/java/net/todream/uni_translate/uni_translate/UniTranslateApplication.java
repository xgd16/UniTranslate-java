package net.todream.uni_translate.uni_translate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.todream.uni_translate.uni_translate.initializer.LanguageFileInitializer;

@SpringBootApplication
@MapperScan("net.todream.uni_translate.uni_translate.mapper")
public class UniTranslateApplication {

	public static void main(String[] args) {
		LanguageFileInitializer.ensureJsonFileExists();
		SpringApplication.run(UniTranslateApplication.class, args);
	}

}
