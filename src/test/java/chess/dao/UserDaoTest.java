package chess.dao;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

}
