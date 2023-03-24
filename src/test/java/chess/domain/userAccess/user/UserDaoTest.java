package chess.domain.userAccess.user;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static chess.domain.userAccess.DbConnection.getConnection;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @Test
    @DisplayName("1. 커넥션 테스트")
    public void connection() {
        try (final Connection connection = getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
        }
    }

    @Test
    @DisplayName("2. 유저 삽입 테스트")
    public void createUser() {
        User user = new User("testUserId");
        Assertions.assertDoesNotThrow(() -> userDao.createUser(user));
    }

    @Test
    @DisplayName("3. 유저 검색 테스트")
    public void findByUserId() {
        Optional<User> user = userDao.findUserById("testUserId");
        assertThat(user.isEmpty()).isFalse();
        assertThat(user.get()).isEqualTo(new User("testUserId"));
    }

    @Test
    @DisplayName("4. 테스트를 통한 삽입 데이터 삭제")
    public void deleteTestData() {
        String userId = "testUserId";
        String query = "DELETE FROM user WHERE user_id = ?";
        try (final Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
        }
    }
}
