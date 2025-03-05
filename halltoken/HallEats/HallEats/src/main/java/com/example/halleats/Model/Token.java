package com.example.halleats.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Token {

   @Id
    private String iid;
    private String name;
    private int lunch;
    private int dinner;
    private String date;
    private int cost;
    private String hallName;
    private String tid;

    public Token() {
    }

    public Token(String iid, String name, int lunch, int dinner, String date, int cost, String hallName, String tid) {
        this.iid = iid;
        this.name = name;
        this.lunch = lunch;
        this.dinner = dinner;
        this.date = date;
        this.cost = cost;
        this.hallName = hallName;
        this.tid = tid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLunch() {
        return lunch;
    }

    public void setLunch(int lunch) {
        this.lunch = lunch;
    }

    public int getDinner() {
        return dinner;
    }

    public void setDinner(int dinner) {
        this.dinner = dinner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    @Override
    public String toString() {
        return "Token{" +
                "iid='" + iid + '\'' +
                ", name='" + name + '\'' +
                ", lunch=" + lunch +
                ", dinner=" + dinner +
                ", date='" + date + '\'' +
                ", cost=" + cost +
                ", hallName='" + hallName + '\'' +
                ", tid='" + tid + '\'' +
                '}';
    }
}
