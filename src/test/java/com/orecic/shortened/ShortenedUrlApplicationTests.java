package com.orecic.shortened;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShortenedUrlApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String createShortUrl;
	private HttpHeaders headers;
	private JSONObject urlJsonObject;
	private final ObjectMapper objectMapper = new ObjectMapper();


	@BeforeEach
	public void init() throws JSONException {
		createShortUrl = "http://localhost:" + port + "/shortened-url/url";

		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		urlJsonObject = new JSONObject();
		urlJsonObject.put("originalUrl", "");
	}

	@Test
	void shouldCallWithSuccess() {
		var request = new HttpEntity<>(urlJsonObject.toString(), headers);

		var urlResultAsJsonStr = restTemplate.postForObject(createShortUrl, request, String.class);

		assertNotNull(urlResultAsJsonStr);


	}

}
