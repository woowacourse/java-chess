package techcourse.fp.dbstudy;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @Test
    public void connection() {
        try (final var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addUser() {
        final var user = new User("testUserId", "testUser");
        userDao.addUser(user);
    }

    @Test
    public void findByUserId() {
        final var user = userDao.findByUserId("testUserId");

        assertThat(user).isEqualTo(new User("testUserId", "testUser"));
    }

    @Test
    public void updateUserId() {
        userDao.updateId("testUserId", "test");
        final var user = userDao.findByUserId("testUserId");
        assertThat(user).isEqualTo(new User("testUserId", "test"));
    }

    @Test
    public void deleteUserId() {
        // 조회 -> 있는 지 체크 후 다음로직
        userDao.deleteId("testUserId");
        final var user = userDao.findByUserId("testUserId");
        assertThat(user).isEqualTo(null);
    }
}
