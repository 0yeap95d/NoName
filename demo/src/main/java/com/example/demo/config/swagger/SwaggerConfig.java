package com.example.demo.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class SwaggerConfig {
	
	/*
	 * Swagger API 문서 생성
	 */
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo())
        		
        		/*
        		 *  API 설정 (select() - build() 의 쌍으로 이루어짐)
        		 */
        		.select()
        		// API 요청 주소를 정의한 클래스가 있는 패키지 정의
        		.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
        		// API 요청 주소 중 Swagger에 나타낼 주소 정의
        		.paths(PathSelectors.any())
        		.build();
    }
    
    /*
     * Swagger 정보
     */
    private ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
    			.title("NoName") // API 이름
    			.description("스트리머들의 장비 정보 제공 및 컴퓨터 장비 견적 추천 서비스") // API 설명
    			.version("1.0.0") // API 버전
    			.build();
    }
}
