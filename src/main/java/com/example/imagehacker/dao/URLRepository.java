
package com.example.imagehacker.dao;

        import com.example.imagehacker.entity.Account;
        import com.example.imagehacker.entity.AccountUrl;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;

/**
 * UserRepository.
 *
 * @author Nguyễn Hải
 * Created 27/11/2023
 */

@Repository
public interface URLRepository extends JpaRepository<AccountUrl, Long> {
    @Query("SELECT u FROM AccountUrl u WHERE u.account = :account")
    AccountUrl findAcc(Account account);


    @Query("SELECT u.url FROM AccountUrl u WHERE u.account.userId = :userId")
    String findLink(long userId);


    @Query("SELECT u.account.ipAddress FROM AccountUrl u WHERE u.account.userId = :userId")
    String findIP(Long userId);

    @Query("SELECT SUM(u.views) FROM AccountUrl u WHERE u.account.ipAddress = :ipAddress")
    Integer sumViewsByIpAddress(@Param("ipAddress") String ipAddress);

}


