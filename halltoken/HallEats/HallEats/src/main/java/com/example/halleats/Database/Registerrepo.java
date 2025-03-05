package com.example.halleats.Database;

import com.example.halleats.Model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Registerrepo extends JpaRepository<Register,String> {
    @Query("from Register  where identity2=?1 and pass2=?2")
    Register findpermission(String identity, String pass);


}
