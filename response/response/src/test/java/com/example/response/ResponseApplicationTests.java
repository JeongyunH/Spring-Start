package com.example.response;

import com.example.response.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ResponseApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		System.out.println("-----------------");

		var objectMapper = new ObjectMapper();

		// object -> text
		// object mapper가 get method(함수)를 활용한다.
		var user = new User("steve", 20);
		var text = objectMapper.writeValueAsString(user);
		System.out.println(text);

		// text -> object
		// object mapper가 default 생성자를 필요로 한다.
		var objectUser = objectMapper.readValue(text, User.class);
		System.out.println(objectUser);
	}

}
