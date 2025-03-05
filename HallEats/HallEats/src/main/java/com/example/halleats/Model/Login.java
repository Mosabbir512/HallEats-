package com.example.halleats.Model;

import org.springframework.stereotype.Component;

@Component
public class Login {
    private String identity;
    private String pass;
    public Login(String identity, String pass) {
        this.identity = identity;
        this.pass = pass;
    }

    public Login() {
    }


    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Login{" +
                "identity='" + identity + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
