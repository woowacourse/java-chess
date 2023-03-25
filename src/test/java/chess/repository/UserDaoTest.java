package chess.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.infra.connection.JdbcTemplate;
import chess.infra.connection.TestConnectionPool;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
@TestMethodOrder(OrderAnnotation.class)
class UserDaoTest {

    private final UserDao userDao = new UserDao(new JdbcTemplate(new TestConnectionPool()));

    @Test
    @Order(1)
    void addUser() {
        //expect
        assertDoesNotThrow(() -> userDao.addUser("testUser"));
    }

    @Test
    @Order(2)
    void deleteByUserId() {
        assertDoesNotThrow(() -> userDao.deleteByUserId("testUser"));
    }
}
