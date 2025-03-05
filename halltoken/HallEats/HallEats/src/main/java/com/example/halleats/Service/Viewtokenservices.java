package com.example.halleats.Service;


import com.example.halleats.Database.Tokenrepo;
import com.example.halleats.Model.Token;
import com.example.halleats.Model.Tokenlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Viewtokenservices {

    @Autowired
    Token token;
    @Autowired
    Tokenlist tokenlist;
    @Autowired
    Tokenrepo tokenrepo;


//    public Optional<List<Token>> searchmytokens(String identity2) {
//
//       List<Token> tlist=tokenrepo.find
//
//
//    }
}
