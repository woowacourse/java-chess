package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.repository.FakeUserDao;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("사용자 로직")
class UserServiceTest {

    FakeUserDao userRepository;

    UserService userService;

    @BeforeEach
    void setup() {
        userRepository = new FakeUserDao();
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

    @DisplayName("로그인에 성공하다")
    @Test
    void login() {
        //given
        String userName = "choco";

        //when
        long loginId = userService.login(userName);

        //then
        assertThat(loginId).isEqualTo(1);
    }

    @DisplayName("모든 사용자 이름을 조회한다")
    @Test
    void findAll() {
        //given & when
        List<String> userNames = userService.findUserNames();

        //then
        assertAll(
                () -> assertThat(userNames).hasSize(2),
                () -> assertThat(userNames).containsExactly("choco", "khaki")
        );
    }
}
