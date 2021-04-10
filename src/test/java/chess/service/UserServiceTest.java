package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.dao.UserDaoInterface;
import chess.dto.user.UserRequestDto;
import chess.entity.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService(new TestUserDao());
    }

    @Test
    void addAndFind() {
        final UserRequestDto userRequestDto = new UserRequestDto("testName");

        assertThatCode(() -> userService.add(userRequestDto))
            .doesNotThrowAnyException();

        assertThat(userService.find(userRequestDto).getName())
            .isEqualTo("testName");
    }
}

class TestUserDao implements UserDaoInterface {

    private final List<User> users = new ArrayList<>();

    @Override
    public void insertUser(final String name) {
        users.add(
            new User(users.size(), name, 0, 0, LocalDateTime.now())
        );
    }

    @Override
    public Optional<User> selectByName(final String name) {
        return users.stream()
            .filter(user -> Objects.equals(user.getName(), name))
            .findAny();
    }

    @Override
    public void deleteByName(final String name) {

    }
}
