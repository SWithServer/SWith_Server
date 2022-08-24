package com.example.demo.src.firebase.GroupInfoFcm;

import com.example.demo.src.firebase.model.FcmMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.apache.http.HttpHeaders;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Service
public class GroupInfoFirebaseService {


    // HTTP v1 Method
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/swith-41b09/messages:send";
    private final ObjectMapper objectMapper;

    public void sendMessageTo(String targetToken, String title, String body) throws IOException {
        String message = makeMessage(targetToken, title, body);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message,
                MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();


        Response response = client.newCall(request)
                .execute();
    /*
        System.out.println("code " + response.code() +
                "body : "+ response.body().string());*/

        /*return new FcmResponse(
                title,
                body,
                "LikeFirst_BTOS",
                "LikeFirst_BTOS");*/
    }

    private String makeMessage(String targetToken, String title, String body) throws JsonProcessingException {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(FcmMessage.Message.builder()
                        .token(targetToken)
                        .notification(FcmMessage.Notification.builder()
                                .title(title)
                                .body(body)
                                .image(null) // Android 1MB 이미지 제한 존재
                                .build()
                        )
                        .data(FcmMessage.FcmData.builder()
                                .title(title)
                                .body(body)
                                .build()
                        )
                        .build()
                )
                .validate_only(false)
                .build();

        return objectMapper.writeValueAsString(fcmMessage);
    }


    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "firebase/firebase_secret_key.json";

        List<String> scopeUrl = new ArrayList<>();
        scopeUrl.add("https://www.googleapis.com/auth/firebase.messaging");

        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(
                        new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(scopeUrl);

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }


}