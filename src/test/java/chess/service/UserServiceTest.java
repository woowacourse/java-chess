package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.dto.user.UserRequestDto;
import chess.service.testdao.TestUserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService(new TestUserDao());
    }

    @Test
    void addAndFind() {
        final String name = "testName";
        final UserRequestDto userRequestDto = new UserRequestDto(name);

        assertThatCode(() -> userService.add(userRequestDto))
            .doesNotThrowAnyException();

        assertThat(userService.findByName(name).getName())
            .isEqualTo(name);
    }
}
