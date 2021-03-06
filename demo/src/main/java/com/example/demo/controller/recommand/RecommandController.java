package com.example.demo.controller.recommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.equipment.Equipment;

/*
 * 네이버 쇼핑 API를 이용한 장비 검색 및 견적 추천 컨트롤러
 */
@CrossOrigin(value = {"*"})
@RestController
@RequestMapping("/recommand")
public class RecommandController {
	
	
	@GetMapping
	public List<Equipment> getResponse() {
		String clientId = "SDaatdF9ZtdfFAFFOTRj";
		String clientSecret = "Zo6n1UiPhV";
		
		String text = null;
		try {
			text = URLEncoder.encode("조립pc", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}
		 
		String apiURL = "https://openapi.naver.com/v1/search/shop.json?query=" + text;
		
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL, requestHeaders);
		
		JSONParser parser = new JSONParser(); // 문자열 데이터를 JSON 데이터로 파싱해주는 JSON Parser 객체
		JSONObject obj = null; 
		JSONArray items = null; 
		
		try {
			obj = (JSONObject) parser.parse(responseBody); // 문자열 데이터를 JSON 데이터로 변환
			items = (JSONArray) obj.get("items"); // JSON 객체에서 items 가져오기
		} catch (ParseException e) {
			throw new RuntimeException("Json 변환 실패", e);
		}
		
		List<Equipment> list = new ArrayList<>();
		
		for(int i = 0; i < items.size(); i++) {
			JSONObject item = (JSONObject) items.get(i);
			
			Equipment e = Equipment.builder()
					.name((String) item.get("title"))
					.category((String) item.get("category2"))
					.price((String) item.get("lprice"))
					.maker((String) item.get("maker"))
					.brand((String) item.get("brand"))
					.build();
			
			list.add(e);
		}
		
		return list;
	}
	
	
	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		
		try {
			con.setRequestMethod("GET");
			
			for(Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}
			
			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				return readBody(con.getInputStream());
			} else {
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}
	
	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패하였습니다. : " + apiUrl, e);
		}
	}
	
	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);
		
		
		try(BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();
			
			String line;
			while((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}
			
			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}
}
