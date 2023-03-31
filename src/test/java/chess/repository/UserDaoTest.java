package chess.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.mysql.JdbcTemplate;
import chess.mysql.TestConnectionPool;
import chess.repository.user.UserDao;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@DisplayNameGeneration(ReplaceUnderscores.class)
@TestMethodOrder(OrderAnnotation.class)
class UserDaoTest {

    private final UserDao userDao = new UserDao(new JdbcTemplate(new TestConnectionPool()));

    @Test
    @Order(1)
    void addUser() {
        //expect
        assertDoesNotThrow(() -> userDao.save("testUser"));
    }

    @Test
    @Order(2)
    void deleteByUserName() {
        assertDoesNotThrow(() -> userDao.deleteByUserName("testUser"));
    }
}
