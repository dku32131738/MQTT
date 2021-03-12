package com.ictway.mqtt.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.net.HttpHeaders;
import com.ictway.mqtt.domain.FcmMessage;

import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
@RequiredArgsConstructor
public class FirebaseCloudMessageService {
	private final String API_URL="https://fcm.googleapis.com/v1/projects/pushmqtt-947cb/messages:send";
	//private final ObjectMapper objectMapper=null;
	private static final Logger logger = LoggerFactory.getLogger(FirebaseCloudMessageService.class);
	
	private String getAccessToken() throws IOException{
		String firebaseConfigPath="firebase/pushmqtt-947cb-firebase-adminsdk-9pux0-072ff12be3.json";
		GoogleCredentials googleCredentials=GoogleCredentials
				.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream());
		googleCredentials.refreshIfExpired();
		return googleCredentials.getAccessToken().getTokenValue();
	}
	
	public void sendMessageTo(String targetToken,String title,String body) throws IOException{
		String message=makeMessage(targetToken,title,body);
		OkHttpClient client=new OkHttpClient();
		RequestBody requestBody=RequestBody.create(message,MediaType.get("application/json; charset=utf-8"));
		Request request=new Request.Builder().url(API_URL).post(requestBody).addHeader(HttpHeaders.AUTHORIZATION, "Bearer"+getAccessToken())
				.addHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8").build();
		Response response=client.newCall(request).execute();
		logger.info(response.body().string());
	}
	
	private String makeMessage(String targetToken,String title,String body) throws JsonProcessingException {
		ObjectMapper objectMapper=new ObjectMapper();
		FcmMessage fcmMessage=FcmMessage.builder()
				.message(FcmMessage.Message.builder().token(targetToken)
						.notification(FcmMessage.Notification.builder()
								.title(title).body(body).image(null).build()
								).build()
						).validate_only(false).build();
		logger.info("예측1");
		return objectMapper.writeValueAsString(fcmMessage);
	}
}
