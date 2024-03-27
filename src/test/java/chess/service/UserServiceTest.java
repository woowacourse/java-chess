package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.repository.TestUserDao;
import chess.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("사용자 로직")
class UserServiceTest {

    UserRepository userRepository;

    UserService userService;

    @BeforeEach
    void setup() {
        userRepository = new TestUserDao();
        userRepository.deleteAll();

        userService = new UserService(userRepository);
    }

    @DisplayName("회원 가입에 성공하다")
    @Test
    void signup() {
        //given
        String userName = "solar";

        //when
        userService.signup(userName);

        //then
        assertAll(
                () -> assertThat(userRepository.findByName(userName).isPresent()).isTrue(),
                () -> assertThat(userRepository.findByName(userName).get().getName()).isEqualTo(userName)
        );
    }
}
