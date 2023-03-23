package database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserDaoTest {
//
//    private final UserDao userDao = new UserDao();
//
//    @BeforeEach
//    void init() {
//        userDao.deleteAll();
//    }
//
//    @Test
//    void connection() {
//        try (final var connection = userDao.getConnection()) {
//            assertThat(connection).isNotNull();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    void addUser() {
//        final var user = new User("testUserId", "testUser");
//        userDao.addUser(user);
//    }
//
//    @Test
//    void findByUserId() {
//        //given
//        final User user = new User("123", "123");
//        userDao.addUser(user);
//
//        //when
//        final User foundUser = userDao.findByUserId("123");
//
//        //then
//        assertThat(foundUser).isEqualTo(new User("123", "123"));
//    }
//
//    @Test
//    void updateUserName() {
//        //given
//        final User user = new User("123", "123");
//        userDao.addUser(user);
//
//        //when
//        userDao.updateUserName("123", "1234");
//
//        //then
//        assertThat(userDao.findByUserId("123")).isEqualTo(new User("123", "1234"));
//    }
//
//    @Test
//    void deleteByUserId() {
//        final User user = new User("123", "123");
//        userDao.addUser(user);
//
//        //when
//        userDao.deleteByUserId("123");
//
//        //then
//        assertThat(userDao.findByUserId("123")).isNull();
//    }
}
