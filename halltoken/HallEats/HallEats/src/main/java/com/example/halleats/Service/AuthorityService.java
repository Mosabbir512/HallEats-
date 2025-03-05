package com.example.halleats.Service;

import com.example.halleats.Database.Tokenrepo;
import com.example.halleats.Model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityService {


    @Autowired
    Tokenrepo trepo;
    public Optional<Token> searchtoken(String identity,String date)
    {

        Optional<Token> token=trepo.findtoken(identity,date);

        return token;
    }
}
