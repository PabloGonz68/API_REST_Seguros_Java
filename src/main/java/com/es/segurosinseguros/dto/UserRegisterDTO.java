package com.es.segurosinseguros.dto;

public class UserRegisterDTO {
    private String username;
    private String password1;
    private String password2;
    private String rol;

    public UserRegisterDTO(String username, String password1, String password2, String rol) {
        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
        this.rol = rol;
    }

    public UserRegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password) {
        this.password1 = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password) {
        this.password2 = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
