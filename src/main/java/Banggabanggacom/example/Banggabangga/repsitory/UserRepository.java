package Banggabanggacom.example.Banggabangga.repsitory;

import Banggabanggacom.example.Banggabangga.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNickname(String nickName);
    Optional<User> findById(Long id);
    User findByEmail(String email);

    boolean existsUserByNickname(String nickname);
}
