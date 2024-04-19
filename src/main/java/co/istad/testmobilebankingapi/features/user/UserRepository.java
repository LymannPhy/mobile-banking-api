package co.istad.testmobilebankingapi.features.user;

import co.istad.testmobilebankingapi.domain.User;
import co.istad.testmobilebankingapi.features.user.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByPhoneNumber(String phoneNumber);
    Boolean existsByNationalCardId(String nationalCardId);
    Boolean existsByStudentIdCard(String studentIdCard);
//    Optional<User> findByUuid(String uuid);
//    @Query(value = "SELECT * FROM users WHERE uuid= ?1", nativeQuery = true)
    @Query("SELECT u FROM User AS u WHERE u.uuid = :uuid")
    Optional<User> findByUuid(String uuid);

    boolean existsByUuid(String uuid);

    @Modifying
    @Query("UPDATE User As u SET u.isBlocked = TRUE WHERE u.uuid = ?1")
    void blockByUuid(String uuid);

    Optional<User> findByPhoneNumber(String phoneNumber);

}
