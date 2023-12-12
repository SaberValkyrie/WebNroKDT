package com.example.imagehacker.dao;

import com.example.imagehacker.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * UserRepository.
 *
 * @author Nguyễn Hải
 * Created 27/11/2023
 */

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {

    @Query("SELECT u FROM Account u WHERE u.username = :username AND u.password = :password")
    Account findByUsernameAndPassword(String username, String password);
}


