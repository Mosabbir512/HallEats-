package com.example.halleats.Database;


import com.example.halleats.Model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Tokenrepo  extends JpaRepository<Token, String> {

    // @Query("from Register  where identity2=?1 and pass2=?2")
    @Query("from Token where iid=?1 and date=?2")
    Optional<Token> findtoken(String identity, String date);
}
