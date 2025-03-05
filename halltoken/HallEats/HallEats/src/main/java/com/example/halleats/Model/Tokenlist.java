package com.example.halleats.Model;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Tokenlist {

  private   List<Token> tokenList;

    public Tokenlist() {
    }

    public Tokenlist(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    @Override
    public String toString() {
        return "Tokenlist{" +
                "tokenList=" + tokenList +
                '}';
    }

    public void setTokenList(List<Token> tokenList) {
        this.tokenList = tokenList;
    }
}
