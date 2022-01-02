package com.example.simple.library.web.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class AppUserSaveInfo implements Serializable {

    public static final long serialVersionUID = 1L;

    @NotBlank(message = "{user.error.email.required}")
    @Email(message = "{user.error.email.format}")
    private String email;

    @NotBlank(message = "{user.error.password.required}")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
