package chess.dao;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.Controller.dto.UserDto;
import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("UserDao 는")
class UserDaoTest {

    @Test
    @DisplayName("디비에 연결되야 한다.")
    void connection() {
        final UserDao userDao = new UserDao();
        final Connection connection = userDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    @DisplayName("유저를 생성해야 한다.")
    void create_User_Record() {
        final UserDao userDao = new UserDao();
        assertThatNoException().isThrownBy(
                () -> userDao.createUser("test2", 2)
        );
    }

    @Test
    @DisplayName("유저를 가져올 수 있어야 한다.")
    void get_User() {
        final UserDao userDao = new UserDao();
        final UserDto userDto = userDao.getUser("test");
        assertThat(userDto.getId()).isEqualTo(1);
    }

}
