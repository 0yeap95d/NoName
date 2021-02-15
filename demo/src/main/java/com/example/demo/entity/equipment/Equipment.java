package com.example.demo.entity.equipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipment {
	
	// 장비 이름
	String name;
	
	// 장비 분류
	String category;
	
	// 장비 제조사
	String maker;
	
	// 장비 브랜드
	String brand;
	
	// 장비 가격
	String price;
	
}
