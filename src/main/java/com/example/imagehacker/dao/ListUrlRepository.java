
package com.example.imagehacker.dao;

        import com.example.imagehacker.entity.Account;
        import com.example.imagehacker.entity.AccountUrl;
        import com.example.imagehacker.entity.ListUrl;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

        import java.util.List;

/**
 * UserRepository.
 *
 * @author Nguyễn Hải
 * Created 27/11/2023
 */

@Repository
public interface ListUrlRepository extends JpaRepository<ListUrl, Long> {

    @Query("SELECT u FROM ListUrl u where u.khadung = :khadung order by u.time desc")
    List<ListUrl> getallLink(byte khadung);
}


