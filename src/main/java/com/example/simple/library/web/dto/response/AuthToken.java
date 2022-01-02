package com.example.simple.library.web.dto.response;

import java.io.Serializable;
import java.util.Date;

import static com.example.simple.library.utils.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;

public class AuthToken implements Serializable {

    public static final long serialVersionUID = 1L;

    private final String token;
    private final String issuedAt;
    private final int validitySeconds;

    public AuthToken(String token) {
        this.token = token;

        this.issuedAt = new Date().toString();
        this.validitySeconds = ACCESS_TOKEN_VALIDITY_SECONDS;
    }

    public String getToken() {
        return token;
    }

    public String getIssuedAt() {
        return issuedAt;
    }

    public int getValiditySeconds() {
        return validitySeconds;
    }
}
