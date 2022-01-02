package com.example.simple.library.utils;

public class Constants {

    public static final String AUTHORITIES_KEY = "AUTH_KEY";
    public static final String SIGNING_KEY = "SIGNING_KEY";
    public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 8 * 60 * 60;

    public static final int PASSWORD_ENCODER_STRENGTH = 12;

    public static final String ROLE_USER = "ROLE_USER";

    public static final int BORROWED_LIMIT = 5;

    public static final String KAFKA_EMAIL_SEND_TOPIC = "BORROWED_EMAIL_SEND";
}
