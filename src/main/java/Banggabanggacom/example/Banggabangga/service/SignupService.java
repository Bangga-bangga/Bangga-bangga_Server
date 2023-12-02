package Banggabanggacom.example.Banggabangga.service;

import Banggabanggacom.example.Banggabangga.domain.User;
import Banggabanggacom.example.Banggabangga.dto.UserSignupDto;
import Banggabanggacom.example.Banggabangga.exception.ErrorCode;
import Banggabanggacom.example.Banggabangga.exception.User.SignupException;
import Banggabanggacom.example.Banggabangga.repsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;


    public User signup(UserSignupDto request) {
        checkDuplicateNickName(request.getNickname());
        User user = creatUser(request);
        return userRepository.save(user);
    }

    public void checkDuplicateNickName(String nickName) {
        Optional<User> user = Optional.ofNullable(userRepository.findByNickname(nickName));
        if (!user.isEmpty()) {
            throw new SignupException(ErrorCode.DUPLICATED_EMAIL);
        }
    }

    private User creatUser(UserSignupDto request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .age(request.getAge())
                .createAt(LocalDateTime.now())
                .role("ROLE_USER")
                .status("A").build();
        return user;
    }
}
