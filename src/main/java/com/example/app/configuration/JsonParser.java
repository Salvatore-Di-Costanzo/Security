package com.example.app.configuration;

import com.example.app.model.TokenJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.keycloak.common.util.Base64;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.persistence.Column;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Component
public class JsonParser {


    public final static String client_id = "app";
    public final static String grant_type = "password";

    public String tokenReader(String username, String password) throws UnirestException, IOException {

        String token = Unirest.post("http://localhost:8081/auth/realms/master/protocol/openid-connect/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Cookie", "AUTH_SESSION_ID_LEGACY=591bdfe1-7608-47e9-b972-b6892652bd2d.ee22231a6597; JSESSIONID=5FAC7557E963A3B9DF2FEFAD9F7F7C9A.ee22231a6597; JSESSIONID=5FAC7557E963A3B9DF2FEFAD9F7F7C9A; OAuth_Token_Request_State=507c22d9-8329-4091-85e8-305176573940")
                .field("username", username)
                .field("password", password)
                .field("client_id", client_id)
                .field("grant_type", grant_type)
                .asJson()
                .getBody().getObject().getString("refresh_token");
        String[] splitToken = token.split("\\.");

        byte[] decodedBytes = Base64.decode(splitToken[1], Base64.URL_SAFE);

        String payload = new String(decodedBytes, StandardCharsets.UTF_8);

        Gson gson = new Gson();

        TokenJson tokenJson = gson.fromJson(payload, TokenJson.class);

        return tokenJson.getSub();
    }
}
