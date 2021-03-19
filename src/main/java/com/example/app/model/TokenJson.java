package com.example.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenJson {
    @JsonProperty("exp")
    private String exp;

    @JsonProperty("iat")
    private Integer iat;

    @JsonProperty("jti")
    private Long jti;

    @JsonProperty("iss")
    private String iss;

    @JsonProperty("aud")
    private String aud;

    @JsonProperty("sub")
    private String sub;

    @JsonProperty("typ")
    private String typ;

    @JsonProperty("azp")
    private String azp;

    @JsonProperty("session_state")
    private String session_state;

    @JsonProperty("scope")
    private String scope;

}
