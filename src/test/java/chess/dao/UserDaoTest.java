package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.user.User;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class UserDaoTest {

    private static final String TEST_USER_NAME = "testName";

    private static UserDao userDao;

    @BeforeAll
    static void setup() {
        userDao = new UserDao();
        final Optional<User> testUser = userDao.selectByName(TEST_USER_NAME);
        testUser.ifPresent(user -> userDao.deleteByName(user.getName()));
    }

    @Test
    @Order(1)
    void insert() {
        assertThatCode(() -> userDao.insert(TEST_USER_NAME))
            .doesNotThrowAnyException();
    }

    @Test
    @Order(2)
    void selectByName() {
        final Optional<User> optionalUser = userDao.selectByName(TEST_USER_NAME);
        assertThat(optionalUser).isPresent();
        assertThat(optionalUser.get().getName()).isEqualTo(TEST_USER_NAME);
    }

    @Test
    @Order(3)
    void deleteByName() {
        assertThatCode(() -> userDao.deleteByName(TEST_USER_NAME))
            .doesNotThrowAnyException();
    }
}
