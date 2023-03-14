package techcourse.fp.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoTest {
    private final UserDao userDao = new UserDao();
    
    @Test
    void connection() {
        try (final var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    void addUser() {
        int addedUser = userDao.addUser(new User("testUserId2", "testUser2"));
        assertThat(addedUser).isEqualTo(1);
    }
    
    @Test
    void findByUserId() {
        final var user = userDao.findByUserId("testUserId");
        assertThat(user).isEqualTo(new User("testUserId", "testUser"));
    }
    
    @Test
    void deleteUser() {
        int deletedUser = userDao.deleteUser(new User("testUserId2", "testUser2"));
        assertThat(deletedUser).isEqualTo(1);
    }
    
    @Test
    void updateUser() {
        int updateUser = userDao.updateUser(new User("testUserId2", "testUser2"), new User("updateUser1", "updateUser1"));
        assertThat(updateUser).isEqualTo(1);
    }
}