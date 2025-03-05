package com.example.halleats.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "register")
public class Register {

    private String name;
    @Id
    private String iid;
  private  String identity2;
  private String pass2;

    public Register() {
    }

    public Register(String name, String iid, String identity2, String pass2) {
        this.name = name;
        this.iid = iid;
        this.identity2 = identity2;
        this.pass2 = pass2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getIdentity2() {
        return identity2;
    }

    public void setIdentity2(String identity2) {
        this.identity2 = identity2;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    @Override
    public String toString() {
        return "Register{" +
                "name='" + name + '\'' +
                ", iid='" + iid + '\'' +
                ", identity2='" + identity2 + '\'' +
                ", pass2='" + pass2 + '\'' +
                '}';
    }


}
